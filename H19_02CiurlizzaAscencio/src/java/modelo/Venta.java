package modelo;

/**
 *
 * @author Usuario
 */
public class Venta {

    private int CODVENT;
    private String CODVEND;
    private String CODCLI;
    private String FECHVENT;
    private float MONTVEND;

    public int getCODVENT() {
        return CODVENT;
    }

    public void setCODVENT(int CODVENT) {
        this.CODVENT = CODVENT;
    }

    public String getCODVEND() {
        return CODVEND;
    }

    public void setCODVEND(String CODVEND) {
        this.CODVEND = CODVEND;
    }

    public String getCODCLI() {
        return CODCLI;
    }

    public void setCODCLI(String CODCLI) {
        this.CODCLI = CODCLI;
    }

    public String getFECHVENT() {
        return FECHVENT;
    }

    public void setFECHVENT(String FECHVENT) {
        this.FECHVENT = FECHVENT;
    }

    public float getMONTVEND() {
        return MONTVEND;
    }

    public void setMONTVEND(float MONTVEND) {
        this.MONTVEND = MONTVEND;
    }
    
    
}
