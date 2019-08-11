
package dao;

import java.util.List;
import modelo.Cliente;


public interface ICliente {
    
     void registrar(Cliente cliente) throws Exception;

    void modificar(Cliente cliente) throws Exception;

    void eliminar(Cliente cliente) throws Exception;

    List<Cliente> listarcliente() throws Exception;
    
}
