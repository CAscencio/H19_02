
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Login;


public class LoginDao extends Conexion{
    
    public Login startSession(String User, String Pass) throws Exception {
        this.conectar();
        ResultSet rs;
        Login login = null;
        try {
            String sql = "SELECT\n"
                    + "	P.CODPER AS CODPER,\n"
                    + "	CODUSU,\n"
                    + "	NOMPER,\n"
                    + "	APEPER,\n"
                    + "	TIPOPER,\n"
                    + "	S.CODSUC AS CODSUC,\n"
                    + "	NOMSUC \n"
                    + "FROM PERSONA.USUARIO\n"
                    + "	INNER JOIN PERSONA.PERSONA AS P\n"
                    + "	ON P.CODPER = USUARIO.CODPER\n"
                    + "	INNER JOIN PERSONA.SUCURSAL AS S\n"
                    + "	ON S.CODSUC = P.CODSUC\n"
                    + "	WHERE NOMUSU = ? and PASSUSU = ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, Pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                login = new Login();
                login.setCODPER(rs.getString("CODPER"));
                login.setCODUSU(rs.getString("CODUSU"));
                login.setNOMPER(rs.getString("NOMPER"));
                login.setAPEPER(rs.getString("APEPER"));
                login.setTIPPER(rs.getString("TIPOPER"));
                login.setCODSUC(rs.getString("CODSUC"));
                login.setNOMSUC(rs.getString("NOMSUC"));
                login.setUSUPER(User);
                login.setPASPER(Pass);              
            }
            return login;
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
