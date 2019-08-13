package graficos;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CantVSucursalImpl extends Conexion {

    public List<CantVSucursal> lstPrestamoDevolucion() throws SQLException, Exception {
        List<CantVSucursal> lista;
        ResultSet rs;
        try {

            String sql = "SELECT\n"
                    + "	COUNT(VT.CODVENT) AS CANTIDAD,\n"
                    + "	S.NOMSUC AS SUCURSAL\n"
                    + "FROM\n"
                    + "VENTA.VENTA AS VT\n"
                    + "INNER JOIN PERSONA.PERSONA AS P\n"
                    + "ON P.CODPER = VT.CODVEND\n"
                    + "INNER JOIN PERSONA.SUCURSAL AS S\n"
                    + "ON S.CODSUC = P.CODSUC\n"
                    + "GROUP BY S.NOMSUC";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            lista = new ArrayList();
            CantVSucursal pd;
            while (rs.next()) {
                pd = new CantVSucursal();
                pd.setSUCURSAL(rs.getString("SUCURSAL"));
                pd.setCANTIDAD(rs.getInt("CANTIDAD"));
                lista.add(pd);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;

    }

}
