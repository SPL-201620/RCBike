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

    public void compartirFacebook(String contenido) {

    }

    public Response compartirTwitter(CompartirTwitter compartir) {
        return webTarget().path(OperacionesSincronizacion.EP_SINCRONIZACION)
                .path(OperacionesSincronizacion.OP_PUBLICAR_EN_TWITTER).request().post(Entity.json(compartir));
    }
}