package controlador;

import dao.ClienteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Cliente;

@Named(value = "clienteC")
@SessionScoped
public class ClienteC implements Serializable {

    private Cliente cliente;
    private ClienteImpl daocliente;
    private List<Cliente> lcliente;

    public ClienteC() {
        cliente = new Cliente();
        daocliente = new ClienteImpl();
        lcliente = new ArrayList();
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
            daocliente.registrar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado completo", ""));
        } catch (Exception e) {
            System.out.println("Error al registrar en C.");
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            daocliente.modificar(cliente);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado completo", ""));
        } catch (Exception e) {
            System.out.println("Error al modificar en C.");
            throw e;
        }
    }

    public void eliminar(Cliente cliente) throws Exception {
        try {
            daocliente.eliminar(cliente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado completo", ""));
        } catch (Exception e) {
            System.out.println("Error al eliminar en C.");
            throw e;
        }
    }

    public void listar() throws Exception {
        try {
            lcliente = daocliente.listarcliente();
        } catch (Exception e) {
            System.out.println("Error al listar en C.");
            throw e;
        }
    }

    public void limpiar() {
        try {
            this.cliente.setNOMCLI(null);
            this.cliente.setAPECLI(null);
            this.cliente.setDNICLI(null);
            this.cliente.setDIRCLI(null);
            this.cliente.setSEXCLI(null);
        } catch (Exception e) {
            System.out.println("Error al limpiar");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteImpl getDaocliente() {
        return daocliente;
    }

    public void setDaocliente(ClienteImpl daocliente) {
        this.daocliente = daocliente;
    }

    public List<Cliente> getLcliente() {
        return lcliente;
    }

    public void setLcliente(List<Cliente> lcliente) {
        this.lcliente = lcliente;
    }

}
