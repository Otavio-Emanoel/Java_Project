package control;

import dao.ProdutoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import model.ProdutoLiquido;
import model.ProdutoSolido;
import view.ProdutosView;

public class ProdutoControl {
    protected ProdutosView produtosView = new ProdutosView();
    
    public void showJanela(){
        produtosView.setVisible(true);
    }
    
    public void novoProduto(){
        produtosView.getSalvarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = produtosView.getNomeTxt().getText();
                String preco = produtosView.getPreco().getText();
                String medida = produtosView.getMedida().getText();
                String tipo = produtosView.getTipoCmbx().getSelectedItem().toString();
                Produto produto = null;
                
                if(tipo.equals("Sólido")){
                    produto = new ProdutoSolido(0, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco), true);
                    
                } else if (tipo.equals("Liquido")) {
                    produto = new ProdutoLiquido(0, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco), true);

                }
                try {
                    ProdutoDAO.create(produto);
                    System.out.println("Produto Criado com Sucesso");
                    addNaTabela();
                } catch (Exception er) {
                    System.err.println("Houve um erro ao tentar salvar" + er);
                }
                
            }
        });
    }
    
    public void addNaTabela() throws ClassNotFoundException, SQLException{
        ArrayList<Produto> listaProdutos = new ArrayList();
        listaProdutos = ProdutoDAO.read();
        
        DefaultTableModel produtosTblModel;
        produtosTblModel = (DefaultTableModel) produtosView.getProdutosTb().getModel();
        produtosTblModel.setNumRows(0);
        
        for(Produto produto : listaProdutos){
            produtosTblModel.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getMedida(),
                produto.getTipo()
            });
        }
    }
    
    protected int id = -1;
    
    public void selecionarProduto(){
        produtosView.getProdutosTb().addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = (int) produtosView.getProdutosTb().getValueAt(produtosView.getProdutosTb().getSelectedRow(), 0);
                String nome = (String) produtosView.getProdutosTb().getValueAt(produtosView.getProdutosTb().getSelectedRow(), 1);
                double preco = (double) produtosView.getProdutosTb().getValueAt(produtosView.getProdutosTb().getSelectedRow(), 2);
                float medida = (float) produtosView.getProdutosTb().getValueAt(produtosView.getProdutosTb().getSelectedRow(), 3);
                String tipo = (String) produtosView.getProdutosTb().getValueAt(produtosView.getProdutosTb().getSelectedRow(), 4);
                int indiceTipo = -1;
                
                if(tipo.equals("solido")){
                    indiceTipo = 0;
                } else if(tipo.equals("liquido")){
                    indiceTipo = 1;
                }
                
                produtosView.getNomeTxt().setText(nome);
                produtosView.getPreco().setText(String.valueOf(preco));
                produtosView.getMedida().setText(String.valueOf(medida));
                produtosView.getTipoCmbx().setSelectedIndex(indiceTipo);

            }

            @Override
            public void mousePressed(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseExited(MouseEvent e) {
              // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseDragged(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void mouseMoved(MouseEvent e) {
              //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
    }
    
    public void atualizarProduto(){
        produtosView.getAtualizarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = produtosView.getNomeTxt().getText();
                String preco = produtosView.getPreco().getText();
                String medida = produtosView.getMedida().getText();
                String tipo = (String) produtosView.getTipoCmbx().getSelectedItem();
                Produto produto = null;
                
                if(tipo.equals("Sólido")){
                    produto = new ProdutoSolido(id, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco), true);
                } else if(tipo.equals("Liquido")){
                    produto = new ProdutoLiquido(id, nome, Float.parseFloat(medida));
                    produto.setPreco(Double.parseDouble(preco), true);
                }
                
                try {
                    ProdutoDAO.update(produto);
                    System.out.println("Produto atualizado com sucesso!");
                    addNaTabela();
                } catch (Exception ex) {
                    System.err.println("Erro ao atualizar produto: " + ex);
                }
            }
        });
    }
    
    public void removerProduto(){
        produtosView.getApagarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProdutoDAO.delete(id);
                    System.out.println("Produto Deletado com sucesso!");
                    addNaTabela();
                } catch (Exception ex) {
                    System.err.println("Erro ao atualizar produto: " + ex);
                }
            }
        });
    }
}
