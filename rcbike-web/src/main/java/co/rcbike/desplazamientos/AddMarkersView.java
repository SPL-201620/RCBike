package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
  
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import eu.agilejava.snoop.client.SnoopServiceClient;
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
    
    /**
     * Metodo unirse al recorrido.
     * @param id de la ruta.
     */
    public void unirse(long id) {
		java.lang.System.out.print("------addMarker---unirse\n");

		String email = autenticacionManager.getEmail();
		
		java.lang.System.out.print("El Email: " +email);
		java.lang.System.out.print("ID: " +id);
		
		SnoopServiceClient desplazamientoRest = modulosManager.clienteSnoop(Modulo.desplazamientos);
        
        desplazamientoRest.getServiceRoot().path("grupal").path("guardarParticipante").queryParam("idRuta", id).queryParam("email", email).request().get();
        
        java.lang.System.out.print("\n");
        
        //FacesContext context = FacesContext.getCurrentInstance();
        
        //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias!", "Te has unido al Recorrido."));
    }
}