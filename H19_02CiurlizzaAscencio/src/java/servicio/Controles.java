
package servicio;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import modelo.VentaDetalle;


@Named(value = "controles")
@SessionScoped
public class Controles implements Serializable {

    String codVDet,codVenta,codEquipo;
    int cantidad;
    private List<VentaDetalle> datosArray;
    public Controles() {
    }
    
}
