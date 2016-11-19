package co.rcbike.sinc_redes.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;

import co.rcbike.sinc_redes.service.SincRedesService;

@Path("/sincronizacion")
@RequestScoped
public class SincRedesEndpoint {

    @Inject
    private SincRedesService service;

}
