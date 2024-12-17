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
    public static void create(Produto produto) throws ClassNotFoundException, SQLException{
        Connection con;
        con = ConexaoUtil.getConnection().Conn();
        String query = "INSERT INTO produtos (nome, preco, medida, tipo)" 
                + " VALUES (?,?,?,?)";
        
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setFloat(3, produto.getMedida());
        stmt.setString(4, produto.getTipo());
        
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
    
    public static void update(Produto produto) throws ClassNotFoundException, SQLException{
        Connection con;
        con = ConexaoUtil.getConnection().Conn();
        String query = "UPDATE produtos SET"
                + " nome = ?,"
                + " preco = ?,"
                + " medida = ?,"
                + " tipo = ?"
                + " WHERE ID_produto = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setFloat(3, produto.getMedida());
        stmt.setString(4, produto.getTipo());
        stmt.setInt(5, produto.getId());
        
        stmt.execute();
        con.close();
    }
    
    public static void delete(int id) throws ClassNotFoundException, SQLException{
        Connection con;
        con = ConexaoUtil.getConnection().Conn();
        String query = "DELETE FROM produtos WHERE ID_produto = ?";
        PreparedStatement stmt = con.prepareStatement(query);
        
        stmt.setInt(1, id);
        
        stmt.execute();
        con.close();
    }
}
