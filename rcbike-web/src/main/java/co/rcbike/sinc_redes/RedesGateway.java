package co.rcbike.sinc_redes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

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

    public void compartirTwitter(String contenido) {

    }
}
