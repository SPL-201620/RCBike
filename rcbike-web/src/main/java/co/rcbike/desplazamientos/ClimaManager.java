package co.rcbike.desplazamientos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import eu.agilejava.snoop.client.SnoopServiceClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class ClimaManager implements Serializable {
	
	@Getter
    @Setter
    private String clima;
	
	@Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;
	

	@PostConstruct
    public void init() {
		java.lang.System.out.print(".-----ClimaManager\n");
		
		SnoopServiceClient desplazamientoRest = modulosManager.clienteSnoop(Modulo.desplazamientos);
        
		String response = desplazamientoRest.getServiceRoot().path("individual").path("obtenerClima").queryParam("latitud", "4.656360")
				.queryParam("longitud", "-74.103770").request().get(String.class);
        
		int inicio = response.indexOf("\"temp\":") + 7;
		int fin = response.indexOf(",\"pressure\"");
		
		int inicioDescripcion = response.indexOf("\"description\":\"") + 15;
		int finDescripcion = response.indexOf("\",\"icon\"");
		
		this.clima = response.substring(inicio, fin) +  " ºF, " + response.substring(inicioDescripcion, finDescripcion) + ", CO.";
        java.lang.System.out.print("\n");
        
        java.lang.System.out.print(clima.toString());
    }
}
