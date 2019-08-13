
package dao;

import java.util.List;
import modelo.Categoria;


public interface ICategoria {
    
    void registrar(Categoria categoria) throws Exception;

    void modificar(Categoria categoria) throws Exception;

    void eliminar(Categoria categoria) throws Exception;

    List<Categoria> listarCategoria() throws Exception;
    
}
