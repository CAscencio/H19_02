
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import modelo.VentaDetalle;

/**
 *
 * @author Usuario
 */
public class VentaDetalleImpl extends Conexion implements IVentaDetalle{

    @Override
    public void registrar(VentaDetalle ventadetalle) throws Exception {
        String sql="INSERT INTO VENTA.VENTA_DETALLE (CODVENT,CODEQUI,CANTVENTD,ESTVENTD) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, ventadetalle.getCODVENT());
            ps.setString(2, ventadetalle.getCODEQUI());
            ps.setInt(3, ventadetalle.getCANTVENTD());
            ps.setString(4, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al registar VDetalle en Dao "+e);
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void modificar(VentaDetalle ventadetalle) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(VentaDetalle ventadetalle) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaDetalle> listado() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String CodigoV(){
        String codigo="";
        String sql="SELECT MAX(CODVENT) FROM VENTA.VENTA";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                codigo = rs.getString(1);
            }
            
        } catch (Exception e) {
        }
        return codigo;
    }
    
}
