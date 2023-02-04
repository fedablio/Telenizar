package bean;

import dao.MonitorDAO;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Monitor;

@ManagedBean
@RequestScoped
public class MonitorBean {

    private Monitor monitor;
    private String sessao;
    private ArrayList<Monitor> listarTodos;
    private ArrayList<Monitor> listarTodosFiltro;

    public MonitorBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                monitor = null;
                listarTodos = null;
                listarTodosFiltro = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                monitor = new Monitor();
                listarTodos = new MonitorDAO().listarTodos();
                listarTodosFiltro = new MonitorDAO().listarTodos();
                if (!sessao.equals("usuarioFulano")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
                }
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public ArrayList<Monitor> getListarTodos() {
        return listarTodos;
    }

    public void setListarTodos(ArrayList<Monitor> listarTodos) {
        this.listarTodos = listarTodos;
    }

    public ArrayList<Monitor> getListarTodosFiltro() {
        return listarTodosFiltro;
    }

    public void setListarTodosFiltro(ArrayList<Monitor> listarTodosFiltro) {
        this.listarTodosFiltro = listarTodosFiltro;
    }
}