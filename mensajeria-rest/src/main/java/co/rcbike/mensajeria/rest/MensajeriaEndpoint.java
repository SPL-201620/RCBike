package co.rcbike.mensajeria.rest;

import java.util.List;

import co.rcbike.mensajeria.model.Conversacion;
import co.rcbike.mensajeria.model.Mensaje;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.mensajeria.service.MensajeriaService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

@Path("/mensajeria")
@RequestScoped
public class MensajeriaEndpoint {

	@Inject
	private MensajeriaService service;

	@GET
	@Path("/conversacion/{emailEmisor: .+@.+}/{emailReceptor: .+@.+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Mensaje> findConvesacionByEmRes(
			@PathParam("emailEmisor") String emailEmisor,
			@PathParam("emailReceptor") String emailReceptor) {
		return service.findConvesacionByEmRes(emailEmisor, emailReceptor);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Conversacion create(Conversacion record) {
		return service.create(record);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Mensaje create(Mensaje record) {
		return service.create(record);
	}

}
