package co.rcbike.autenticacion.strategy.local;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.omnifaces.util.Messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

import co.rcbike.autenticacion.DatosAutenticacion;
import co.rcbike.autenticacion.DatosAutenticacion.EstadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
@JBossLog
public class AutenticacionLocal extends AutenticacionStrategy implements Serializable {

    private String email;

    private String clave;

    @Inject
    private AutenticacionGateway gateway;

    @Override
    public DatosAutenticacion autenticar(String contenidoAutenticacion) {
        return login(contenidoAutenticacion);
    }

    /**
     * 
     * @param contenidoAutenticacion
     *            {payload = { "email": "eee", "clave": "ccc" }}
     * @return
     */
    private DatosAutenticacion login(String contenidoAutenticacion) {
        DatosAutenticacion respuesta = new DatosAutenticacion();
        try {
            procesarCredenciales(contenidoAutenticacion);

            if (Strings.isNullOrEmpty(email) || Strings.isNullOrEmpty(clave)) {
                errorDatos(respuesta);
                return respuesta;
            }

            Response autenticar = gateway.autenticar(email, clave);
            respuesta.setEmail(email);
            respuesta.setRequiereClave(true);
            respuesta.setRequiereEmail(true);

            if (ok(autenticar)) {
                respuesta.setEstado(EstadoAutenticacion.OK);
            } else if (clave(autenticar)) {
                respuesta.setEstado(EstadoAutenticacion.CLAVE_ERRONEA);
            } else if (noExiste(autenticar)) {
                respuesta.setEstado(EstadoAutenticacion.NO_EXISTE_USUARIO);
            } else {
                errorDatos(respuesta);
            }
        } catch (IOException e) {
            log.debug("Error procesando autenticacion con Local", e);
            errorDatos(respuesta);
        }

        return respuesta;
    }

    private void errorDatos(DatosAutenticacion respuesta) {
        respuesta.setEstado(EstadoAutenticacion.ERROR);
        Messages.create("Informacion incompleta por favor ingrese Correo y Contraseña").error().add();
    }

    private boolean noExiste(Response autenticar) {
        return Status.fromStatusCode(autenticar.getStatus()).equals(Status.NOT_FOUND);
    }

    private boolean clave(Response autenticar) {
        return Status.fromStatusCode(autenticar.getStatus()).equals(Status.UNAUTHORIZED);
    }

    private boolean ok(Response autenticar) {
        return Status.fromStatusCode(autenticar.getStatus()).equals(Status.OK);
    }

    @SuppressWarnings("unchecked")
    private void procesarCredenciales(String contenidoAutenticacion) throws IOException {
        Map<String, String> credencialesLocal = (Map<String, String>) new ObjectMapper()
                .readValue(contenidoAutenticacion, Map.class).get(ATTR_PAYLOAD);
        email = credencialesLocal.get("email");
        clave = credencialesLocal.get("clave");
    }

    @Override
    public String nombreServicio() {
        return "Local";
    }
}
