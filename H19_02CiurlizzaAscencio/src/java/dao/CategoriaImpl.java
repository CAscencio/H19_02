
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;


public class CategoriaImpl extends Conexion implements ICategoria{

    @Override
    public void registrar(Categoria categoria) throws Exception {
        String sql = "INSERT INTO EQUIPO.CATEGORIA (NOMCAT,ESTCAT) VALUES (?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNOMCAT());
            ps.setString(2, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al registrar en DAO "+e);
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void modificar(Categoria categoria) throws Exception {
        String sql ="UPDATE EQUIPO.CATEGORIA SET NOMCAT=?,ESTCAT=? WHERE CODCAT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, categoria.getNOMCAT());
            ps.setString(2, "A");
            ps.setString(3, categoria.getCODCAT());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modifcar "+e);
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Categoria categoria) throws Exception {
String sql="UPDATE EQUIPO.CATEGORIA SET ESTCAT=? WHERE CODCAT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setString(2, categoria.getCODCAT());
        } catch (Exception e) {
            System.out.println("Error al eliminar en DAO "+e);
            throw e;
        }finally{
            this.cerrar();
        }    }

    @Override
    public List<Categoria> listarCategoria() throws Exception {
        List<Categoria> listado;
        Categoria categoria;
        String sql="SELECT * FROM EQUIPO.CATEGORIA WHERE ESTCAT='A'";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                categoria = new Categoria();
                categoria.setCODCAT(rs.getString("CODCAT"));
                categoria.setNOMCAT(rs.getString("NOMCAT"));
                listado.add(categoria);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al listar en DAO");
            throw e;
        }finally{
            this.cerrar();
        }
        return listado;

    }
    
}
