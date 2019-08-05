package dao;

import java.util.List;
import modelo.VentaDetalle;

public interface IVentaDetalle {

    void registrar(VentaDetalle ventadetalle) throws Exception;

    void modificar(VentaDetalle ventadetalle) throws Exception;

    void eliminar(VentaDetalle ventadetalle) throws Exception;
    
    List<VentaDetalle> listado() throws Exception;

}
