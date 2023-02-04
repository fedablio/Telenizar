package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Cli5;

@ManagedBean
@RequestScoped
public class Cli5Bean {

    private Cli5 cli5;
    private String sessao;
    private Boolean disabled1 = true;

    public Cli5Bean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                cli5 = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                cli5 = new Cli5();
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
        String entrada = cli5.getCompleta();
        String vlan = cli5.getVlan();
        if (entrada.length() < 11 || entrada.length() > 14 || vlan.equals("")) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Posição deve ter de 11 a 14 caracteres e a VLAN deve ser preenchida.", null));
            cli5.setScript("");
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
            String avs01 = "<font color='gray'>## Exibe a versão da OLT ##</font><br/>";
            String com01 = "<font color='green'>swversion</font><br/>";
            String avs02 = "<font color='gray'>## Exclui um possível profile  ##</font><br/>";
            String com02 = "<font color='green'>onu profile delete spec " + slot + "/" + door + "/" + position + "</font><br/>";
            String avs03 = "<font color='gray'>## Cria o profile ##</font><br/>";
            String com03 = "<font color='green'>onu profile create spec  " + slot + "/" + door + "/" + position + " zhone-2428-1 zhone-24281-gen-me</font><br/>";
            String avs04 = "<font color='gray'>## Verifica o gtp usado na bridge com o comando abaixo do campo traf prof ##</font><br/>";
            String com04 = "<font color='green'>onu gemports " + slot + "/" + door + "/" + position + "</font><br/>";
            String posi_alt;
            if (position.equals("1") || position.equals("2") || position.equals("3") || position.equals("4") || position.equals("5") || position.equals("6") || position.equals("7") || position.equals("8") || position.equals("9")) {
                posi_alt = "0" + position;
            } else {
                posi_alt = position;
            }
            String avs05 = "<font color='gray'>## Se necessário, substituir o número após o comando gtp ##</font><br/>";
            String com05 = "<font color='green'>bridge add 1-" + slot + "-" + door + "-" + position + "/gpononu gem 6" + posi_alt + " gtp 4 downlink vlan " + vlan + " tagged wlan 5 rg-bpppoe</font><br/>";
            String avs06 = "<font color='gray'>## Salva as configurações ##</font><br/>";
            String com06 = "<font color='green'>onu apply " + slot + "/" + door + "/" + position + "</font><br/>";
            cli5.setScript(avs01 + com01 + avs02 + com02 + avs03 + com03 + avs04 + com04 + avs05 + com05 + avs06 + com06);
        }
    }

    public void limpar() {
        cli5.setCompleta(null);
        cli5.setVlan(null);
        cli5.setScript(null);
    }

    public Cli5 getCli5() {
        return cli5;
    }

    public void setCli5(Cli5 cli5) {
        this.cli5 = cli5;
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