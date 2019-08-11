
package dao;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.annotation.PostConstruct;


@Named(value = "informacionImpl")
@SessionScoped
public class InformacionImpl extends  Conexion implements Serializable {

   
    public InformacionImpl() {
    }
    
    @PostConstruct
    public void iniciar(){
        try {
            productos();
            clientes();
            trabajadores();
            sucursales();
        } catch (Exception e) {
        }
    }
    
    public String productos(){
        String cantidad = "";
        String sql = "SELECT COUNT(CODEQUI) AS CANTIDAD FROM EQUIPO.EQUIPO WHERE ESTEQUI='A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            cantidad = rs.getString("CANTIDAD");
            }
            st.close();
            rs.close();
            return cantidad;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Producto "+e);
        }
        return "";
        
    }
    
    public String clientes(){
        String cantidad = "";
        String sql = "SELECT COUNT(CODPER) AS CANTIDAD FROM PERSONA.PERSONA WHERE TIPOPER = 'C' AND ESTPER = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            cantidad = rs.getString("CANTIDAD");
            }
            st.close();
            rs.close();
            return cantidad;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Clientes "+e);
        }
        return "";
        
    }
    
    public String trabajadores(){
        String cantidad = "";
        String sql = "SELECT COUNT(CODPER) AS CANTIDAD FROM PERSONA.PERSONA WHERE TIPOPER = 'V' AND ESTPER = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            cantidad = rs.getString("CANTIDAD");
            }
            st.close();
            rs.close();
            return cantidad;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Trabajadores (Vendedores) "+e);
        }
        return "";
        
    }
    
    public String sucursales(){
        String cantidad = "";
        String sql = "SELECT COUNT(CODSUC) AS CANTIDAD FROM PERSONA.SUCURSAL WHERE ESTSUC = 'A'";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            cantidad = rs.getString("CANTIDAD");
            }
            st.close();
            rs.close();
            return cantidad;
        } catch (Exception e) {
            System.out.println("Error al traer Cant. Sucursales "+e);
        }
        return "";
        
    }
}
