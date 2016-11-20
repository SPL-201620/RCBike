package co.rcbike.desplazamientos;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@SuppressWarnings("serial")
@SessionScoped
public class DesplazamientosGateway implements Serializable {
    @Inject
    @Snoop(serviceName = "desplazamientos")
    private SnoopServiceClient desplazamientosService;
}
