package controlador;

import Servicio.SessionUtils;
import dao.LoginDao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Login;
import servicio.Controles;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    //Objeto de Sesion
    private Login login = new Login();

    //Variables de Logeo
    private String User;
    private String Pass;
    private String Codigo;

    public LoginController() {
    }

    public void startSession() throws Exception {
        LoginDao dao;
        try {
            dao = new LoginDao();
            login = dao.startSession(User, Pass);
            Codigo = login.getCODPER();
            Controles control = new Controles();
            control.codigoR(Integer.parseInt(Codigo));
            if (login != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", login);
                switch (login.getTIPPER()) {
                    case "A":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_02CiurlizzaAscencio/faces/vistas/Principal.xhtml");
                        break;
                }
            } else {
                setPass(null);
                login = new Login();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña / Usuario Incorrecto", ""));
            }
        } catch (Exception e) {
            throw e;
        }

    }

//    public int codvendedor() {
//        try {
//            System.out.println(" EN EL METODO PARA TRAER :"+login.getCODPER());
//            return codigo = Integer.parseInt(login.getCODPER());
//        } catch (Exception e) {
//            System.out.println("Error al traer CODIGO VENDEDOR");
//        }
//        return 0;
//    }
    public void finishSession() throws IOException {
        System.out.println(" P 1");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(); //Cierra la Session
        System.out.println("P 2");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_02CiurlizzaAscencio/"); // Mandamos al Login
        System.out.println("P 3");
    }

    public void securityLogin() throws IOException {
        Login us = SessionUtils.obtenerObjetoSesion();
        if (us != null) {
            switch (us.getTIPPER()) {
                case "A":
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_02CiurlizzaAscencio/faces/vistas/Principal.xhtml");
                    break;
            }
        }
    }

    public void securitySession() throws IOException {
        if (SessionUtils.obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/H19_02CiurlizzaAscencio/faces/vistas/Login.xhtml");
        }
    }

    public void obtenerDatos() {
        System.out.println(SessionUtils.ObtenerCodigoSesion());
        System.out.println(SessionUtils.ObtenerNombreSesion());
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

}
