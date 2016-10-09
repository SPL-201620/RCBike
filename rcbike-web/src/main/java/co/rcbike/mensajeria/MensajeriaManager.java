package co.rcbike.mensajeria;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.Usuario;
import eu.agilejava.snoop.client.SnoopServiceClient;
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
    private Usuario amigoSelected;
	
	@Getter
    @Setter
	private String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AutenticacionManager.EMAIL_ATTR);
	

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public List<Usuario> getAmigos() {        
        SnoopServiceClient usuariosRest = modulosManager.clienteSnoop(Modulo.usuarios);        
        return usuariosRest.getServiceRoot().path("usuarios").path("amigos").path(email).request().get(List.class);        
    }
 }