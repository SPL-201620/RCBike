package co.rcbike.mensajeria.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.mensajeria.model.Mensaje;
import co.rcbike.mensajeria.service.MensajeriaService;

@Path("/mensajeria")
@RequestScoped
public class MensajeriaEndpoint {

	@Inject
	private MensajeriaService service;

	@GET
	@Path("/mensaje/{emailEmisor: .+@.+}/{emailReceptor: .+@.+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Mensaje> findConvesacionByEmRes(
			@PathParam("emailEmisor") String emailEmisor,
			@PathParam("emailReceptor") String emailReceptor) {
		return service.findMensajes(emailEmisor, emailReceptor);
	}

	@POST
	@Path("/nuevo-mensaje-conversacion")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void mensaje(Mensaje mensaje) {
		service.createMensaje(mensaje);
	}

}
