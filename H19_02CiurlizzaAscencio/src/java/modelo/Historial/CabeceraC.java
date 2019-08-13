package modelo.Historial;

import dao.Conexion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Usuario
 */
@Named(value = "cabeceraC")
@SessionScoped
public class CabeceraC extends Conexion implements Serializable {

    private List<Cabecera> listCabecera;

    private List<Detalle> listDetalle;

    private Cabecera cabecera;
    private Detalle detalle;

    public CabeceraC() {
        listCabecera = new ArrayList();
        cabecera = new Cabecera();
        detalle = new Detalle();
        listDetalle = new ArrayList();
    }

    @PostConstruct
    public void iniciar() {
        try {
            listar();
        } catch (Exception e) {
        }
    }

    public void listar() throws Exception {
        try {
            listCabecera = listarCabecera();
        } catch (Exception e) {
            System.out.println("Error al listar en C " + e);
            throw e;
        }
    }

    public void MostrarDetalle(Cabecera cabecera) throws Exception {
        try {
            listDetalle = mostrarDetalle(cabecera.getCODIGO());
        } catch (Exception e) {
            System.out.println("Error al listar en C " + e);
            throw e;
        }
    }

    public List<Cabecera> listarCabecera() throws Exception {
        List<Cabecera> listado;
        Cabecera cabecera;
        String sql = "SELECT \n"
                + "	CODVENT AS CODIGO,\n"
                + "	CONCAT(V.NOMPER,' ',V.APEPER) AS VENDEDOR,\n"
                + "	CONCAT(C.NOMPER,' ',C.APEPER) AS CLIENTE,\n"
                + "	CONVERT(VARCHAR(10),FECHVENT,103) AS FECHA,\n"
                + "	MONTVEND AS MONTO\n"
                + "FROM VENTA.VENTA AS VT\n"
                + "	INNER JOIN PERSONA.PERSONA AS V\n"
                + "	 ON V.CODPER = VT.CODVEND\n"
                + "	 INNER JOIN PERSONA.PERSONA AS C\n"
                + "	 ON C.CODPER = VT.CODCLI\n"
                + "	WHERE ESTVENT='A'";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cabecera = new Cabecera();
                cabecera.setCODIGO(rs.getString("CODIGO"));
                cabecera.setVENDEDOR(rs.getString("VENDEDOR"));
                cabecera.setCLIENTE(rs.getString("CLIENTE"));
                cabecera.setFECHA(rs.getString("FECHA"));
                cabecera.setMONTO(rs.getString("MONTO"));
                listado.add(cabecera);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al listar en Dao " + e);
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<Detalle> mostrarDetalle(String codigo) throws Exception {
        List<Detalle> listado;
        Detalle detalleM;
        ResultSet rs;
        String sql = "SELECT\n"
                + "	ROW_NUMBER() OVER(ORDER BY NOMEQUI ASC) AS NUMERO,\n"
                + "	NOMEQUI AS EQUIPO,\n"
                + "	PRECEQUI AS PRECIO,\n"
                + "	CANTVENTD AS CANTIDAD,\n"
                + "	(PRECEQUI * CANTVENTD)AS IMPORTE\n"
                + "FROM VENTA.VENTA_DETALLE AS VT\n"
                + "	INNER JOIN EQUIPO.EQUIPO AS E\n"
                + "	ON E.CODEQUI = VT.CODEQUI\n"
                + "	WHERE CODVENT=?";
        try {
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                detalleM = new Detalle();
                detalleM.setNUMERO(rs.getString("NUMERO"));
                detalleM.setEQUIPO(rs.getString("EQUIPO"));
                detalleM.setPRECIO(rs.getString("PRECIO"));
                detalleM.setCANTIDAD(rs.getString("CANTIDAD"));
                detalleM.setIMPORTE(rs.getString("IMPORTE"));
                listado.add(detalleM);
            }
            ps.close();
            rs.close();
            return listado;
        } catch (Exception e) {
            System.out.println("Error al listar en Dao " + e);
            throw e;
        } finally {
            this.cerrar();
        }

    }

    public List<Detalle> getListDetalle() {
        return listDetalle;
    }

    public void setListDetalle(List<Detalle> listDetalle) {
        this.listDetalle = listDetalle;
    }

    
    
    public List<Cabecera> getListCabecera() {
        return listCabecera;
    }

    public void setListCabecera(List<Cabecera> listCabecera) {
        this.listCabecera = listCabecera;
    }

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

}
