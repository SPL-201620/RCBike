package co.rcbike.usuarios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

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
public class RegistroManager implements Serializable {

    @Getter
    @Setter
    @NotNull
    private String email;

    @Getter
    @Setter
    @NotNull
    private String clave;

    @Getter
    @Setter
    @NotNull
    private String nombres;

    @Getter
    @Setter
    @NotNull
    private String apellidos;

    @Getter
    @Setter
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @PostConstruct
    public void init() {
        this.email = autenticacionManager.getEmail();
        this.clave = autenticacionManager.getClave();
        autenticacionManager.setClave(null);

    }

    public String registrar() {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);

        SnoopServiceClient usuariosRest = modulosManager.clienteSnoop(Modulo.usuarios);
        Response response = usuariosRest.getServiceRoot().path("usuarios").request().post(Entity.json(usuario));
        log.debug(response);
        autenticacionManager.setUsuario(usuario);
        return Navegacion.redirectView(Views.dashboard);
    }
}
