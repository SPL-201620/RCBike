package co.rcbike.usuarios.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import co.rcbike.usuarios.model.Usuario;
import co.rcbike.usuarios.service.UsuariosService;

@Path("/usuarios")
@RequestScoped
public class UsuariosEndpoint {

    @Inject
    private UsuariosService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listAllMembers() {
        return service.listUsuario();
    }

    @GET
    @Path("/{email: .+@.+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario lookupUsuarioByEmail(@PathParam("email") String email) {
        Usuario member = service.findUsuario(email);
        if (member == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return member;
    }
    @GET
    @Path("/amigos/{email: .+@.+}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listAmigos(@PathParam("email") String email) {
        lookupUsuarioByEmail(email);
        return service.listAmigos(email);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearUsuario(Usuario usuario){
       try {
        service.crearUsuario(usuario);
    } catch (Exception e) {
        e.printStackTrace();
    }
       return Response.ok().build();
    }
    
}
