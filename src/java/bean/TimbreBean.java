package bean;

import dao.TimbreDAO;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Timbre;
import org.primefaces.event.SelectEvent;

@ManagedBean
@RequestScoped
public class TimbreBean {

    private String sessao;
    private Timbre timbre;
    private Timbre timbreSelecionado;
    private ArrayList<Timbre> listarTodos;
    private ArrayList<Timbre> listarTodosFiltro;
    private Boolean disabled1 = true;
    private Boolean disabled2 = true;
    private Boolean disabled3 = true;

    public TimbreBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                timbre = null;
                timbreSelecionado = null;
                listarTodos = null;
                listarTodosFiltro = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                if (sessao.equals("usuarioFulano")) {
                    disabled1 = false;
                    disabled3 = false;
                } else {
                    disabled1 = true;
                    disabled3 = true;
                }
                timbre = new Timbre();
                timbreSelecionado = new Timbre();
                listarTodos = new TimbreDAO().listarTodos();
                listarTodosFiltro = new TimbreDAO().listarTodos();
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void inserir() {
        try {
            if (timbre.getNome_timbre().equals("") || timbre.getAssunto_timbre().equals("") || timbre.getAssunto_timbre().equals("")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome, Assunto e Conteúdo devem ser preechidos.", null));
            } else {
                new TimbreDAO().inserir(timbre);
                FacesContext.getCurrentInstance().getExternalContext().redirect("fig.jsf");
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void alterar() {
        try {
            if (timbreSelecionado.getNome_timbre().equals("") || timbreSelecionado.getAssunto_timbre().equals("") || timbreSelecionado.getAssunto_timbre().equals("")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome, Assunto e Conteúdo devem ser preechidos.", null));
            } else {
                new TimbreDAO().alterar(timbreSelecionado);
                FacesContext.getCurrentInstance().getExternalContext().redirect("fig.jsf");
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void excluir(int valor) {
        try {
            new TimbreDAO().excluir(valor);
            FacesContext.getCurrentInstance().getExternalContext().redirect("fig.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void onRowSelect(SelectEvent event) {
        disabled2 = false;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public Timbre getTimbre() {
        return timbre;
    }

    public void setTimbre(Timbre timbre) {
        this.timbre = timbre;
    }

    public Timbre getTimbreSelecionado() {
        return timbreSelecionado;
    }

    public void setTimbreSelecionado(Timbre timbreSelecionado) {
        this.timbreSelecionado = timbreSelecionado;
    }

    public ArrayList<Timbre> getListarTodos() {
        return listarTodos;
    }

    public void setListarTodos(ArrayList<Timbre> listarTodos) {
        this.listarTodos = listarTodos;
    }

    public ArrayList<Timbre> getListarTodosFiltro() {
        return listarTodosFiltro;
    }

    public void setListarTodosFiltro(ArrayList<Timbre> listarTodosFiltro) {
        this.listarTodosFiltro = listarTodosFiltro;
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

    public Boolean getDisabled3() {
        return disabled3;
    }

    public void setDisabled3(Boolean disabled3) {
        this.disabled3 = disabled3;
    }
    
    
}