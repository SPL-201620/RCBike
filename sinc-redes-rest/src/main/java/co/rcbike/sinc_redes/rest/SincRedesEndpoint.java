package co.rcbike.sinc_redes.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.sinc_redes.data.SincRedesRepository;

@Path("/sincronizacion")
@RequestScoped
public class SincRedesEndpoint {

    @Inject
    private SincRedesRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
    }

}
