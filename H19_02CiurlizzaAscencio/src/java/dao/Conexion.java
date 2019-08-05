
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    Connection cnx = null;
    
    public Connection conectar(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnx = DriverManager.getConnection("jdbc:sqlserver://DJ;databaseName=VENTAS_HACKHATON","Admin","Admin");
        } catch (Exception e) {
            System.out.println("Error :'v");
        }
        return cnx;
    }
    
    public void cerrar() throws SQLException{
        if(cnx != null){
            cnx.close();
        }
    }
    
    
    public static void main(String[] args) {
        Conexion cn = new Conexion();
        cn.conectar();
        if(cn != null){
            System.out.println("Conectado");
        }else{
            System.out.println("Error de Conexion");
        }
    }
    
}
