
package test;

import dao.ConexaoUtil;
import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teste {
    public static void main(String args[]){
        try {
            ProdutoDAO.read();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
