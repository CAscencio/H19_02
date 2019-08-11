package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Login;

public class LoginImpl extends Conexion {

    public Login startSession(String User, String Pass) {
        try {
            Login login = null;
            ResultSet rs;
            String sql = "SELECT\n"
                    + "	P.CODPER AS CODPER,\n"
                    + "	CODUSU,\n"
                    + "	NOMPER,\n"
                    + "	APEPER,\n"
                    + "	TIPOPER\n"
                    + "FROM PERSONA.USUARIO\n"
                    + "	INNER JOIN PERSONA.PERSONA AS P\n"
                    + "	ON P.CODPER = USUARIO.CODPER\n"
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
                login.setUSUPER(User);
                login.setPASPER(Pass);
            }
            return login;
        } catch (Exception e) {
            System.out.println("ERROR EN LoginImpl : " + e.getMessage());
            return null;
        }
    }

}
