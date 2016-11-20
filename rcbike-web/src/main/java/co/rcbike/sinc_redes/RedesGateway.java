package co.rcbike.sinc_redes;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@SuppressWarnings("serial")
@SessionScoped
public class RedesGateway implements Serializable {

    @Inject
    @Snoop(serviceName = "sinc_redes")
    private SnoopServiceClient sincRedesService;
}
