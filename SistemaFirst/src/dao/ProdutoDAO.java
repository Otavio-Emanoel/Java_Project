package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Produto;
import model.ProdutoLiquido;
import model.ProdutoSolido;

public class ProdutoDAO {
    public static void create() throws ClassNotFoundException, SQLException{
        Connection con;
        con = ConexaoUtil.getConnection().Conn();
        String query = "INSERT INTO produtos (nome, preco, medida, tipo) VALUES ('leite','25.00','1.0','Solido')";
        
        PreparedStatement stmt = con.prepareStatement(query);
        
        stmt.execute();
        con.close();
    }
    
    public static ArrayList read() throws ClassNotFoundException, SQLException {
        ArrayList<Produto> listaDeProdutos = new ArrayList();
        Produto produto = null;
        Connection con;
        con = ConexaoUtil.getConnection().Conn();
        String query = "SELECT * FROM produtos WHERE 1";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet resultado = stmt.executeQuery();
        
        while(resultado.next()){
            if (resultado.getString("tipo").equalsIgnoreCase("solido")) {
                produto = new ProdutoSolido(
                resultado.getInt("ID_produto"),
                resultado.getString("nome"),
                resultado.getFloat("medida"));
                produto.setPreco(resultado.getDouble("preco"));
            } else if (resultado.getString("tipo").equalsIgnoreCase("liquido")) {
                produto = new ProdutoLiquido(
                resultado.getInt("ID_produto"),
                resultado.getString("nome"),
                resultado.getFloat("medida"));
                produto.setPreco(resultado.getDouble("preco"));
            }
            listaDeProdutos.add(produto);
            
                    
        }
        
        con.close();
        return listaDeProdutos;
    }
    
    public static void update() throws ClassNotFoundException, SQLException{
        Connection con;
        con = ConexaoUtil.getConnection().Conn();
        String query = "UPDATE produtos SET"
                + " nome = 'Sabao',"
                + " preco = 1.20,"
                + " medida = 400.0,"
                + " tipo = 'solido'"
                + " WHERE ID_produto = 2";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.execute();
        con.close();
    }
    
    public static void delete() throws ClassNotFoundException, SQLException{
        Connection con;
        con = ConexaoUtil.getConnection().Conn();
        String query = "DELETE FROM produtos WHERE ID_produto = 2";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.execute();
        con.close();
    }
}
