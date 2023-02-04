package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;

public class UsuarioDAO {

    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public UsuarioDAO() {
        conn = new ConnectionFactory().getConexao();
    }

    public boolean autenticar(Usuario usuario) {
        boolean resultado = false;
        String sql = "SELECT * FROM usuario WHERE nick_usuario = '" + usuario.getNick_usuario() + "' AND senha_usuario = '" + md5(usuario.getSenha_usuario()) + "' ";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultado = true;
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

    public String md5(String senha) {
        String sen = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            sen = hash.toString(16);
        } catch (NoSuchAlgorithmException erro) {
            throw new RuntimeException(erro);
        }
        return sen;
    }

    public String md52(String senha) {
        String sen = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
            sen = hash.toString(16);
        } catch (NoSuchAlgorithmException erro) {
            throw new RuntimeException(erro);
        }
        return sen;
    }
}