
package test;

import control.ProdutoControl;
import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teste {
    public static void main(String args[]){
        
        ProdutoControl produtoControl = new ProdutoControl();
        try {
            produtoControl.addNaTabela();
            produtoControl.novoProduto();
            produtoControl.selecionarProduto();
            produtoControl.atualizarProduto();
            produtoControl.removerProduto();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        produtoControl.showJanela();
    }
}
