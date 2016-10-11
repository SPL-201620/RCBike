package co.rcbike.mensajeria;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.TabChangeEvent;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModMensajeria;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.mensajeria.model.Mensaje;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
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
	private String amigoSelected;

	@Getter
	@Setter
	private String mensaje;

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

	private GenericType<List<Mensaje>> gTListMensaje = new GenericType<List<Mensaje>>() {
	};

	public void onTabChange(TabChangeEvent event) {
		amigoSelected = (event.getTab().getTitle());
		listMensaje();
	}

	public void listMensaje() {
		mensajes = modulosManager.root(Modulo.mensajeria)
				.path(ModMensajeria.ENDPNT_MENSAJERIA).path("mensaje")
				.path(AutenticacionManager.emailAutenticado())
				.path(amigoSelected).request().get(gTListMensaje);
	}

	public void crearConversacionMensaje() {
		Mensaje nuevoMensaje = new Mensaje();
		Date fechaHora = new Date();
		nuevoMensaje.setContenido(mensaje);
		nuevoMensaje.setEmailEmisor(AutenticacionManager.emailAutenticado());
		nuevoMensaje.setEmailReceptor(amigoSelected);
		nuevoMensaje.setFechaHora(fechaHora);
		modulosManager.root(Modulo.mensajeria)
				.path(ModMensajeria.ENDPNT_MENSAJERIA)
				.path("nuevo-mensaje-conversacion").request()
				.post(Entity.json(nuevoMensaje));
		listMensaje();
	}

}
