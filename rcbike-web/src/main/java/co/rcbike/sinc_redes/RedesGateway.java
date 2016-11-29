package co.rcbike.sinc_redes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import co.rcbike.sinc_redes.model.CompartirTwitter;
import co.rcbike.sinc_redes.model.OperacionesSincronizacion;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class RedesGateway extends RcbikeRestGateway {

    @Inject
    @Snoop(serviceName = "sinc_redes")
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }

    public Response compartirFacebook(String userId, String accessToken, String contenido) {
        return webTarget().path(OperacionesSincronizacion.EP_SINCRONIZACION)
                .path(OperacionesSincronizacion.OP_PUBLICAR_EN_FACEBOOK)
                .queryParam(OperacionesSincronizacion.PARAM_USER_ID, userId)
                .queryParam(OperacionesSincronizacion.PARAM_ACCESS_TOKEN, accessToken)
                .queryParam(OperacionesSincronizacion.PARAM_MESSAGE, contenido).request().post(Entity.json(""));
    }

    public Response compartirTwitter(CompartirTwitter compartir) {
        return webTarget().path(OperacionesSincronizacion.EP_SINCRONIZACION)
                .path(OperacionesSincronizacion.OP_PUBLICAR_EN_TWITTER).request().post(Entity.json(compartir));
    }
}