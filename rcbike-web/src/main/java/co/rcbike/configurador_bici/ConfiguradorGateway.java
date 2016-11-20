package co.rcbike.configurador_bici;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@SuppressWarnings("serial")
@SessionScoped
public class ConfiguradorGateway implements Serializable {
    @Inject
    @Snoop(serviceName = "configurador")
    private SnoopServiceClient configuradorService;
}
