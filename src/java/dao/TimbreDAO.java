package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Timbre;

public class TimbreDAO {

    private Connection conn;
    private ArrayList<Timbre> lista = new ArrayList<>();
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;

    public TimbreDAO() {
        conn = new ConnectionFactory().getConexao();
    }

    public void inserir(Timbre tim) {
        String sql = "INSERT INTO timbre (nome_timbre, assunto_timbre, conteudo_timbre) VALUES (?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tim.getNome_timbre());
            stmt.setString(2, tim.getAssunto_timbre());
            stmt.setString(3, tim.getConteudo_timbre());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public void alterar(Timbre tim) {
        String sql = "UPDATE timbre SET nome_timbre = ?, assunto_timbre = ?, conteudo_timbre = ? WHERE id_timbre = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tim.getNome_timbre());
            stmt.setString(2, tim.getAssunto_timbre());
            stmt.setString(3, tim.getConteudo_timbre());
            stmt.setInt(4, tim.getId_timbre());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public void excluir(int valor) {
        String sql = "DELETE FROM timbre WHERE id_timbre = " + valor;
        try {
            st = conn.createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public ArrayList<Timbre> listarTodos() {
        String sql = "SELECT * FROM timbre";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Timbre tim = new Timbre();
                tim.setId_timbre(rs.getInt("id_timbre"));
                tim.setNome_timbre(rs.getString("nome_timbre"));
                tim.setAssunto_timbre(rs.getString("assunto_timbre"));
                tim.setConteudo_timbre(rs.getString("conteudo_timbre"));
                lista.add(tim);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }
}