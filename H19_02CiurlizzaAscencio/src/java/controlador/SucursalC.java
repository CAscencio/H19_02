package controlador;

import dao.SucursalImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Sucursal;

@Named(value = "sucursalC")
@SessionScoped
public class SucursalC implements Serializable {

    private Sucursal sucursal;
    private SucursalImpl daosucursal;
    private List<Sucursal> lsucursal;

    public SucursalC() {
        sucursal = new Sucursal();
        daosucursal = new SucursalImpl();
        lsucursal = new ArrayList();
    }
    
    @PostConstruct
    public void iniciar(){
        try {
            listar();
        } catch (Exception e) {
        }
    }
    
    public void registrar() throws Exception{
        try {
            daosucursal.registrar(sucursal);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado completo",""));
        } catch (Exception e) {
            System.out.println("Error al registrar en C.");
            throw e;
        }
    }
    
    public void modificar() throws Exception{
        try {
            daosucursal.modificar(sucursal);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificado completo",""));
        } catch (Exception e) {
            System.out.println("Error al registrar en C.");
            throw e;
        }
    }
    
    public void eliminar(Sucursal sucursal) throws Exception{
        try {
            daosucursal.eliminar(sucursal);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado completo",""));
        } catch (Exception e) {
            System.out.println("Error al eliminar en C.");
            throw e;
        }
    }
    
    public void listar() throws Exception{
        try {
            lsucursal = daosucursal.listarsucursal();
        } catch (Exception e) {
            System.out.println("Error al listar en C.");
            throw e;
        }
    }

    
    public void limpiar(){
        this.sucursal.setNOMSUC(null);
        this.sucursal.setDIRSUC(null);
    }
    
    
    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public SucursalImpl getDaosucursal() {
        return daosucursal;
    }

    public void setDaosucursal(SucursalImpl daosucursal) {
        this.daosucursal = daosucursal;
    }

    public List<Sucursal> getLsucursal() {
        return lsucursal;
    }

    public void setLsucursal(List<Sucursal> lsucursal) {
        this.lsucursal = lsucursal;
    }

    
    
    
}
