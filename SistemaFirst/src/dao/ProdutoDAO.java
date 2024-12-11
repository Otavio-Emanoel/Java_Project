package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {
    public static void create() throws ClassNotFoundException, SQLException{
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "INSERT INTO produtos (nome, preco, medida, tipo) VALUES ('leite','25.00','1.0','Solido')";
        
        PreparedStatement stmt = con.prepareStatement(query);
        
        stmt.execute();
        con.close();
    }
    
    public static void read() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "SELECT * FROM produtos WHERE 1";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet resultado = stmt.executeQuery();
        
        while(resultado.next()){
            System.out.println("ID: "+resultado.getString("ID_produto"));
            System.out.println("NOME: "+resultado.getString("nome"));
            System.out.println("PREÇO: "+resultado.getString("preco"));
            System.out.println("MEDIDA: "+resultado.getString("medida"));
            System.out.println("TIPO: "+resultado.getString("tipo"));
            System.out.println("\n");

        }
        
        con.close();
    }
}
