package servicio;

import controlador.LoginController;
import dao.Conexion;
import dao.EquipoImpl;
import dao.PersonaImpl;
import dao.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Equipo;
import modelo.Persona;
import modelo.Venta;
import modelo.VentaDetalle;

@Named(value = "controles")
@SessionScoped
public class Controles extends Conexion implements Serializable {
//Variable para tabla

    private String codEquipo, nombreE;
    private int cantidad, codVDet;
    private Double precio;

    private int CodigoBusca; //Codigo del Equipo a buscar
    private String Dni;

    private List<VentaDetalle> listaventadetalle;
    private VentaDetalle ventadetalle;

    private PersonaImpl daoPersona;
    private Persona persona;
    private String Datos;

    private Equipo equipo;
    private EquipoImpl daoEquipo;

//Instancia de Venta - VentaImpl (Para registar la cabecera)
    private Venta venta;
    private VentaImpl daoVenta;

    public Controles() {
        listaventadetalle = new ArrayList();
        ventadetalle = new VentaDetalle();
        daoPersona = new PersonaImpl();
        persona = new Persona();
        daoEquipo = new EquipoImpl();
    }

    public void registrarVenta() {
        venta = new Venta();
        daoVenta = new VentaImpl();
        LoginController codigo = new LoginController();
        try {
            venta.setCODVEND(String.valueOf(getMiccodigo()));
            venta.setCODCLI(String.valueOf(persona.getCODPER()));
            System.out.println("AVER A VER Q SALE XD");
            imprime();
            System.out.println("P1");
            venta.setMONTVEND((float) total());
            System.out.println("P2");
            daoVenta.registrar(venta);
            System.out.println("P3");
            registrarDetalle();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta Hecha", ":D"));
        } catch (Exception e) {
        }
    }

    //Método que agrega los Datos a la tabla - (Usa un constructor con párametros).)
    public void agregar() {
        codEquipo = String.valueOf(equipo.getCODEQUI());
        System.out.println("EQUIPO :" + getCodEquipo());
        nombreE = equipo.getNOMEQUI();
        System.out.println("NOMBRE_EQUIPO :" + getNombreE());
        precio = Double.parseDouble(equipo.getPRECEQUI());
        System.out.println("PRECIO_EQUIPO :" + getPrecio());
        try {
            listaventadetalle.add(new VentaDetalle(getCodEquipo(), getNombreE(), getCantidad(), getPrecio()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado", ":D"));
        } catch (Exception e) {
            System.out.println("Error en guardar en ArrayLsit :( " + e);
            throw e;
        }
    }

//Método que calcula el Total de los productos
    public double total() {
        try {
            double subtotal = 0.0;
            for (VentaDetalle ventaDetalle : listaventadetalle) {
                subtotal = subtotal + (ventaDetalle.getCANTVENTD() * ventaDetalle.getPRECIO());
            }
            return subtotal;
        } catch (Exception e) {
            System.out.println("Error al Obtener total :( " + e);
            return 0.0;
        }

    }

    //Registrar en la Base de Datos
    public void registrarDetalle() throws Exception {
        String codigo = CodigoV();
        String sql = "INSERT INTO VENTA.VENTA_DETALLE (CODVENT,CODEQUI,CANTVENTD,ESTVENTD) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            int contador = 0;
            for (VentaDetalle vd : listaventadetalle) {
                ps.setInt(1, Integer.parseInt(codigo));
                ps.setString(2, vd.getCODEQUI());
                ps.setInt(3, vd.getCANTVENTD());
                ps.setString(4, "A");
                contador += ps.executeUpdate();
            }
            ps.close();
            limpioTabla();
            codigo = "";
        } catch (Exception e) {
            System.out.println("Error al registar VDetalle en Dao " + e);
            throw e;
        } finally {
            this.cerrar();
        }
    }

    //Método para Traer el ultimo registro de la Venta
    public String CodigoV() {
        String codigo = "";
        String sql = "SELECT MAX(CODVENT) AS CODIGO FROM VENTA.VENTA";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                codigo = rs.getString("CODIGO");
            }
            return codigo;
        } catch (Exception e) {
        }
        return null;
    }

    //Buscar Equipo por CODEQUI , mostra el la vista
    public void buscarEquipo() {
        equipo = new Equipo();
        try {
            System.out.println("Esto recibo :" + CodigoBusca);
            equipo = daoEquipo.buscarEquipo(CodigoBusca);
            System.out.println("Esto Traigo :" + equipo.getCODEQUI());
            System.out.println("Esto Traigo :" + equipo.getNOMEQUI());
            System.out.println("Esto Traigo :" + equipo.getPRECEQUI());
            System.out.println("Esto Traigo :" + equipo.getCANTEQUI());
        } catch (Exception e) {
        }
    }

    //Buscar Clinete por su DNI y mostra Nombre y Apellidos
    public void buscarCliente() {
        persona = new Persona();
        try {
            System.out.println("Este es el DNI enviado :" + Dni);
            persona = daoPersona.buscarDatos(Dni);
            Datos = persona.getNOMPER() + " " + persona.getAPEPER();
            System.out.println("Esto devuelve :" + persona.getNOMPER() + " " + persona.getAPEPER());
        } catch (Exception e) {
            System.out.println("Error al Buscra el cliente :v " + e);
        }
    }

    private static int miccodigo;

    public int codigoR(int codigoV) {
        miccodigo = codigoV;
        imprime();
        return codigoV;
    }

    public void imprime() {
        System.out.println("FINAL :"+getMiccodigo());
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public EquipoImpl getDaoEquipo() {
        return daoEquipo;
    }

    public void setDaoEquipo(EquipoImpl daoEquipo) {
        this.daoEquipo = daoEquipo;
    }

    public String getDatos() {
        return Datos;
    }

    public void setDatos(String Datos) {
        this.Datos = Datos;
    }

    public int getCodigoBusca() {
        return CodigoBusca;
    }

    public void setCodigoBusca(int CodigoBusca) {
        this.CodigoBusca = CodigoBusca;
    }

    public PersonaImpl getDaoPersona() {
        return daoPersona;
    }

    public void setDaoPersona(PersonaImpl daoPersona) {
        this.daoPersona = daoPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public void limpioTabla() {
        listaventadetalle = new ArrayList();
    }

    public void delete(int index) {
        listaventadetalle.remove(index);
    }

    public String getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(String codEquipo) {
        this.codEquipo = codEquipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodVDet() {
        return codVDet;
    }

    public void setCodVDet(int codVDet) {
        this.codVDet = codVDet;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<VentaDetalle> getListaventadetalle() {
        return listaventadetalle;
    }

    public void setListaventadetalle(List<VentaDetalle> listaventadetalle) {
        this.listaventadetalle = listaventadetalle;
    }

    public VentaDetalle getVentadetalle() {
        return ventadetalle;
    }

    public void setVentadetalle(VentaDetalle ventadetalle) {
        this.ventadetalle = ventadetalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public VentaImpl getDaoVenta() {
        return daoVenta;
    }

    public void setDaoVenta(VentaImpl daoVenta) {
        this.daoVenta = daoVenta;
    }

    public int getMiccodigo() {
        return miccodigo;
    }


}
