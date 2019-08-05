
package dao;

import java.sql.PreparedStatement;
import java.util.List;
import modelo.Venta;


public class VentaImpl extends Conexion implements IVenta{

    @Override
    public void registrar(Venta venta) throws Exception {
        String sql ="INSERT INTO VENTA.VENTA (CODVEND,CODCLI,MONTVEND,ESTVENT) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, venta.getCODVEND());
            ps.setString(2, venta.getCODCLI());
            ps.setDouble(3, venta.getMONTVEND());
            ps.setString(4, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al guardar Venta en Dao "+e);
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void modificar(Venta venta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Venta venta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> listado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
