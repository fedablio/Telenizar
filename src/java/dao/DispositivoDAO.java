package dao;

import model.Dispositivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DispositivoDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private Statement st;
    private ResultSet rs;
    private ArrayList<Dispositivo> lista = new ArrayList<>();

    public DispositivoDAO() {
        conn = new ConnectionFactory().getConexao();
    }

    public void inserir(Dispositivo dispo) {
        String sql = "INSERT INTO dispositivo (marca_dispositivo, modelo_dispositivo, porta_dispositivo, manual_dispositivo, nuvem_dispositivo, ipv6_dispositivo, observacao_dispositivo, data_dispositivo, hora_dispositivo) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dispo.getMarca_dispositivo());
            stmt.setString(2, dispo.getModelo_dispositivo());
            stmt.setString(3, dispo.getPorta_dispositivo());
            stmt.setString(4, dispo.getManual_dispositivo());
            stmt.setString(5, dispo.getNuvem_dispositivo());
            stmt.setString(6, dispo.getIpv6_dispositivo());
            stmt.setString(7, dispo.getObservacao_dispositivo());
            stmt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
            stmt.setTime(9, new java.sql.Time(new java.util.Date().getTime()));
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException("Erro 201: " + erro);
        }
    }

    public void alterar(Dispositivo dispo) {
        String sql = "UPDATE dispositivo SET marca_dispositivo = ?, modelo_dispositivo = ?, porta_dispositivo = ?, manual_dispositivo = ?, nuvem_dispositivo = ?, ipv6_dispositivo = ?, observacao_dispositivo = ?, data_dispositivo = ?, hora_dispositivo = ? WHERE codigo_dispositivo = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dispo.getMarca_dispositivo());
            stmt.setString(2, dispo.getModelo_dispositivo());
            stmt.setString(3, dispo.getPorta_dispositivo());
            stmt.setString(4, dispo.getManual_dispositivo());
            stmt.setString(5, dispo.getNuvem_dispositivo());
            stmt.setString(6, dispo.getIpv6_dispositivo());
            stmt.setString(7, dispo.getObservacao_dispositivo());
            stmt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
            stmt.setTime(9, new java.sql.Time(new java.util.Date().getTime()));
            stmt.setInt(10, dispo.getCodigo_dispositivo());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public void excluir(int valor) {
        String sql = "DELETE FROM dispositivo WHERE codigo_dispositivo = " + valor;
        try {
            st = conn.createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }

    public ArrayList<Dispositivo> listarTodos() {
        String sql = "SELECT * FROM dispositivo ORDER BY codigo_dispositivo DESC";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Dispositivo dispo = new Dispositivo();
                dispo.setCodigo_dispositivo(rs.getInt("codigo_dispositivo"));
                dispo.setMarca_dispositivo(rs.getString("marca_dispositivo"));
                dispo.setModelo_dispositivo(rs.getString("modelo_dispositivo"));
                dispo.setPorta_dispositivo(rs.getString("porta_dispositivo"));
                dispo.setManual_dispositivo(rs.getString("manual_dispositivo"));
                dispo.setNuvem_dispositivo(rs.getString("nuvem_dispositivo"));
                dispo.setIpv6_dispositivo(rs.getString("ipv6_dispositivo"));
                dispo.setObservacao_dispositivo(rs.getString("observacao_dispositivo"));
                dispo.setData_dispositivo(rs.getDate("data_dispositivo"));
                dispo.setHora_dispositivo(rs.getTime("hora_dispositivo"));
                lista.add(dispo);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }

    public int total() {
        int resultado = 0;
        String sql = "SELECT COUNT(codigo_dispositivo) AS total FROM dispositivo ";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultado = rs.getInt("total");
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

    public double nuvem() {
        double resultado = 0;
        String sql = "SELECT COUNT(codigo_dispositivo) AS total FROM dispositivo WHERE nuvem_dispositivo = 'S' ";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultado = rs.getInt("total");
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

    public double ipv6() {
        double resultado = 0;
        String sql = "SELECT COUNT(codigo_dispositivo) AS total FROM dispositivo WHERE ipv6_dispositivo = 'S' ";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultado = rs.getInt("total");
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

    public double nuvem_ipv6() {
        double resultado = 0;
        String sql = "SELECT COUNT(codigo_dispositivo) AS total FROM dispositivo WHERE nuvem_dispositivo = 'S' AND ipv6_dispositivo = 'S' ";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultado = rs.getInt("total");
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

    public double sem_nuvem_ipv6() {
        double resultado = 0;
        String sql = "SELECT COUNT(codigo_dispositivo) AS total FROM dispositivo WHERE nuvem_dispositivo = 'N' AND ipv6_dispositivo = 'N' ";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                resultado = rs.getInt("total");
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return resultado;
    }

    public ArrayList<String> listarMarca() {
        ArrayList<String> valor = new ArrayList<>();
        String sql = "SELECT marca_dispositivo FROM dispositivo";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String string = rs.getString("marca_dispositivo");
                valor.add(string);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return valor;
    }
}