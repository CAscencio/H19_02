package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Sucursal;

public class SucursalImpl extends Conexion implements ISucursal {

    @Override
    public void registrar(Sucursal sucursal) throws Exception {
        String sql = "INSERT INTO PERSONA.SUCURSAL (NOMSUC,DIRSUC,ESTSUC) VALUES (?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, sucursal.getNOMSUC());
            ps.setString(2, sucursal.getDIRSUC());
            ps.setString(3, "A");
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
    public void modificar(Sucursal sucursal) throws Exception {
        String sql ="UPDATE PERSONA.SUCURSAL SET NOMSUC=?,DIRSUC=?,ESTSUC=? WHERE CODSUC=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, sucursal.getNOMSUC());
            ps.setString(2, sucursal.getDIRSUC());
            ps.setString(3, "A");
            ps.setInt(4, sucursal.getCODSUC());
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
    public void eliminar(Sucursal sucursal) throws Exception {
        String sql="UPDATE PERSONA.SUCURSAL SET ESTSUC=? WHERE CODSUC=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, sucursal.getCODSUC());
        } catch (Exception e) {
            System.out.println("Error al eliminar en DAO "+e);
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public List<Sucursal> listarsucursal() throws Exception {
        List<Sucursal> listado;
        Sucursal sucursal;
        String sql="SELECT * FROM PERSONA.SUCURSAL WHERE ESTSUC='A'";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                sucursal=new Sucursal();
                sucursal.setCODSUC(rs.getInt("CODSUC"));
                sucursal.setNOMSUC(rs.getString("NOMSUC"));
                sucursal.setDIRSUC(rs.getString("DIRSUC"));
                listado.add(sucursal);
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
