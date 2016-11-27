package co.rcbike.alquiler;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;

import co.rcbike.alquiler.model.OperacionesAlquiler;
import co.rcbike.alquiler.model.SitioAlquilerWeb;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class AlquilerGateway extends RcbikeRestGateway {
	
	public static final GenericType<List<SitioAlquilerWeb>> TYPE_LIST = new GenericType<List<SitioAlquilerWeb>>() {
    };
    
    @Inject
    @Snoop(serviceName = OperacionesAlquiler.EP_ALQUILER)
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }
    
    public List<SitioAlquilerWeb> consultarSitios() {
        return webTarget().path("alquiler").path("sitiosAlquiler")
                .request().get(TYPE_LIST);
    }
}
