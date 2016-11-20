package co.rcbike.alquiler;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import co.rcbike.alquiler.model.OperacionesAlquiler;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@SuppressWarnings("serial")
@SessionScoped
public class AlquilerGateway implements Serializable {
    @Inject
    @Snoop(serviceName = OperacionesAlquiler.EP_ALQUILER)
    private SnoopServiceClient alquilerService;
}
