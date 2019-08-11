package controlador;

import dao.LoginImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Login;
import servicio.SessionUtils;

@Named(value = "loginC")
@SessionScoped
public class LoginC implements Serializable {

    private LoginImpl dao;
    private Login logn;

    //Variables de logeo
    private String user;
    private String pass;
    private String block;

    public LoginC() {
        dao = new LoginImpl();
        logn = new Login();
    }

    public void ingresar() throws Exception {
        System.out.println("asfsafsa");
        try {
            dao = new LoginImpl();
            logn = dao.startSession(user, pass);

            if (logn != null) {
//                intentos = 0;
//                block = "PF('bloc').hide()";
                System.out.println("Lllego al switch");
                switch (logn.getTIPPER()) {
                    case "A":
//                        setLogn(logn);
                        System.out.println("Llege para redireccionar");
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_02CiurlizzaAscencio/faces/vistas/Principal.xhtml");
                        break;
                }
            } else {
                pass = "";
                //intentos++;
                /*if (intentos == 3) {
                    block = "PF('bloc').show()";
                }*/
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña/Usuario Incorrecto"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void cerrarSesion() throws IOException {
        System.out.println("AQUI");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_02CiurlizzaAscencio/faces/vistas/Login.xhtml");
        System.out.println("AQUI");
    }

    public void securitySession() throws IOException {
        if (SessionUtils.ObtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_02CiurlizzaAscencio");
        }
    }

    public Login getLogn() {
        return (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }

    public void setLogn(Login logn) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", logn);
    }

    public LoginImpl getDao() {
        return dao;
    }

    public void setDao(LoginImpl dao) {
        this.dao = dao;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

}
