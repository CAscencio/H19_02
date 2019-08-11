package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteImpl extends Conexion implements ICliente {

    @Override
    public void registrar(Cliente cliente) throws Exception {

        String sql = "INSERT INTO PERSONA.PERSONA (NOMPER,APEPER,DNIPER,DIRPER,SEXPER,CELPER,TIPOPER,ESTPER,CODSUC)\n"
                + "	VALUES (?,?,?,?,?,?,?,?,NULL)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cliente.getNOMCLI());
            ps.setString(2, cliente.getAPECLI());
            ps.setString(3, cliente.getDNICLI());
            ps.setString(4, cliente.getDIRCLI());
            ps.setString(5, cliente.getSEXCLI());
            ps.setString(6, "NULL");
            ps.setString(7, "C");
            ps.setString(8, "A");
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
    public void modificar(Cliente cliente) throws Exception {

        String sql = "UPDATE PERSONA.PERSONA SET NOMPER=?,APEPER=?,DNIPER=?,DIRPER=?,SEXPER=? WHERE CODPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, cliente.getNOMCLI());
            ps.setString(2, cliente.getAPECLI());
            ps.setString(3, cliente.getDNICLI());
            ps.setString(4, cliente.getDIRCLI());
            ps.setString(5, cliente.getSEXCLI());
            ps.setInt(6, cliente.getCODCLI());
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
    public void eliminar(Cliente cliente) throws Exception {

        String sql = "UPDATE PERSONA.PERSONA SET ESTPER=? WHERE CODPER=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, cliente.getCODCLI());
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
    public List<Cliente> listarcliente() throws Exception {

        List<Cliente> listado;
        Cliente cliente;
        String sql = "SELECT \n"
                + "	CODPER,\n"
                + "	NOMPER,\n"
                + "	APEPER,\n"
                + "	DNIPER,\n"
                + "	DIRPER,\n"
                + "	SEXPER \n"
                + "FROM PERSONA.PERSONA \n"
                + "	WHERE ESTPER='A' AND TIPOPER='C'";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setCODCLI(rs.getInt("CODPER"));
                cliente.setNOMCLI(rs.getString("NOMPER"));
                cliente.setAPECLI(rs.getString("APEPER"));
                cliente.setDNICLI(rs.getString("DNIPER"));
                cliente.setDIRCLI(rs.getString("DIRPER"));
                cliente.setSEXCLI(rs.getString("SEXPER"));
                listado.add(cliente);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error al listar en DAO");
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;
    }

}
