package co.rcbike.sinc_redes.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.sinc_redes.service.SincRedesService;

@Path("/sincronizacion")
@RequestScoped
public class SincRedesEndpoint {

    @Inject
    private SincRedesService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String alive() {
        return "endpoint alive";
    }

}
