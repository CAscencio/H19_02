package controlador;

import dao.CategoriaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Categoria;

@Named(value = "categoriaC")
@SessionScoped
public class CategoriaC implements Serializable {

    private Categoria categoria;
    private CategoriaImpl daocategoria;
    private List<Categoria> listcategoria;

    public CategoriaC() {
        categoria = new Categoria();
        daocategoria = new CategoriaImpl();
        listcategoria = new ArrayList();
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
            daocategoria.registrar(categoria);
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
            daocategoria.modificar(categoria);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Modificado completo",""));
        } catch (Exception e) {
            System.out.println("Error al registrar en C.");
            throw e;
        }
    }
    
    public void eliminar(Categoria categoria) throws Exception{
        try {
            daocategoria.eliminar(categoria);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminado completo",""));
        } catch (Exception e) {
            System.out.println("Error al eliminar en C.");
            throw e;
        }
    }
    
    public void listar() throws Exception{
        try {
            listcategoria = daocategoria.listarCategoria();
        } catch (Exception e) {
            System.out.println("Error al listar en C.");
            throw e;
        }
    }

    
    public void limpiar(){
        this.categoria.setNOMCAT(null);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CategoriaImpl getDaocategoria() {
        return daocategoria;
    }

    public void setDaocategoria(CategoriaImpl daocategoria) {
        this.daocategoria = daocategoria;
    }

    public List<Categoria> getListcategoria() {
        return listcategoria;
    }

    public void setListcategoria(List<Categoria> listcategoria) {
        this.listcategoria = listcategoria;
    }
    
    

}
