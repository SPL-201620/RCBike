package co.rcbike.alquiler;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import co.rcbike.alquiler.model.OperacionesAlquiler;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class AlquilerGateway extends RcbikeRestGateway {

    @Inject
    @Snoop(serviceName = OperacionesAlquiler.EP_ALQUILER)
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }
}
