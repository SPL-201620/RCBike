package co.rcbike.mensajeria.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.mensajeria.data.MensajeriaRepository;

@Path("/mensajes")
@RequestScoped
public class MensajeriaEndpoint {

    @Inject
    private MensajeriaRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
    }

}
