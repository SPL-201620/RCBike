package co.rcbike.mensajeria;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;

import org.primefaces.event.TabChangeEvent;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModMensajeria;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.mensajeria.model.Mensaje;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class MensajeriaManager implements Serializable {

	@Getter
	@Setter
	private String email = (String) FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap()
			.get(AutenticacionManager.EMAIL_ATTR);

	@Getter
	@Setter
	private List<Mensaje> mensajes;

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

	private GenericType<List<Mensaje>> gTListMensaje = new GenericType<List<Mensaje>>() {
	};
	
	public void onTabChange(TabChangeEvent event) {
        listMensaje(event.getTab().getTitle());
    }

	public void listMensaje(String amigoSelected) {
		mensajes = modulosManager.root(Modulo.mensajeria)
				.path(ModMensajeria.ENDPNT_MENSAJERIA).path("conversacion")
				.path(AutenticacionManager.emailAutenticado())
				.path(amigoSelected).request().get(gTListMensaje);
	}

}