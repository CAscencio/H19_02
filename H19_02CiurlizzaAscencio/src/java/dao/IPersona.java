package dao;

import java.util.List;
import modelo.Persona;

public interface IPersona {

    void registrar(Persona persona) throws Exception;

    void modificar(Persona persona) throws Exception;

    void eliminar(Persona persona) throws Exception;

    List<Persona> listarpersona() throws Exception;

}
