package modelo;

/**
 *
 * @author Usuario
 */
public class VentaDetalle {

    private int CODVENTD;
    private int CODVENT;
    private String CODEQUI;
    private int CANTVENTD;

    public VentaDetalle() {
    }

    public VentaDetalle(int CODVENTD, int CODVENT, String CODEQUI, int CANTVENTD) {
        this.CODVENTD = CODVENTD;
        this.CODVENT = CODVENT;
        this.CODEQUI = CODEQUI;
        this.CANTVENTD = CANTVENTD;
    }

   
    
    public int getCODVENTD() {
        return CODVENTD;
    }

    public void setCODVENTD(int CODVENTD) {
        this.CODVENTD = CODVENTD;
    }

    public int getCODVENT() {
        return CODVENT;
    }

    public void setCODVENT(int CODVENT) {
        this.CODVENT = CODVENT;
    }

    public String getCODEQUI() {
        return CODEQUI;
    }

    public void setCODEQUI(String CODEQUI) {
        this.CODEQUI = CODEQUI;
    }

    public int getCANTVENTD() {
        return CANTVENTD;
    }

    public void setCANTVENTD(int CANTVENTD) {
        this.CANTVENTD = CANTVENTD;
    }

    
}
