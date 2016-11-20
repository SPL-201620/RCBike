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

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.fasterxml.jackson.core.JsonProcessingException;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.mensajeria.model.Mensaje;
import co.rcbike.usuarios.UsuariosManager;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.web.socket.NotifyWebSocketClient;
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
    @ManagedProperty(value = "#{usuariosManager}")
    private UsuariosManager usuariosManager;

    @Inject
    private NotifyWebSocketClient websocketNotifier;

    @Inject
    private MensajeriaGateway gateway;

    @PostConstruct
    public void init() {
        listConversaciones();
    }

    public void listConversaciones() {
        List<String> emailReceptores = gateway.listConversaciones();
        conversaciones = usuariosManager.buscarUsuariosByEmail(emailReceptores);
    }

    public void seleccionarConversacion(Usuario conversacionSeleccionada) {
        this.conversacionSeleccionada = conversacionSeleccionada;
        mensajesConversacion = gateway.listMensajes(AutenticacionManager.emailAutenticado(),
                this.conversacionSeleccionada.getEmail());
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
        gateway.crearMensaje(nuevoMensaje);
        mensajesConversacion.add(nuevoMensaje);
        mensaje = "";
        websocketNotifier.notifyClient(receptor,
                new NotifyWebSocketClient.MessageWrapper<String>("Actualizar-Chat", ""));
    }

}
