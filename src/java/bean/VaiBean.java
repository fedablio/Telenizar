package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class VaiBean {

    private String titleLink1;
    private String titleMenu1;
    private String titleButton1;
    private String titleButton2;

    public VaiBean() {
        String sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
        if (!sessao.equals("usuarioFulano")) {
            titleMenu1 = "Sem previlégio para inserir.";
            titleButton1 = "Sem previlégio para alterar.";
            titleButton2 = "Sem previlégio para excluir.";
        } else {
            titleMenu1 = "Inserir novo registro.";
            titleButton1 = "Selecione algum registro para alterar.";
            titleButton2 = "Selecione algum registro para excluir.";
        }
        titleLink1 = "Encerrar sessão.";
    }

    public void p_apple() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_perry() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_lemon() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("lemon.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_cherry() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cherry.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_guava() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("guava.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_peach() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("peach.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_plum() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("plum.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_orange() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("orange.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_melon() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("melon.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_cocoa() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cocoa.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void p_fig() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("fig.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void sair() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exceção", String.valueOf(erro)));
        }
    }

    public String getTitleLink1() {
        return titleLink1;
    }

    public void setTitleLink1(String titleLink1) {
        this.titleLink1 = titleLink1;
    }

    public String getTitleMenu1() {
        return titleMenu1;
    }

    public void setTitleMenu1(String titleMenu1) {
        this.titleMenu1 = titleMenu1;
    }

    public String getTitleButton1() {
        return titleButton1;
    }

    public void setTitleButton1(String titleButton1) {
        this.titleButton1 = titleButton1;
    }

    public String getTitleButton2() {
        return titleButton2;
    }

    public void setTitleButton2(String titleButton2) {
        this.titleButton2 = titleButton2;
    }
}