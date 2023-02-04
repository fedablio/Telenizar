package bean;

import dao.AdministradorDAO;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Administrador;
import org.primefaces.event.SelectEvent;

@ManagedBean
@RequestScoped
public class AdministradorBean {

    private Administrador administrador;
    private Administrador administradorSelecionado;
    private String sessao;
    private Boolean disabled1 = true;
    private Boolean disabled2 = true;
    private ArrayList<Administrador> listarTodos;

    public AdministradorBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                administrador = null;
                administradorSelecionado = null;
                listarTodos = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                administrador = new Administrador();
                administradorSelecionado = new Administrador();
                listarTodos = new AdministradorDAO().listarTodos();
                if (sessao.equals("usuarioFulano")) {
                    disabled1 = false;
                } else {
                    disabled1 = true;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
                }
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void onRowSelect(SelectEvent event) {
        disabled2 = false;
    }

    public void alterar() {
        try {
            if (administradorSelecionado.getSenha_usuario().equals("")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Senha deve ser preechida.", null));
            } else {
                new AdministradorDAO().alterar(administradorSelecionado);
                FacesContext.getCurrentInstance().getExternalContext().redirect("orange.jsf");
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Administrador getAdministradorSelecionado() {
        return administradorSelecionado;
    }

    public void setAdministradorSelecionado(Administrador administradorSelecionado) {
        this.administradorSelecionado = administradorSelecionado;
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

    public Boolean getDisabled2() {
        return disabled2;
    }

    public void setDisabled2(Boolean disabled2) {
        this.disabled2 = disabled2;
    }

    public ArrayList<Administrador> getListarTodos() {
        return listarTodos;
    }

    public void setListarTodos(ArrayList<Administrador> listarTodos) {
        this.listarTodos = listarTodos;
    }
}