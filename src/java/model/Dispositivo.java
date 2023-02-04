package model;

import java.sql.Date;
import java.sql.Time;

public class Dispositivo {

    private int codigo_dispositivo;
    private String marca_dispositivo;
    private String modelo_dispositivo;
    private String porta_dispositivo;
    private String manual_dispositivo;
    private String nuvem_dispositivo;
    private String ipv6_dispositivo;
    private String observacao_dispositivo;
    private Date data_dispositivo;
    private Time hora_dispositivo;

    public int getCodigo_dispositivo() {
        return codigo_dispositivo;
    }

    public void setCodigo_dispositivo(int codigo_dispositivo) {
        this.codigo_dispositivo = codigo_dispositivo;
    }

    public String getMarca_dispositivo() {
        return marca_dispositivo;
    }

    public void setMarca_dispositivo(String marca_dispositivo) {
        this.marca_dispositivo = marca_dispositivo;
    }

    public String getModelo_dispositivo() {
        return modelo_dispositivo;
    }

    public void setModelo_dispositivo(String modelo_dispositivo) {
        this.modelo_dispositivo = modelo_dispositivo;
    }

    public String getPorta_dispositivo() {
        return porta_dispositivo;
    }

    public void setPorta_dispositivo(String porta_dispositivo) {
        this.porta_dispositivo = porta_dispositivo;
    }

    public String getManual_dispositivo() {
        return manual_dispositivo;
    }

    public void setManual_dispositivo(String manual_dispositivo) {
        this.manual_dispositivo = manual_dispositivo;
    }

    public String getNuvem_dispositivo() {
        return nuvem_dispositivo;
    }

    public void setNuvem_dispositivo(String nuvem_dispositivo) {
        this.nuvem_dispositivo = nuvem_dispositivo;
    }

    public String getIpv6_dispositivo() {
        return ipv6_dispositivo;
    }

    public void setIpv6_dispositivo(String ipv6_dispositivo) {
        this.ipv6_dispositivo = ipv6_dispositivo;
    }

    public String getObservacao_dispositivo() {
        return observacao_dispositivo;
    }

    public void setObservacao_dispositivo(String observacao_dispositivo) {
        this.observacao_dispositivo = observacao_dispositivo;
    }

    public Date getData_dispositivo() {
        return data_dispositivo;
    }

    public void setData_dispositivo(Date data_dispositivo) {
        this.data_dispositivo = data_dispositivo;
    }

    public Time getHora_dispositivo() {
        return hora_dispositivo;
    }

    public void setHora_dispositivo(Time hora_dispositivo) {
        this.hora_dispositivo = hora_dispositivo;
    }
    
}