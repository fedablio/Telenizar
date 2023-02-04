package bean;

import model.Dispositivo;
import dao.DispositivoDAO;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@RequestScoped
public class DispositivoBean {

    private Dispositivo dispositivo;
    private Dispositivo dispositivoSelecionado;
    private ArrayList<Dispositivo> listarTodos;
    private ArrayList<Dispositivo> listarTodosFiltro;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel pieModel3;
    private PieChartModel pieModel4;
    private int total_dispositivo;
    private Boolean disabled1 = true;
    private Boolean disabled2 = true;
    private Boolean disabled3 = true;
    private Boolean disabled4 = true;
    private Boolean disabled5 = true;
    private ArrayList<String> listarMarca;
    private String sessao;

    public DispositivoBean() {
        try {
            sessao = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("_NICK_");
            if (sessao == null) {
                dispositivo = null;
                dispositivoSelecionado = null;
                listarTodos = null;
                listarTodosFiltro = null;
                listarMarca = null;
                FacesContext.getCurrentInstance().getExternalContext().redirect("perry.jsf");
            } else {
                dispositivo = new Dispositivo();
                dispositivoSelecionado = new Dispositivo();
                listarTodos = new DispositivoDAO().listarTodos();
                listarTodosFiltro = new DispositivoDAO().listarTodos();
                createPieModel1();
                createPieModel2();
                createPieModel3();
                createPieModel4();
                total_dispositivo = new DispositivoDAO().total();
                listarMarca = new DispositivoDAO().listarMarca();
                if (sessao.equals("usuarioFulano")) {
                    disabled4 = false;
                    disabled5 = false;
                } else {
                    disabled4 = true;
                    disabled5 = true;
                }
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void inserir() {
        try {
            if (dispositivo.getMarca_dispositivo().equals("") || dispositivo.getModelo_dispositivo().equals("") || dispositivo.getNuvem_dispositivo().equals("") || dispositivo.getIpv6_dispositivo().equals("")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Marca, Modelo, Nuvem (cloud) e IPv6 devem ser preechidos.", null));
            } else {
                new DispositivoDAO().inserir(dispositivo);
                FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void alterar() {
        try {
            if (dispositivoSelecionado.getMarca_dispositivo().equals("") || dispositivoSelecionado.getModelo_dispositivo().equals("") || dispositivoSelecionado.getNuvem_dispositivo().equals("") || dispositivoSelecionado.getIpv6_dispositivo().equals("")) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Marca, Modelo, Nuvem (cloud) e IPv6 devem ser preechidos.", null));
            } else {
                new DispositivoDAO().alterar(dispositivoSelecionado);
                FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
            }
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    public void excluir(int valor) {
        try {
            new DispositivoDAO().excluir(valor);
            FacesContext.getCurrentInstance().getExternalContext().redirect("apple.jsf");
        } catch (Exception erro) {
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(erro), null));
        }
    }

    private void createPieModel1() {
        double total = new DispositivoDAO().total();
        double nuvem = new DispositivoDAO().nuvem();
        pieModel1 = new PieChartModel();
        pieModel1.setTitle("c/ Nuvem");
        pieModel1.setLegendPosition("w");
        pieModel1.setFill(true);
        pieModel1.setShowDataLabels(true);
        pieModel1.set("Nuvem", nuvem);
        pieModel1.set("Total", total);
        pieModel1.setDiameter(100);
    }

    private void createPieModel2() {
        double total = new DispositivoDAO().total();
        double ipv6 = new DispositivoDAO().ipv6();
        pieModel2 = new PieChartModel();
        pieModel2.setTitle("c/ IPv6");
        pieModel2.setLegendPosition("w");
        pieModel2.setFill(true);
        pieModel2.setShowDataLabels(true);
        pieModel2.set("IPv6", ipv6);
        pieModel2.set("Total", total);
        pieModel2.setDiameter(100);
    }

    private void createPieModel3() {
        double total = new DispositivoDAO().total();
        double nuvem_ipv6 = new DispositivoDAO().nuvem_ipv6();
        pieModel3 = new PieChartModel();
        pieModel3.setTitle("c/ Nuvem e IPv6");
        pieModel3.setLegendPosition("w");
        pieModel3.setFill(true);
        pieModel3.setShowDataLabels(true);
        pieModel3.set("Nuvem e IPv6", nuvem_ipv6);
        pieModel3.set("Total", total);
        pieModel3.setDiameter(100);
    }

    private void createPieModel4() {
        double total = new DispositivoDAO().total();
        double sem_nuvem_ipv6 = new DispositivoDAO().sem_nuvem_ipv6();
        pieModel4 = new PieChartModel();
        pieModel4.setTitle("s/ Nuvem e IPv6");
        pieModel4.setLegendPosition("w");
        pieModel4.setFill(true);
        pieModel4.setShowDataLabels(true);
        pieModel4.set("Nuvem e IPv6", sem_nuvem_ipv6);
        pieModel4.set("Total", total);
        pieModel4.setDiameter(100);
    }

    public void onRowSelect(SelectEvent event) {
        if (sessao.equals("usuarioFulano")) {
            disabled1 = false;
            disabled2 = false;
            disabled3 = false;
        } else {
            disabled1 = true;
            disabled2 = true;
            disabled3 = false;
        }
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Dispositivo getDispositivoSelecionado() {
        return dispositivoSelecionado;
    }

    public void setDispositivoSelecionado(Dispositivo dispositivoSelecionado) {
        this.dispositivoSelecionado = dispositivoSelecionado;
    }

    public ArrayList<Dispositivo> getListarTodos() {
        return listarTodos;
    }

    public void setListarTodos(ArrayList<Dispositivo> listarTodos) {
        this.listarTodos = listarTodos;
    }

    public ArrayList<Dispositivo> getListarTodosFiltro() {
        return listarTodosFiltro;
    }

    public void setListarTodosFiltro(ArrayList<Dispositivo> listarTodosFiltro) {
        this.listarTodosFiltro = listarTodosFiltro;
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setPieModel2(PieChartModel pieModel2) {
        this.pieModel2 = pieModel2;
    }

    public PieChartModel getPieModel3() {
        return pieModel3;
    }

    public void setPieModel3(PieChartModel pieModel3) {
        this.pieModel3 = pieModel3;
    }

    public PieChartModel getPieModel4() {
        return pieModel4;
    }

    public void setPieModel4(PieChartModel pieModel4) {
        this.pieModel4 = pieModel4;
    }

    public int getTotal_dispositivo() {
        return total_dispositivo;
    }

    public void setTotal_dispositivo(int total_dispositivo) {
        this.total_dispositivo = total_dispositivo;
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

    public Boolean getDisabled4() {
        return disabled4;
    }

    public void setDisabled4(Boolean disabled4) {
        this.disabled4 = disabled4;
    }

    public Boolean getDisabled5() {
        return disabled5;
    }

    public void setDisabled5(Boolean disabled5) {
        this.disabled5 = disabled5;
    }

    public ArrayList<String> getListarMarca() {
        return listarMarca;
    }

    public void setListarMarca(ArrayList<String> listarMarca) {
        this.listarMarca = listarMarca;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }
}