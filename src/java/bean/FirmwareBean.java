package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Firmware;

@ManagedBean
@RequestScoped
public class FirmwareBean {

    private Firmware firmware;
    private String sessao;

    public FirmwareBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                firmware = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                firmware = new Firmware();
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void gerar() {
        String modelo = firmware.getModelo();
        String versao = firmware.getVersao();
        String entrada = firmware.getCompleta();
        if (modelo.equals("") || versao.equals("") || entrada.length() < 11 || entrada.length() > 14) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "O campo Modelo e Versão devem ser preenchidos. A Posição deve ter de 11 a 14 caracteres.", null));
            firmware.setScript(null);
        } else {
            String comando1 = "";
            String comando2 = "";
            String comando3 = "";
            String coment1 = "";
            String coment2 = "";
            String coment3 = "";
            int quantidade = entrada.length();
            String olt = "";
            String slot = "";
            String door = "";
            String position = "";
            if (quantidade == 11) {
                olt = entrada.substring(4, 5);
                slot = entrada.substring(6, 7);
                door = entrada.substring(8, 9);
                position = entrada.substring(10, 11);
            }
            if (quantidade == 12) {
                //PON-1/1/1.11
                if (entrada.substring(9, 10).equals(".")) {
                    olt = entrada.substring(4, 5);
                    slot = entrada.substring(6, 7);
                    door = entrada.substring(8, 9);
                    position = entrada.substring(10, 12);
                }
                //PON-1/11/1.1
                if (!entrada.substring(9, 10).equals(".")) {
                    olt = entrada.substring(4, 5);
                    slot = entrada.substring(6, 8);
                    door = entrada.substring(9, 10);
                    position = entrada.substring(11, 12);
                }
                //PON-1/1/11.1
                if (!entrada.substring(9, 10).equals(".") && entrada.substring(7, 8).equals("/")) {
                    olt = entrada.substring(4, 5);
                    slot = entrada.substring(6, 7);
                    door = entrada.substring(8, 10);
                    position = entrada.substring(11, 12);
                }
            }
            if (quantidade == 13) {
                //PON-1/1/11.11
                if (entrada.substring(7, 8).equals("/") && entrada.substring(10, 11).equals(".")) {
                    olt = entrada.substring(4, 5);
                    slot = entrada.substring(6, 7);
                    door = entrada.substring(8, 10);
                    position = entrada.substring(11, 13);
                }
                //PON-1/11/1.11
                if (!entrada.substring(7, 8).equals("/") && entrada.substring(10, 11).equals(".")) {
                    olt = entrada.substring(4, 5);
                    slot = entrada.substring(6, 8);
                    door = entrada.substring(9, 10);
                    position = entrada.substring(11, 13);
                }
                //PON-1/11/11.1
                if (!entrada.substring(10, 11).equals(".")) {
                    olt = entrada.substring(4, 5);
                    slot = entrada.substring(6, 8);
                    door = entrada.substring(9, 11);
                    position = entrada.substring(12, 13);
                }
            }
            //PON-1/11/11.11
            if (quantidade == 14) {
                olt = entrada.substring(4, 5);
                slot = entrada.substring(6, 8);
                door = entrada.substring(9, 11);
                position = entrada.substring(12, 14);
            }
            if (modelo.substring(0, 14).equals("ZNID-GPON-2426")) {
                if (versao.equals("S3.1.256") || versao.equals("S3.1.276")) {
                    String texto = "Para este modelo pode encontrar as versões S3.1.256, S3.1.276, S3.1.282 ou S3.1.330";
                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, texto, null));
                    coment1 = "<font color='gray'>## executa o upgrade ##</font><br/>";
                    comando1 = "<font color='green'>onu image download-activate-commit " + slot + "/" + door + "/" + position + " znid24xxsip_0301330.img part 1</font><br/>";
                    coment2 = "<font color='gray'>## verificar a situação do upgrade ##</font><br/>";
                    comando2 = "<font color='green'>onu image show " + slot + "/" + door + "/" + position + "</font><br/>";
                    coment3 = "<font color='gray'>## verificar a situação da ONT ##</font><br/>";
                    comando3 = "<font color='green'>onu status " + slot + "/" + door + "/" + position + "</font><br/>";
                } else {
                    firmware.setScript(null);
                }
            } else {
                firmware.setScript(null);
            }
            if (modelo.substring(0, 15).equals("ZNID-GPON-2426A")) {
                if (versao.equals("S3.0.725") || versao.equals("S3.1.276") || versao.equals("S3.1.282") || versao.equals("S3.1.320")) {
                    String texto = "Para este modelo pode encontrar as versões S3.0.725, S3.1.276, S3.1.282, S3.1.320 ou 'S3.1.330'";
                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, texto, null));
                    coment1 = "<font color='gray'>## executa o upgrade ##</font><br/>";
                    comando1 = "<font color='green'>onu image download-activate-commit " + slot + "/" + door + "/" + position + " znid24xxasip_0301330.img part 1</font><br/>";
                    coment2 = "<font color='gray'>## verificar a situação do upgrade ##</font><br/>";
                    comando2 = "<font color='green'>onu image show " + slot + "/" + door + "/" + position + "</font><br/>";
                    coment3 = "<font color='gray'>## verificar a situação da ONT ##</font><br/>";
                    comando3 = "<font color='green'>onu status " + slot + "/" + door + "/" + position + "</font><br/>";
                } else {
                    firmware.setScript(null);
                }
            } else {
                firmware.setScript(null);
            }
            if (modelo.substring(0, 16).equals("ZNID-GPON-2428A1") || modelo.substring(0, 16).equals("ZNID-GPON-2426A1")) {
                if (versao.equals("S4.1.029") || versao.equals("S4.1.030") || versao.equals("S4.1.048") || versao.equals("S4.1.056") || versao.equals("S4.1.100") || versao.equals("S4.1.223")) {
                    String texto = "Para este modelo pode encontrar as versões S4.1.029, S4.1.030, S4.1.048, S4.1.056, S4.1.100, S4.1.223 ou 'S4.1.224'";
                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, texto, null));
                    coment1 = "<font color='gray'>## executa o upgrade ##</font><br/>";
                    comando1 = "<font color='green'>onu image download-activate-commit " + slot + "/" + door + "/" + position + " znid24xxa1sip_0401224.img part 1</font><br/>";
                    coment2 = "<font color='gray'>## verificar a situação do upgrade ##</font><br/>";
                    comando2 = "<font color='green'>onu image show " + slot + "/" + door + "/" + position + "</font><br/>";
                    coment3 = "<font color='gray'>## verificar a situação da ONT ##</font><br/>";
                    comando3 = "<font color='green'>onu status " + slot + "/" + door + "/" + position + "</font><br/>";
                } else {
                    firmware.setScript(null);
                }
            } else {
                firmware.setScript(null);
            }
            firmware.setScript(coment1 + comando1 + coment2 + comando2 + coment3 + comando3);
        }
    }

    public void limpar() {
        firmware.setModelo(null);
        firmware.setVersao(null);
        firmware.setCompleta(null);
        firmware.setScript(null);
    }

    public Firmware getFirmware() {
        return firmware;
    }

    public void setFirmware(Firmware firmware) {
        this.firmware = firmware;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }
}