package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import co.rcbike.desplazamientos.model.RutaWeb;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
@ApplicationScoped
public class GeocodeView implements Serializable{

    private MapModel geoModel;
    private String centerGeoMap = "4.656360,-74.103770";

    @Getter
    @Setter
    private List<RutaWeb> rutas;

    @Getter
    @Setter
    private RutaWeb ruta;

    @Inject
    private DesplazamientosGateway gateway;

    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        rutas = new ArrayList<RutaWeb>();
    }

    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();

        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();

            GeocodeResult result = results.get(0);
            geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
        }

        String lat = String.format("%7f", results.get(0).getLatLng().getLat()).replace(',', '.');
        String lng = String.format("%7f", results.get(0).getLatLng().getLng()).replace(',', '.');

        java.lang.System.out.println(".-----inicioGrupales\n" + lat + "--:--" + lng);

        rutas = gateway.listGruposCercanos(lat, lng);

        java.lang.System.out.println("Rutas:\n" + rutas.toString());
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }
}