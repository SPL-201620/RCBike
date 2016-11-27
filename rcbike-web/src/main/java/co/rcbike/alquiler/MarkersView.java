package co.rcbike.alquiler;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import co.rcbike.alquiler.model.SitioAlquilerWeb;
 
@ManagedBean
@ViewScoped
public class MarkersView implements Serializable {
    
    private MapModel simpleModel;
    private Marker marker;
    
    @Inject
    private AlquilerGateway gateway;
  
    @PostConstruct
    public void init() {
        simpleModel = new DefaultMapModel();
        
        List<SitioAlquilerWeb> lista = gateway.consultarSitios();
        
        for(int i=0; i< lista.size(); i++)
        {
        	LatLng coord1 = new LatLng(lista.get(i).getLatitud().doubleValue(), lista.get(i).getLongitud().doubleValue());
        	simpleModel.addOverlay(new Marker(coord1, lista.get(i).getEstacionesEntrega(), "<span style='font-weight:bold'>Dirección: </span>" + lista.get(i).getLimiteRecorridos()
        			+ "<br/>" + "<span style='font-weight:bold'>Precio: </span>" + lista.get(i).getTarifas()
        			+ "<br/>" + "<span style='font-weight:bold'>Email de contacto: </span>" + lista.get(i).getEmailCreador()));
        	
        	Circle circle1 = new Circle(coord1, 200);
            circle1.setStrokeColor(getRandomColor());
            circle1.setFillColor(getRandomColor());
            circle1.setFillOpacity(0.5);
            simpleModel.addOverlay(circle1);
        }
    }
  
    public MapModel getSimpleModel() {
        return simpleModel;
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
      
    public Marker getMarker() {
        return marker;
    }
    
    /**
     * Gets the random color.
     *
     * @return the random color
     */
     private static String getRandomColor() {
          String[] letters = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
          String color = "#";
          for (int i = 0; i < 6; i++ ) {
             color += letters[(int) Math.round(Math.random() * 15)];
          }
          return color;
     }
}