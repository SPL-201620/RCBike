package co.rcbike.usuarios.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.rcbike.usuarios.data.UsuariosRepository;
import co.rcbike.usuarios.model.Usuario;

@Path("/perfil")
@RequestScoped
public class UsuariosEndpoint {

    @Inject
    private UsuariosRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listAllMembers() {
		return repository.findAllOrderedByName();
    }

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario lookupUsuarioById(@PathParam("id") long id) {
		Usuario member = repository.findById(id);
		if (member == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return member;
	}

	@GET
	@Path("/{email:^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario lookupUsuarioByEmail(@PathParam("email") String email) {
		Usuario member = repository.findByEmail(email);
		if (member == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return member;
	}
}
