package co.rcbike.autenticacion.strategy;

import java.io.Serializable;

import javax.inject.Inject;
import javax.ws.rs.core.Response.Status;

import org.omnifaces.util.Messages;

import co.rcbike.autenticacion.DatosAutenticacion;
import co.rcbike.autenticacion.DatosAutenticacion.EstadoAutenticacion;
import co.rcbike.usuarios.UsuariosGateway;
import lombok.Getter;

@SuppressWarnings("serial")
public abstract class AutenticacionStrategy implements Serializable {

    @Getter
    public static final String ATTR_PAYLOAD = "payload";

    @Inject
    private UsuariosGateway usuariosGateway;

    public abstract DatosAutenticacion autenticar(String contenidoAutenticacion);
    public abstract String nombreServicio();

    public boolean usuarioExiste(String email) {
        if (usuariosGateway.requestUsuario(email).getStatus() == Status.OK.getStatusCode()) {
            return true;
        } else {
            return false;
        }
    }

    protected DatosAutenticacion buildErrorRespuesta(DatosAutenticacion respuesta) {
        respuesta.setEstado(EstadoAutenticacion.ERROR);
        Messages.create("No fue posible autenticarse con " + nombreServicio()).error().add();
        return respuesta;
    }
}
