package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;

public class PersonaImpl extends Conexion implements IPersona {

    @Override
    public void registrar(Persona persona) throws Exception {
        String sql = "INSERT INTO PERSONA.PERSONA (NOMPER,APEPER,DNIPER,TIPOPER,CODSUC,DIRPER,CELPER,SEXPER,ESTPER)\n"
                + "	VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDNIPER());
            ps.setString(4, persona.getTIPOPER());
            ps.setString(5, persona.getCODSUC());
            ps.setString(6, persona.getDIRPER());
            ps.setString(7, persona.getCELPER());
            ps.setString(8, persona.getSEXPER());
            ps.setString(9, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al registra en DAO " + e);
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        String sql = "UPDATE PERSONA.PERSONA SET NOMPER=?,APEPER=?,DNIPER=?,TIPOPER=?,CODSUC=?,DIRPER=?,CELPER=?,SEXPER=? WHERE CODPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getDNIPER());
            ps.setString(4, persona.getTIPOPER());
            ps.setString(5, persona.getCODSUC());
            ps.setString(6, persona.getDIRPER());
            ps.setString(7, persona.getCELPER());
            ps.setString(8, persona.getSEXPER());
            ps.setString(9, "A");
            ps.setInt(10, persona.getCODPER());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar en DAO " + e);
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Persona persona) throws Exception {
        String sql = "UPDATE PERSONA.PERSONA SET SEXPER=? WHERE CODPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, persona.getCODPER());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Eliminar en DAO " + e);
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Persona> listarpersona() throws Exception {
        List<Persona> listado;
        Persona persona;
        String sql = "SELECT * FROM PERSONA.PERSONA WHERE ESTPER='A'";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persona = new Persona();
                persona.setCODPER(rs.getInt("CODPER"));
                persona.setNOMPER(rs.getString("NOMPER"));
                persona.setAPEPER(rs.getString("APEPER"));
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setTIPOPER(rs.getString("TIPOPER"));
                persona.setCODSUC(rs.getString("CODSUC"));
                persona.setDIRPER(rs.getString("DIRPER"));
                persona.setCELPER(rs.getString("CELPER"));
                persona.setSEXPER(rs.getString("SEXPER"));
                listado.add(persona);
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
