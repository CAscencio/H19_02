package dao;

import java.util.List;
import modelo.Venta;

public interface IVenta {

    void registrar(Venta venta) throws Exception;

    void modificar(Venta venta) throws Exception;

    void eliminar(Venta venta) throws Exception;

    List<Venta> listado() throws Exception;

}
