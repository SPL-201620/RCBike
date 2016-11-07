package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.desplazamientos.model.RutaWeb;
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
public class RutaGrupalManager implements Serializable {
	@Getter
    @Setter
    private List<RutaWeb> rutas;
	
	@Getter
    @Setter
    private String email;
	
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

	@SuppressWarnings("unchecked")
	@PostConstruct
    public void init() {
		java.lang.System.out.print(".-----inicioGrupales\n");

		this.email = autenticacionManager.getEmail();
		
		java.lang.System.out.print("El Email:" +this.email);
		
		SnoopServiceClient desplazamientoRest = modulosManager.clienteSnoop(Modulo.desplazamientos);
        
        rutas = desplazamientoRest.getServiceRoot().path("grupal").path("rutasGrupales").request().get(List.class);
        
        java.lang.System.out.print("\n");
        
        java.lang.System.out.print(rutas.toString());
    }
}