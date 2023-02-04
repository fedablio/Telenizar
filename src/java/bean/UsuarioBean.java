package bean;

import dao.MonitorDAO;
import dao.UsuarioDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import model.Monitor;
import model.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean {

    private Usuario usuario;
    private String sessao;
    private String versao = "2.6.5";

    public UsuarioBean() {
        usuario = new Usuario();
        sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
        if (sessao == null) {
            sessao = "";
        }
    }

    public void autenticar() {
        try {
            if (usuario.getNick_usuario().equals("") || usuario.getSenha_usuario().equals("")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e Senha devem ser preenchidos.", null));
            } else {
                if (new UsuarioDAO().autenticar(usuario)) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("_NICK_", usuario.getNick_usuario());
                    FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
                    dedo_duro();
                } else {
                    FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário e/ou Senha inválidos.", null));
                }
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    private void dedo_duro() {
        Monitor monitor = new Monitor();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        monitor.setNick_monitor(usuario.getNick_usuario());
        monitor.setIp_monitor(request.getRemoteAddr());
        monitor.setData_monitor(new java.sql.Date(new java.util.Date().getTime()));
        monitor.setHora_monitor(new java.sql.Time(new java.util.Date().getTime()));
        new MonitorDAO().inserir(monitor);
    }

    public void limpar() {
        usuario.setNick_usuario(null);
        usuario.setSenha_usuario(null);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}