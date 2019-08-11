
package controlador;

import dao.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;
import modelo.VentaDetalle;


@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    private Venta venta;
    private VentaImpl daoVenta;
    private List<VentaDetalle> listaVD;
    
    public VentaC() {
        venta = new Venta();
        daoVenta = new VentaImpl();
        listaVD = new ArrayList();
    }
    
    
    public void registrar() throws Exception, Exception{
        try {
            daoVenta.registrar(venta);
        } catch (Exception e) {
            System.out.println("Error al registrar Cabecera :( "+e);
            throw e;
        }
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public VentaImpl getDaoVenta() {
        return daoVenta;
    }

    public void setDaoVenta(VentaImpl daoVenta) {
        this.daoVenta = daoVenta;
    }

    public List<VentaDetalle> getListaVD() {
        return listaVD;
    }

    public void setListaVD(List<VentaDetalle> listaVD) {
        this.listaVD = listaVD;
    }
   
}
