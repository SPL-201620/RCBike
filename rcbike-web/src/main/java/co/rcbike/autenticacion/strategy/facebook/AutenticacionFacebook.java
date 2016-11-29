package co.rcbike.autenticacion.strategy.facebook;

import java.io.IOException;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.rcbike.autenticacion.DatosAutenticacion;
import co.rcbike.autenticacion.DatosAutenticacion.EstadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;
import lombok.Getter;
import lombok.extern.jbosslog.JBossLog;
/**
 * @see <a href=
 *      "https://developers.facebook.com/docs/reference/javascript/FB.getLoginStatus#response_and_session_objects">
 *      Facebook Login Status</a>
 * @author Felipe
 *
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
@JBossLog
public class AutenticacionFacebook extends AutenticacionStrategy {

    @Getter
    private String userID;

    @Getter
    private String accessToken;

    private static final String FB_RS_ATTR_STATUS = "status";

    private static final String FB_RS_ATTR_AUTH_RESP = "authResponse";
    private static final String FB_RS_ATTR_TOKEN = "accessToken";
    // private static final String FB_RS_ATTR_EXPIRES = "expiresIn";
    // private static final String FB_RS_ATTR_SIGNED = "signedRequest";
    private static final String FB_RS_ATTR_UID = "userID";

    private static final String AUTH_OK_VAL = "connected";
    // private static final String AUTH_FAIL_VAL = "not_authorized";

    @Override
    public DatosAutenticacion autenticar(String contenidoAutenticacion) {
        return login(contenidoAutenticacion);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private DatosAutenticacion login(String contenidoAutenticacion) {
        DatosAutenticacion resp = new DatosAutenticacion();
        try {
            Map<String, Object> respFacebook = procesarRespuestaFacebook(contenidoAutenticacion);
            if (verficarRespuesta(respFacebook)) {
                resp = procesarAutenticacion((Map) respFacebook.get(FB_RS_ATTR_AUTH_RESP));
            } else {
                resp = buildErrorRespuesta(resp);
            }
        } catch (IOException e) {
            log.error("Error procesando autenticacion con Facebook", e);
            resp = buildErrorRespuesta(resp);
        }
        return resp;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> procesarRespuestaFacebook(String contenidoAutenticacion) throws IOException {
        Map<String, Object> respuestaFacebook = (Map<String, Object>) new ObjectMapper()
                .readValue(contenidoAutenticacion, Map.class).get(ATTR_PAYLOAD);
        return respuestaFacebook;
    }

    private boolean verficarRespuesta(Map<String, Object> valoresAutenticacion) {
        String authVal = (String) valoresAutenticacion.get(FB_RS_ATTR_STATUS);
        if (authVal.equals(AUTH_OK_VAL)) {
            return true;
        } else {
            return false;
        }
    }

    private DatosAutenticacion procesarAutenticacion(Map<String, String> authResponse) {
        DatosAutenticacion respuesta = new DatosAutenticacion();
        // picture?type=square&height=140
        String respLastName = "last_name";
        String respFirstName = "first_name";
        String respEmail = "email";
        Client cl = ClientBuilder.newClient();

        userID = authResponse.get(FB_RS_ATTR_UID);
        accessToken = authResponse.get(FB_RS_ATTR_TOKEN);

        WebTarget mainEndpoint = cl.target("https://graph.facebook.com/v2.8").path(userID);
        WebTarget profileEP = mainEndpoint.queryParam("access_token", accessToken).queryParam("fields",
                String.join(",", "id", respEmail, respFirstName, respLastName));

        Map<String, Object> map = profileEP.request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<Map<String, Object>>() {
                });

        String emailFacebook = map.get(respEmail).toString();
        respuesta.setRequiereClave(false);
        respuesta.setRequiereEmail(false);
        respuesta.setEmail(emailFacebook);
        respuesta.setNombres(map.get(respFirstName).toString());
        respuesta.setApellidos(map.get(respLastName).toString());

        if (usuarioExiste(emailFacebook)) {
            respuesta.setEstado(EstadoAutenticacion.OK);
        } else {
            respuesta.setEstado(EstadoAutenticacion.NO_EXISTE_USUARIO);
        }
        return respuesta;
    }

    @Override
    public String nombreServicio() {
        return "Facebook";
    }

}
