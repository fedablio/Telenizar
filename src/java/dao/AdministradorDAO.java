package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Administrador;

public class AdministradorDAO {

    private Connection conn;
    private Statement st;
    private PreparedStatement stmt;
    private ResultSet rs;
    private ArrayList<Administrador> lista = new ArrayList<>();

    public AdministradorDAO() {
        conn = new ConnectionFactory().getConexao();
    }

    public void alterar(Administrador adm) {
        String sql = "UPDATE usuario SET senha_usuario = ? WHERE codigo_usuario = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, md5(adm.getSenha_usuario()));
            stmt.setInt(2, adm.getCodigo_usuario());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public ArrayList<Administrador> listarTodos() {
        String sql = "SELECT * FROM usuario";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Administrador adm = new Administrador();
                adm.setCodigo_usuario(rs.getInt("codigo_usuario"));
                adm.setNick_usuario(rs.getString("nick_usuario"));
                adm.setSenha_usuario(rs.getString("senha_usuario"));
                lista.add(adm);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }

    public String md5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException erro) {
            throw new RuntimeException(erro);
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }
}