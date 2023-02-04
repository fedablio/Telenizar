package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Monitor;

public class MonitorDAO {

    private Connection conn;
    private Statement st;
    private PreparedStatement stmt;
    private ResultSet rs;
    private ArrayList<Monitor> lista = new ArrayList<>();

    public MonitorDAO() {
        conn = new ConnectionFactory().getConexao();
    }

    public ArrayList<Monitor> listarTodos() {
        String sql = "SELECT * FROM monitor ORDER BY id_monitor DESC";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Monitor mon = new Monitor();
                mon.setId_monitor(rs.getInt("id_monitor"));
                mon.setNick_monitor(rs.getString("nick_monitor"));
                mon.setIp_monitor(rs.getString("ip_monitor"));
                mon.setData_monitor(rs.getDate("data_monitor"));
                mon.setHora_monitor(rs.getTime("hora_monitor"));
                lista.add(mon);
            }
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
        return lista;
    }

    public void inserir(Monitor monitor) {
        String sql = "INSERT INTO monitor (nick_monitor, ip_monitor, data_monitor, hora_monitor) VALUES (?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, monitor.getNick_monitor());
            stmt.setString(2, monitor.getIp_monitor());
            stmt.setDate(3, monitor.getData_monitor());
            stmt.setTime(4, monitor.getHora_monitor());
            stmt.execute();
            stmt.close();
        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
}