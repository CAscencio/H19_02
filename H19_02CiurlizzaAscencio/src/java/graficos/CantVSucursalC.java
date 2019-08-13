package graficos;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;

@Named(value = "CantVSucursalC")
@SessionScoped

public class CantVSucursalC implements Serializable {

    private PieChartModel pieModel;
    private List<CantVSucursal> lstCantVSucursal;

    @PostConstruct
    public void init() {
        try {
            lstCantPresDev();
        } catch (Exception ex) {
//            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lstCantPresDev() throws Exception {
        try {
            CantVSucursalImpl dao = new CantVSucursalImpl();
            lstCantVSucursal = dao.lstPrestamoDevolucion();
            graficarCantPresDev(lstCantVSucursal);
        } catch (SQLException e) {
            throw e;
        }
    }

    //Graficar un grafico estadistico en forma de pie con la información)
    public void graficarCantPresDev(List<CantVSucursal> lista) {
        pieModel = new PieChartModel();
        for (CantVSucursal predev : lstCantVSucursal) {
            pieModel.set(predev.getSUCURSAL(), predev.getCANTIDAD());
        }
        pieModel.setTitle("Ventas por Sucursales");
        pieModel.setLegendPosition("ne");
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(200);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<CantVSucursal> getLstPresDev() {
        return lstCantVSucursal;
    }

    public void setLstPresDev(List<CantVSucursal> lstCantVSucursal) {
        this.lstCantVSucursal = lstCantVSucursal;
    }

}
