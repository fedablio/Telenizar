package model;

import java.sql.Date;
import java.sql.Time;

public class Monitor {

    private int id_monitor;
    private String nick_monitor;
    private String ip_monitor;
    private Date data_monitor;
    private Time hora_monitor;

    public int getId_monitor() {
        return id_monitor;
    }

    public void setId_monitor(int id_monitor) {
        this.id_monitor = id_monitor;
    }

    public String getNick_monitor() {
        return nick_monitor;
    }

    public void setNick_monitor(String nick_monitor) {
        this.nick_monitor = nick_monitor;
    }

    public String getIp_monitor() {
        return ip_monitor;
    }

    public void setIp_monitor(String ip_monitor) {
        this.ip_monitor = ip_monitor;
    }

    public Date getData_monitor() {
        return data_monitor;
    }

    public void setData_monitor(Date data_monitor) {
        this.data_monitor = data_monitor;
    }

    public Time getHora_monitor() {
        return hora_monitor;
    }

    public void setHora_monitor(Time hora_monitor) {
        this.hora_monitor = hora_monitor;
    }
}