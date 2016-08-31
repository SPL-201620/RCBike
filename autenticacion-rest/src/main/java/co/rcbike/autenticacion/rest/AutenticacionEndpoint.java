package co.rcbike.autenticacion.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import co.rcbike.autenticacion.service.AutenticacionService;

@Path("/autenticar")
@RequestScoped
public class AutenticacionEndpoint {

    @Inject
	private AutenticacionService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response autenticacionLocal(
			@QueryParam("email") String email, @QueryParam("clave") String clave) {
		ResponseBuilder resp;
		switch (service.autenticar(email, clave)) {
		case CLAVE_ERRONEA:
			resp = Response.status(Response.Status.UNAUTHORIZED).entity(email);
			break;
		case NO_EXISTE_USUARIO:
			resp = Response.status(Response.Status.NOT_FOUND).entity(email);
			break;
		case OK:
			resp = Response.ok(email);
			break;
		default:
			resp = Response.serverError();
			break;
		}
		return resp.build();
    }

}
