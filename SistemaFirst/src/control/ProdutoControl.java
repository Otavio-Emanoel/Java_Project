package control;

import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Produto;
import view.ProdutosView;

public class ProdutoControl {
    protected ProdutosView produtosView = new ProdutosView();
    
    public void showJanela(){
        produtosView.setVisible(true);
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
}
