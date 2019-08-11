package modelo;


public class VentaDetalle {

    private int CODVENTD;
    private int CODVENT;
    private String CODEQUI;
    private int CANTVENTD;
    private Double PRECIO;
    
    private String NOMBREP;

    public VentaDetalle() {
    }

    public VentaDetalle(String CODEQUI,String NOMBREP, int CANTVENTD, Double PRECIO) {
       this.NOMBREP = NOMBREP;
        this.CODEQUI = CODEQUI;
        this.CANTVENTD = CANTVENTD;
        this.PRECIO = PRECIO;
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

    public Double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(Double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public String getNOMBREP() {
        return NOMBREP;
    }

    public void setNOMBREP(String NOMBREP) {
        this.NOMBREP = NOMBREP;
    }

    
    
}
