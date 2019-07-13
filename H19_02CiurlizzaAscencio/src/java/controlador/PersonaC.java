
package controlador;

import dao.PersonaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Persona;


@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private Persona persona;
    private PersonaImpl daopersona;
    private List<Persona> lpersona;
    
    public PersonaC() {
        persona = new Persona(); 
        daopersona = new PersonaImpl();
        lpersona = new ArrayList();
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
            daopersona.registrar(persona);
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
            daopersona.modificar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificado completo",""));
        } catch (Exception e) {
            System.out.println("Error al registrar en C.");
            throw e;
        }
    }
    
    public void eliminar(Persona persona) throws Exception{
        try {
            daopersona.eliminar(persona);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrado completo",""));
        } catch (Exception e) {
            System.out.println("Error al registrar en C.");
            throw e;
        }
    }
    
    public void listar() throws Exception{
        try {
            lpersona = daopersona.listarpersona();
        } catch (Exception e) {
            System.out.println("Error al listar en C.");
            throw e;
        }
    }
    
    public void limpiar(){
        this.persona.setNOMPER(null);
        this.persona.setAPEPER(null);
        this.persona.setDNIPER(null);
        this.persona.setESTPER(null);
        this.persona.setDIRPER(null);
        this.persona.setSEXPER(null);
        this.persona.setCODSUC(null);
        this.persona.setTIPOPER(null);
        this.persona.setCELPER(null);
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public PersonaImpl getDaopersona() {
        return daopersona;
    }

    public void setDaopersona(PersonaImpl daopersona) {
        this.daopersona = daopersona;
    }

    public List<Persona> getLpersona() {
        return lpersona;
    }

    public void setLpersona(List<Persona> lpersona) {
        this.lpersona = lpersona;
    }
    
    
    
}
