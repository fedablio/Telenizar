package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Modelo;

@ManagedBean
@RequestScoped
public class ModeloBean {

    private Modelo modelo;
    private String sessao;
    private Boolean disabled1 = true;

    public ModeloBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                modelo = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                modelo = new Modelo();
                if(sessao.equals("usuarioFulano")){
                    disabled1 = false;
                }else{
                    disabled1 = true;
                }
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void gerar() {
        if (modelo.getPais().equals("")) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Algum modelo deve ser selecionado.", null));
        } else {
            String comentario01 = "<font color='gray'>## Habilita o terminal ##</font><br/>";
            String comentario02 = "<font color='gray'>## Torna super usuário ##</font><br/>";
            String comentario03 = "<font color='gray'>## Aplica o comando NA ##</font><br/>";
            String aviso01 = "Aumenta a potência da rede 2.4 de 100 para 300 mW, diminui os canais para 11 quando em 20 MHz e para 7 quando em 40 MHz. Pode variar dependendo a versão do firmware.";
            String aviso02 = "Aumenta a potência da rede 2.4 de 100 para 300 mW, diminui os canais para 11 quando em 20 MHz e para 7 quando em 40 MHz. Pode variar dependendo a versão do firmware.";
            String aviso03 = "Mantém a potência da rede 2.4 em 100 mW e diminui os canais para 11 quando em 20 MHz e para 7 quando em 40 MHz. Pode variar dependendo a versão do firmware.";
            String aviso04 = "Mantém as potências das redes 2.4 (100 mW) e 5.8 (200 mW de 5.15 a 5.35 ou 400 mW de 5.47 a 5.725 GHz). Para a rede 2.4, diminui os canais para 11 quando em 20 e para 7 quando em 40 MHz. Para a rede 5.8 aumenta os canais para 9 quando em 20, para 4 quando em 40 e para 8 quando em 80 MHz. Pode variar dependendo a versão do firmware.";
            String aviso05 = "Na prática OCP-D e EU são a mesma coisa. Mantém as potências das redes 2.4 (100 mW) e 5.8 (200 mW de 5.15 a 5.35 ou 400 mW de 5.47 a 5.725 GHz). Para a rede 2.4, diminui os canais para 11 quando em 20 e para 7 quando em 40 MHz. Para a rede 5.8 aumenta os canais para 9 quando em 20, para 4 quando em 40 e para 8 quando em 80 MHz. Pode variar dependendo a versão do firmware.";
            String comando01 = "<font color='green'>enable</font><br/>";
            String comando02 = "<font color='green'>configure terminal</font><br/>";
            String comando03 = "";
            if (modelo.getPais().equals("2426")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, aviso01, null));
                comando03 = "<font color='green'>system model-number ZNID-GPON-2426-NA</font><br/>";
            }
            if (modelo.getPais().equals("2426a")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, aviso02, null));
                comando03 = "<font color='green'>system model-number ZNID-GPON-2426A-NA</font><br/>";
            }
            if (modelo.getPais().equals("2426a1")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, aviso03, null));
                comando03 = "<font color='green'>system model-number ZNID-GPON-2426A1-NA</font><br/>";
            }
            if (modelo.getPais().equals("2428a1")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, aviso04, null));
                comando03 = "<font color='green'>system model-number ZNID-GPON-2428A1-NA</font><br/>";
            }
            if (modelo.getPais().equals("2428a1ocp")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, aviso05, null));
                comando03 = "<font color='green'>system model-number ZNID-GPON-2428A1-NA</font><br/>";
            }
            modelo.setScript(comentario01 + comando01 + comentario02 + comando02 + comentario03 + comando03);
        }
    }

    public void limpar() {
        modelo.setPais(null);
        modelo.setScript(null);
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public Boolean getDisabled1() {
        return disabled1;
    }

    public void setDisabled1(Boolean disabled1) {
        this.disabled1 = disabled1;
    }
    
}