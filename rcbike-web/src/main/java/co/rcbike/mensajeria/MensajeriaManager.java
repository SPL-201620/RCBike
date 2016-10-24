package co.rcbike.mensajeria;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModMensajeria;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.mensajeria.model.Mensaje;
import co.rcbike.usuarios.UsuariosManager;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.web.util.UtilRest;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MensajeriaManager implements Serializable {

    @Getter
    @Setter
    private List<Usuario> conversaciones;

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

    @Getter
    @Setter
    @ManagedProperty(value = "#{usuariosManager}")
    private UsuariosManager usuariosManager;

    private GenericType<List<Mensaje>> gTListMensaje = new GenericType<List<Mensaje>>() {
    };

    @PostConstruct
    public void init() {
        listConversaciones();
    }

    public void listConversaciones() {
        List<String> emailReceptores = modulosManager.root(Modulo.mensajeria).path(ModMensajeria.ENDPNT_MENSAJERIA)
                .path("conversaciones").path(AutenticacionManager.emailAutenticado()).request()
                .get(UtilRest.TYPE_LIST_STRING);
        conversaciones = usuariosManager.buscarUsuariosByEmail(emailReceptores);
    }

    public void crearConversacionMensaje() {
        Mensaje nuevoMensaje = new Mensaje();
        Date fechaHora = new Date();
        nuevoMensaje.setContenido(mensaje);
        nuevoMensaje.setEmailEmisor(AutenticacionManager.emailAutenticado());
        nuevoMensaje.setEmailReceptor(amigoSelected);
        nuevoMensaje.setFechaHora(fechaHora);
        modulosManager.root(Modulo.mensajeria).path(ModMensajeria.ENDPNT_MENSAJERIA).request()
                .post(Entity.json(nuevoMensaje));
    }

}
