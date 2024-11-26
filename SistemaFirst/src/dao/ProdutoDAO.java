package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {
    public static void read() throws ClassNotFoundException, SQLException {
        Connection con = ConexaoUtil.getConnection().Conn();
        String query = "SELECT * FROM produtos WHERE 1";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet resultado = stmt.executeQuery();
        
        while(resultado.next()){
            System.out.println(resultado.getString("ID_produto"));
        }
    }
}
