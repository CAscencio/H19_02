package dao;

import java.util.List;
import modelo.Sucursal;

public interface ISucursal {

    void registrar(Sucursal sucursal) throws Exception;

    void modificar(Sucursal sucursal) throws Exception;

    void eliminar(Sucursal sucursal) throws Exception;

    List<Sucursal> listarsucursal() throws Exception;

}
