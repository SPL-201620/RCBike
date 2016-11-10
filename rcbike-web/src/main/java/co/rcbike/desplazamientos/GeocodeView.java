package co.rcbike.desplazamientos;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import eu.agilejava.snoop.client.SnoopServiceClient;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class GeocodeView {

    private MapModel geoModel;
    private String centerGeoMap = "4.656360,-74.103770";

    @Getter
    @Setter
    private List<RutaWeb> rutas;

    @Getter
    @Setter
    private RutaWeb ruta;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
    }

    @SuppressWarnings("unchecked")
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();

            // for (int i = 0; i < results.size(); i++) {
            GeocodeResult result = results.get(0);
            geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            // }
        }
        /// ---
        String lat = String.format("%7f", results.get(0).getLatLng().getLat()).replace(',', '.');

        String lng = String.format("%7f", results.get(0).getLatLng().getLng()).replace(',', '.');

        java.lang.System.out.println(".-----inicioGrupales\n");
        java.lang.System.out.println(lat + "--:--" + lng);

        SnoopServiceClient desplazamientoRest = modulosManager.clienteSnoop(Modulo.desplazamientos);

        rutas = desplazamientoRest.getServiceRoot().path("grupal").path("rutasGrupales").path("cercanos")
                .queryParam("latitud", lat).queryParam("longitud", lng).request().get(List.class);

        java.lang.System.out.println("Rutas:\n");

        java.lang.System.out.println(rutas.toString());
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }
}