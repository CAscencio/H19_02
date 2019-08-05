package controlador;

import dao.EquipoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Equipo;

/**
 *
 * @author Usuario
 */
@Named(value = "equipoC")
@SessionScoped
public class EquipoC implements Serializable {
    
    private Equipo equipo;
    private EquipoImpl equipoDao;
    private List<Equipo> lequipo;
    
    public EquipoC() {
        equipo = new Equipo();
        equipoDao = new EquipoImpl();
        lequipo = new ArrayList();
    }
    
    @PostConstruct
    public void iniciar() {
        try {
            listar();
        } catch (Exception e) {
        }
    }
    
    public void registrar() throws Exception {
        try {
            equipoDao.registrar(equipo);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado con exito", ":D"));
        } catch (Exception e) {
            System.out.println("Error al registrar en C "+e);
            throw e;
        }
    }
    
    public void modificar() throws Exception {
        try {
            equipoDao.modificar(equipo);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado con exito", ":D"));
        } catch (Exception e) {
            System.out.println("Error al modificar en C "+e);
            throw e;
        }
        
    }
    
    public void eliminar(Equipo equipo) throws Exception {
        try {
            equipoDao.eliminar(equipo);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado con exito", ":D"));
        } catch (Exception e) {
            System.out.println("Error al eliminar en C "+e);
            throw e;
        }
    }
    
    public void listar() throws Exception {
        try {
            lequipo = equipoDao.listarequipo();
        } catch (Exception e) {
            System.out.println("Error al listar en C "+e);
            throw e;
        }
    }
    
    public void limpiar(){
        this.equipo.setNOMEQUI(null);
        this.equipo.setCATEQUI(null);
        this.equipo.setPRECEQUI(null);
        this.equipo.setCANTEQUI(null);
        
    }
    
    public Equipo getEquipo() {
        return equipo;
    }
    
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    public EquipoImpl getEquipoDao() {
        return equipoDao;
    }
    
    public void setEquipoDao(EquipoImpl equipoDao) {
        this.equipoDao = equipoDao;
    }
    
    public List<Equipo> getLequipo() {
        return lequipo;
    }
    
    public void setLequipo(List<Equipo> lequipo) {
        this.lequipo = lequipo;
    }
    
}
