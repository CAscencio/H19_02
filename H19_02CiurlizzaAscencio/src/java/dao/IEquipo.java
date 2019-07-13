package dao;

import java.util.List;
import modelo.Equipo;

public interface IEquipo {

    void registrar(Equipo equipo) throws Exception;

    void modificar(Equipo equipo) throws Exception;

    void eliminar(Equipo equipo) throws Exception;

    List<Equipo> listarequipo() throws Exception;

}
