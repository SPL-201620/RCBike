package co.rcbike.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.primefaces.event.FileUploadEvent;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.web.util.Navegacion;
import co.rcbike.web.util.Navegacion.Views;
import eu.agilejava.snoop.client.SnoopServiceClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class PerfilUsuarioManager implements Serializable {

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    private List<Usuario> amigos; 
    
    @Getter
    @Setter
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        String email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get(AutenticacionManager.EMAIL_ATTR);
        SnoopServiceClient usuariosRest = modulosManager.clienteSnoop(Modulo.usuarios);
        usuario = usuariosRest.getServiceRoot().path("usuarios").path(email).request().get(Usuario.class);
        amigos = usuariosRest.getServiceRoot().path("usuarios").path("amigos").path(email).request().get(List.class);
    }

}
