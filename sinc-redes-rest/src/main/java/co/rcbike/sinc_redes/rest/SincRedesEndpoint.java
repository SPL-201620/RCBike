package co.rcbike.sinc_redes.rest;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.rcbike.sinc_redes.model.CompartirTwitter;
import co.rcbike.sinc_redes.model.OperacionesSincronizacion;
import co.rcbike.sinc_redes.service.SincRedesService;
import twitter4j.Status;
import twitter4j.TwitterException;

@Path(OperacionesSincronizacion.EP_SINCRONIZACION)
@RequestScoped
public class SincRedesEndpoint {

    @Inject
    private SincRedesService service;

    /**
     * REST:
     * POST,/sincronizacion/publicarEnFacebook/userId=userId,accessToken=accessToken,message=message
     * 
     * Crea una entrada en el muro de Facebook
     * 
     * @param userId
     *            Identificador de usuario en Facebook
     * @param accessToken
     *            Identificador del token con permisos de Facebook
     * @param message
     *            Mensaje para colocar en el feed
     */
    @POST
    @Path(OperacionesSincronizacion.OP_PUBLICAR_EN_FACEBOOK)
    public Response publicarEnFacebook(@QueryParam(OperacionesSincronizacion.PARAM_USER_ID) String userId,
            @QueryParam(OperacionesSincronizacion.PARAM_ACCESS_TOKEN) String accessToken,
            @QueryParam(OperacionesSincronizacion.PARAM_MESSAGE) String message) {
        Map<String, Object> map = service.postOnFacebook(userId, accessToken, message);
        if (map.get("error") != null) {
            return Response.serverError().entity(
                    "Se produjo un error al intentar publicar en Facebook. " + ((Map) map.get("error")).get("message"))
                    .build();
        } else {
            return Response.ok(map, MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path(OperacionesSincronizacion.OP_PUBLICAR_EN_TWITTER)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response publicarEnTwitter(CompartirTwitter compartir) {
        try {
            Status status = service.postOnTwitter(compartir);
            return Response.ok(status, MediaType.APPLICATION_JSON).build();
        } catch (TwitterException e) {
            return Response.serverError().entity(e.getErrorCode() + " - " + e.getErrorMessage()).build();
        }
    }

}
