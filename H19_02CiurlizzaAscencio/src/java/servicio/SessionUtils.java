package Servicio;

import modelo.Login;
import javax.faces.context.FacesContext;

public class SessionUtils {

    public static Login obtenerObjetoSesion() {
        return (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }

    public static String ObtenerNombreSesion() {
        Login us = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        return us != null ? us.getNOMPER() : null;
    }

    public static String ObtenerCodigoSesion() {
        Login us = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        return us != null ? us.getCODPER() : null;
    }

}
