package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Remoto;

@ManagedBean
@RequestScoped
public final class RemotoBean {

    private Remoto remoto;
    private String sessao;
    private Boolean disabled1 = true;
    private String ip;
    private String porta;
    private String url1;
    private String url2;

    public RemotoBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                remoto = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                remoto = new Remoto();
                if (sessao.equals("usuarioFulano")) {
                    disabled1 = false;
                } else {
                    disabled1 = true;
                }
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void gerar() {
        String entrada = remoto.getCompleta();
        if (entrada.length() < 11 || entrada.length() > 14) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Posição deve ter de 11 a 14 caracteres.", null));
            remoto.setScript("");
        } else {
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
            String ol = olt;
            String sl = slot;
            String dr = door;
            String po = position;
            String poa;
            if (po.equals("1") || po.equals("2") || po.equals("3") || po.equals("4") || po.equals("5") || po.equals("6") || po.equals("7") || po.equals("8") || po.equals("9")) {
                poa = "0" + po;
            } else {
                poa = po;
            }
            String exp1 = "<font color='gray'>## Exclui possíveis profiles de acesso remoto na posição ##</font><br/>";
            String excluir = "<font color='green'>cpe-mgr delete local " + ol + "-" + sl + "-" + dr + "-5" + poa + "/gponport gtp 512</font><br/>";
            String exp2 = "<font color='gray'>## Exibe os profiles da posição ##</font><br/>";
            String gem = "<font color='green'>onu gemports " + sl + "/" + dr + "/" + po + "</font><br/>";
            String exp3 = "<font color='gray'>## Insere o profile de acesso remoto na posição ##</font><br/>";
            String adicionar = "<font color='green'>cpe-mgr add local " + ol + "-" + sl + "-" + dr + "-5" + poa + "/gponport gtp 512</font><br/>";
            String exp4 = "<font color='gray'>## Exibe o Public IP address e Port HTTP a serem utilizados ##</font><br/>";
            String mostrar = "<font color='green'>cpe-mgr show local " + ol + "-" + sl + "-" + dr + "-5" + poa + "</font>";
            remoto.setScript(exp1 + excluir + exp2 + gem + exp3 + adicionar + exp4 + mostrar);
        }
    }

    public void limpar() {
        remoto.setCompleta(null);
        remoto.setScript(null);
    }

    public void url() {
        if (ip.equals("") || porta.equals("")) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "IP e Porta devem ser preechidos.", null));
        } else {
            String endereco = "http://" + ip + ":" + porta + "/";
            url1 = "Para acessar a ONT remotamente, <a href=\"" + endereco + "\" target=\"_blank\" style=\"color: blue;\">clique aqui</a> ou copie o que está em amarelo e cole no navegador de internet: <font style=\"background-color: yellow;\">" + endereco + "</font>";
            url2 = endereco;
        }
    }

    public void limpar2() {
        ip = null;
        porta = null;
        url1 = null;
        url2 = null;
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

    public Remoto getRemoto() {
        return remoto;
    }

    public void setRemoto(Remoto remoto) {
        this.remoto = remoto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }
}