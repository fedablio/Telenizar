package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Ont2428;

@ManagedBean
@RequestScoped
public class Ont2428Bean {

    private Ont2428 ont2428;
    private String sessao;
    private Boolean disabled1 = true;

    public Ont2428Bean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                ont2428 = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                ont2428 = new Ont2428();
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
        String entrada = ont2428.getCompleta();
        if (entrada.length() < 11 || entrada.length() > 14) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Posição deve ter de 11 a 14 caracteres.", null));
            ont2428.setScript("");
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
            String coment1 = "<font color='gray'>## Excluir o profile ##</font><br/>";
            String linha1 = "<font color='green'>onu profile delete spec " + slot + "/" + door + "/" + position + "</font><br/>";
            String coment2 = "<font color='gray'>## Insere o profile 2428 ##</font><br/>";
            String linha2 = "<font color='green'>onu set " + slot + "/" + door + "/" + position + " meprof zhone-2428</font><br/>";
            String coment3 = "<font color='gray'>## Aplica o profile ##</font><br/>";
            String linha3 = "<font color='green'>onu apply " + slot + "/" + door + "/" + position + "</font><br/>";
            String coment4 = "<font color='gray'>## Atualiza as execuções ##</font><br/>";
            String linha4 = "<font color='green'>update gpon-olt-onu-config ONU-Generic-Assignments-Profile-name= " + olt + "-" + slot + "-" + door + "-" + position + "/gpononu</font>";
            ont2428.setScript(coment1 + linha1 + coment2 + linha2 + coment3 + linha3 + coment4 + linha4);
        }
    }

    public void limpar() {
        ont2428.setCompleta(null);
        ont2428.setScript(null);
    }

    public Ont2428 getOnt2428() {
        return ont2428;
    }

    public void setOnt2428(Ont2428 ont2428) {
        this.ont2428 = ont2428;
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