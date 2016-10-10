package co.rcbike.desplazamientos;

import java.io.Serializable;
import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
  
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import lombok.Getter;
import lombok.Setter;
 
@ManagedBean
public class AddMarkersView implements Serializable {
     
	private static final long serialVersionUID = 1L;

	@Getter
    @Setter
	private MapModel emptyModel;
	
	@Getter
    @Setter
    private String title;
    
	@Getter
    @Setter
    private double lat;
    
	@Getter
    @Setter
    private double lng;
  
    @PostConstruct
    public void init() {
    	this.title = "";
        emptyModel = new DefaultMapModel();
    }
            
    public void addMarker(int markersLength) {
    	if(markersLength < 2)
    	{
	        Marker marker = new Marker(new LatLng(lat, lng), title);
	        emptyModel.addOverlay(marker);
	          
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marcador Agredado", "Latitud:" + lat + ", Longitud:" + lng));
        }
    }
}