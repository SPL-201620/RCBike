package co.rcbike.mensajeria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModMensajeria;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.mensajeria.model.Mensaje;
import co.rcbike.mensajeria.model.OperacionesMensajeria;
import co.rcbike.usuarios.UsuariosManager;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.web.socket.NotifyWebSocketClient;
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
    private Usuario conversacionSeleccionada;

    @Getter
    @Setter
    private List<Mensaje> mensajesConversacion;

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

    @Inject
    private NotifyWebSocketClient websocketNotifier;

    private GenericType<List<Mensaje>> gTListMensaje = new GenericType<List<Mensaje>>() {
    };

    @PostConstruct
    public void init() {
        listConversaciones();
    }

    public void listConversaciones() {
        List<String> emailReceptores = modulosManager.root(Modulo.mensajeria).path(OperacionesMensajeria.EP_MENSAJERIA)
                .path(OperacionesMensajeria.OP_CONVERSACIONES).path(AutenticacionManager.emailAutenticado()).request()
                .get(UtilRest.TYPE_LIST_STRING);
        conversaciones = usuariosManager.buscarUsuariosByEmail(emailReceptores);
    }

    public void seleccionarConversacion(Usuario conversacionSeleccionada) {
        this.conversacionSeleccionada = conversacionSeleccionada;
        mensajesConversacion = modulosManager.root(Modulo.mensajeria).path(OperacionesMensajeria.EP_MENSAJERIA)
                .path(OperacionesMensajeria.OP_MENSAJE).path(AutenticacionManager.emailAutenticado())
                .path(conversacionSeleccionada.getEmail()).request().get(gTListMensaje);

    }

    public void actualizarConversacionActual() {
        seleccionarConversacion(conversacionSeleccionada);
    }

    public void onNuevaConversacionSelect(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        if (conversaciones.contains(usuario)) {
            seleccionarConversacion(usuario);
        } else {
            conversaciones.add(0, usuario);
            conversacionSeleccionada = usuario;
            mensajesConversacion = new ArrayList<>();
        }
        RequestContext.getCurrentInstance().execute("marcarConvByIdClass('" + usuario.getId() + "');");
    }

    public void crearMensaje() throws JsonProcessingException {
        String receptor = conversacionSeleccionada.getEmail();
        Mensaje nuevoMensaje = new Mensaje();
        nuevoMensaje.setEmailEmisor(AutenticacionManager.emailAutenticado());
        nuevoMensaje.setEmailReceptor(receptor);
        nuevoMensaje.setContenido(mensaje);
        nuevoMensaje.setFechaHora(new Date());
        modulosManager.root(Modulo.mensajeria).path(ModMensajeria.ENDPNT_MENSAJERIA).request()
                .post(Entity.json(nuevoMensaje));
        mensajesConversacion.add(nuevoMensaje);
        mensaje = "";
        websocketNotifier.notifyClient(receptor,
                new NotifyWebSocketClient.MessageWrapper<String>("Actualizar-Chat", ""));
    }

}
