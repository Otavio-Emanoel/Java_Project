package dao;

import java.sql.DriverManager;

public class ConexaoUtil {

    private String caminho = "localhost";
    private String porta = "3306";
    private String nome = "bdProject";
    private String usuario = "root";
    private String senha = "";

    public String URL = "jdbc:mysql://" + caminho + ":" + porta + "/" + nome + "?useTimezone=true&serverTimezone=GMT";

    public static ConexaoUtil getConnection() {
        ConexaoUtil conexaoUtil = null;
        if (conexaoUtil == null) {
            conexaoUtil = new ConexaoUtil();
            return conexaoUtil;
        } else {
            return null;
        }
    }

    public void Conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.getConnection(URL, usuario, senha);
            System.out.println("CONECTADO COM SUCESSO!");
        } catch (Exception ex) {
            System.err.println("ERRO AO CONECTAR COM O BD: \n" + ex);
        }
    }

}
