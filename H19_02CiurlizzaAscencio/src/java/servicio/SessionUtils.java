package servicio;

import javax.faces.context.FacesContext;
import modelo.Login;

public class SessionUtils {
    
    public static Login ObtenerObjetoSesion() {
        return (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }
    
    public static String ObTenerNombreSession() {
        Login us = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getNOMPER();
        } else {
            return null;
        }
    }
    
    public static String ObtenerCodigoSesion() {
        Login us = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        if (us != null) {
            return us.getCODUSU();
        } else {
            return null;
        }
    }
}
