package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConexao() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://endereco/banco", "user", "pass");
        } catch (SQLException | ClassNotFoundException erro) {
            throw new RuntimeException(erro);
        }
        return conexao;
    }
}