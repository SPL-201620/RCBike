package co.rcbike.autenticacion.strategy.facebook;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.autenticacion.model.ResultadoAutenticacion.EstadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.usuarios.service.UsuariosService;

@Stateless
public class AutenticacionFacebook extends AutenticacionStrategy {

    @Inject
    private UsuariosService usuarioService;

    private static final String FB_RS_ATTR_STATUS = "status";

    private static final String FB_RS_ATTR_AUTH_RESP = "authResponse";
    private static final String FB_RS_ATTR_TOKEN = "accessToken";
    private static final String FB_RS_ATTR_EXPIRES = "expiresIn";
    private static final String FB_RS_ATTR_SIGNED = "signedRequest";;
    private static final String FB_RS_ATTR_UID = "userID";

    private static final String AUTH_OK_VAL = "connected";
    private static final String AUTH_FAIL_VAL = "not_authorized";

    /**
     * @see <a href=
     *      "https://developers.facebook.com/docs/reference/javascript/FB.getLoginStatus#response_and_session_objects">
     *      Facebook Login Status</a>
     */

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public ResultadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion) {
        ResultadoAutenticacion retornoAutenticacion = new ResultadoAutenticacion();
        Map payload = (Map) valoresAutenticacion.get(ATTR_PAYLOAD);
        String authVal = (String) payload.get(FB_RS_ATTR_STATUS);

        switch (authVal) {
            case AUTH_OK_VAL :
                processAuthOk(retornoAutenticacion, (Map) payload.get(FB_RS_ATTR_AUTH_RESP));
                break;
            case AUTH_FAIL_VAL :
                retornoAutenticacion.setEstado(EstadoAutenticacion.CLAVE_ERRONEA);
                break;
            default :
                break;
        }
        return retornoAutenticacion;
    }

    private void processAuthOk(ResultadoAutenticacion retorno, Map<String, String> authResponse) {
        // picture?type=square&height=140
        String respLastName = "last_name";
        String respFirstName = "first_name";
        String respEmail = "email";
        Client cl = ClientBuilder.newClient();

        WebTarget mainEndpoint = cl.target("https://graph.facebook.com/v2.8").path(authResponse.get(FB_RS_ATTR_UID));
        WebTarget profileEP = mainEndpoint.queryParam("access_token", authResponse.get(FB_RS_ATTR_TOKEN))
                .queryParam("fields", "id,email,first_name,last_name");

        Map<String, Object> map = profileEP.request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<Map<String, Object>>() {
                });

        String emailFacebook = map.get(respEmail).toString();
        Usuario usuario = usuarioService.findUsuario(emailFacebook);
        if (usuario == null) {
            retorno.setNombresExternos(map.get(respFirstName).toString());
            retorno.setApellidosExternos(map.get(respLastName).toString());
            retorno.setEstado(EstadoAutenticacion.NO_EXISTE_USUARIO);
        } else {
            retorno.setEstado(EstadoAutenticacion.OK);
        }
        retorno.setEmail(emailFacebook);
    }
}
