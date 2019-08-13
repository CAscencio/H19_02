package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;

public class EquipoImpl extends Conexion implements IEquipo {

    private Equipo equipo;

    @Override
    public void registrar(Equipo equipo) throws Exception {
        String sql = "INSERT INTO EQUIPO.EQUIPO (NOMEQUI,CODCAT,PRECEQUI,CANTEQUI,ESTEQUI) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, equipo.getNOMEQUI());
            ps.setString(2, equipo.getCODCAT());
            ps.setString(3, equipo.getPRECEQUI());
            ps.setString(4, equipo.getCANTEQUI());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al registrar en Dao " + e);
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Equipo equipo) throws Exception {
        String sql = "UPDATE EQUIPO.EQUIPO SET NOMEQUI=?,CODCAT=?,PRECEQUI=?,CANTEQUI=?,ESTEQUI=? WHERE CODEQUI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, equipo.getNOMEQUI());
            ps.setString(2, equipo.getCODCAT());
            ps.setString(3, equipo.getPRECEQUI());
            ps.setString(4, equipo.getCANTEQUI());
            ps.setString(5, "A");
            ps.setInt(6, equipo.getCODEQUI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar en Dao " + e);
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Equipo equipo) throws Exception {
        String sql = "UPDATE EQUIPO.EQUIPO SET ESTEQUI=? WHERE CODEQUI=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, equipo.getCODEQUI());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Eliminar en Dao " + e);
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Equipo> listarequipo() throws Exception {
        List<Equipo> listado;
        Equipo equipo;
        String sql = "SELECT * FROM EQUIPO.EQUIPO WHERE ESTEQUI='A'";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                equipo = new Equipo();
                equipo.setCODEQUI(rs.getInt("CODEQUI"));
                equipo.setNOMEQUI(rs.getString("NOMEQUI"));
                equipo.setCODCAT(rs.getString("CODCAT"));
                equipo.setPRECEQUI(rs.getString("PRECEQUI"));
                equipo.setCANTEQUI(rs.getString("CANTEQUI"));
                listado.add(equipo);
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

    public Equipo buscarEquipo(int codEquipo) {
        equipo = new Equipo();
        String sql = "SELECT CODEQUI,NOMEQUI,PRECEQUI,CANTEQUI FROM EQUIPO.EQUIPO WHERE CODEQUI="+codEquipo;
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                equipo.setCODEQUI(rs.getInt("CODEQUI"));
                equipo.setNOMEQUI(rs.getString("NOMEQUI"));
                equipo.setPRECEQUI(rs.getString("PRECEQUI"));
                equipo.setCANTEQUI(rs.getString("CANTEQUI"));
            }
            return equipo;
        } catch (Exception e) {
            System.out.println("Error al Buscar Cliente");
            return null;
        }

    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
