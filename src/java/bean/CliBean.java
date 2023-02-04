package bean;

import model.Cli;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CliBean {

    private Cli cli;
    private String sessao;
    private Boolean disabled1 = true;

    public CliBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                cli = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                cli = new Cli();
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
        String contrato = cli.getContrato();
        String fsan = cli.getFsan();
        String slot = cli.getSlot();
        String door = cli.getDoor();
        String position = cli.getPosition();
        String vlan = cli.getVlan();
        if (contrato.equals("") || fsan.equals("") || slot.equals("") || door.equals("") || position.equals("") || vlan.equals("")) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Os campos Contrato, FSAN, Slot, Porta, Posição e VLAN devem ser preenchidos.", null));
            cli.setScript(null);
        } else {
            String com01 = "<font color='green'>onu show " + slot + "/" + door + "/" + position + "</font><br/>";
            String com02 = "<font color='green'>onu delete " + slot + "/" + door + "/" + position + "</font><br/>";
            String com03 = "<font color='green'>y</font><br/>";
            String com04 = "<font color='green'>n</font><br/>";
            String com05 = "<font color='green'>y</font><br/>";
            String avs01 = "<font color='gray'>### Verificar se a ONT voltou para unassigned ###</font><br/>";
            String avs02 = "<font color='gray'>### se não aparecer nada, é problema de sinal ###</font><br/>";
            String com06 = "<font color='green'>onu show " + slot + "/" + door + "</font><br/>";
            String avs03 = "<font color='gray'>### Iniciar a configuração ###</font><br/>";
            String com07 = "<font color='green'>onu set " + slot + "/" + door + "/" + position + " meprof zhone-2426</font><br/>";
            String com08 = "<font color='green'>cpe system add " + slot + "/" + door + "/" + position + " sys-common-profile 1</font><br/>";
            String com09 = "<font color='green'>update gpon-olt-onu-config onu-added=true 1/" + slot + "/" + door + "/" + position + "/gpononu</font><br/>";
            String com10 = "<font color='green'>onu clear " + slot + "/" + door + "/" + position + " regid</font><br/>";
            String com11 = "<font color='green'>onu set 1-" + slot + "-" + door + "-" + position + " vendorid ZNTS serno fsan " + fsan + "</font><br/>";
            String avs04 = "<font color='gray'>### Verificar se a ONT subiu com o comando abaixo ###</font><br/>";
            String com12 = "<font color='green'>onu status " + slot + "/" + door + "/" + position + "</font><br/>";
            String avs05 = "<font color='gray'>### Configurações de bridge e PPPoE ###</font><br/>";
            String posi_alt;
            if (position.equals("1") || position.equals("2") || position.equals("3") || position.equals("4") || position.equals("5") || position.equals("6") || position.equals("7") || position.equals("8") || position.equals("9")) {
                posi_alt = "0" + position;
            } else {
                posi_alt = position;
            }
            String com13 = "<font color='green'>bridge add 1-" + slot + "-" + door + "-" + position + "/gpononu gem 6"+posi_alt+" gtp 4 downlink vlan " + vlan + " tagged eth 1 rg-bpppoe</font><br/>";
            String com14 = "<font color='green'>bridge add 1-" + slot + "-" + door + "-" + position + "/gpononu gem 6"+posi_alt+" gtp 4 downlink vlan " + vlan + " tagged eth 2 rg-bpppoe</font><br/>";
            String com15 = "<font color='green'>bridge add 1-" + slot + "-" + door + "-" + position + "/gpononu gem 6"+posi_alt+" gtp 4 downlink vlan " + vlan + " tagged eth 3 rg-bpppoe</font><br/>";
            String com16 = "<font color='green'>bridge add 1-" + slot + "-" + door + "-" + position + "/gpononu gem 6"+posi_alt+" gtp 4 downlink vlan " + vlan + " tagged wlan 1 rg-bpppoe</font><br/>";
            String com17 = "<font color='green'>bridge add 1-" + slot + "-" + door + "-" + position + "/gpononu gem 6"+posi_alt+" gtp 4 downlink vlan " + vlan + " tagged eth 4 rg-bpppoe</font><br/>";
            String com18 = "<font color='green'>cpe system sysinfo add 1-" + slot + "-" + door + "-" + position + " location " + contrato + " name " + contrato + ":1-" + slot + "-" + door + "-" + position + "</font><br/>";
            String com19 = "<font color='green'>cpe rg wan modify " + slot + "/" + door + "/" + position + " pppoe-usr-id " + contrato + "@empresa.com</font><br/>";
            String com20 = "<font color='green'>cpe rg wan modify " + slot + "/" + door + "/" + position + " pppoe-password " + contrato + "</font><br/>";
            String com21 = "<font color='green'>cpe rg wan show " + slot + "/" + door + "/" + position + " showhidden</font><br/>";
            String com22 = "<font color='green'>update gpon-olt-onu-config dba-status-reporting=enabled 1/" + slot + "/" + door + "/" + position + "/gpononu</font><br/>";

            String com23 = "<font color='green'>update bridge-interface-record bridgeIfIngressPacketRuleGroupIndex = " + position + " 1-" + slot + "-" + door + "-6" + posi_alt + "-gponport-" + vlan + "/bridge</font><br/>";
            String com24 = "<font color='green'>update gpon-olt-onu-config line-status-traps=enabled 1-" + slot + "-" + door + "-" + position + "/gpononu</font><br/>";
            String com25 = "<font color='green'>onu profile create spec " + slot + "/" + door + "/" + position + " zhone-2426-1 zhone-24261-gen-me</font><br/>";
            cli.setScript(com01 + com02 + com03 + com04 + com05 + avs01 + avs02 + com06 + avs03 + com07 + com08 + com09 + com10 + com11 + avs04 + com12 + avs05 + com13 + com14 + com15 + com16 + com17 + com18 + com19 + com20 + com21 + com22 + com23 + com24 + com25);
        }
    }

    public void limpar() {
        cli.setContrato(null);
        cli.setFsan(null);
        cli.setSlot(null);
        cli.setDoor(null);
        cli.setPosition(null);
        cli.setVlan(null);
        cli.setScript(null);
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

    public Cli getCli() {
        return cli;
    }

    public void setCli(Cli cli) {
        this.cli = cli;
    }
}