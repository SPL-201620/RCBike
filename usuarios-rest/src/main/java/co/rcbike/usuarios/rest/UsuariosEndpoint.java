package co.rcbike.usuarios.rest;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.usuarios.model.RegistroUsuario;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.usuarios.service.UsuariosService;

@Path(OperacionesUsuarios.EP_USUARIOS)
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
    @Path(OperacionesUsuarios.OP_USUARIOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listUsuariosFiltro(@QueryParam("filtro") String filtro) {
        return service.listUsuariosFiltro(filtro);
    }

    @GET
    @Path(OperacionesUsuarios.OP_USUARIOS + "/{emails}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listUsuarios(@PathParam("emails") String emails) {
        return service.listUsuarios(Arrays.asList(emails.split(Pattern.quote("|"))));
    }

    @GET
    @Path(OperacionesUsuarios.OP_USUARIO + "/" + OperacionesUsuarios.PATH_PRM_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario lookupUsuarioByEmail(@PathParam("email") String email) {
        Usuario member = service.findUsuario(email);
        if (member == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return member;
    }

    @GET
    @Path(OperacionesUsuarios.OP_AMIGOS + "/" + OperacionesUsuarios.PATH_PRM_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listAmigos(@PathParam("email") String email) {
        lookupUsuarioByEmail(email);
        return service.listAmigos(email);
    }

    @GET
    @Path(OperacionesUsuarios.OP_NOAMIGOS + "/" + OperacionesUsuarios.PATH_PRM_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listNoAmigos(@PathParam("email") String email, @QueryParam("filtro") String filtro) {
        return service.listNoAmigosDe(email, filtro);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response crearUsuario(RegistroUsuario registroUsuario) {
        try {
            service.crearUsuario(registroUsuario);
        } catch (Exception e) {
            Response.status(Status.BAD_REQUEST).entity("El usuario ya existe en el sistema");
        }
        return Response.ok().build();
    }

    @POST
    @Path(OperacionesUsuarios.OP_AGREGAR_AMIGO)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response agregarAmigo(List<String> emails) {
        service.agregarAmigo(emails.get(0), emails.get(1));
        return Response.ok().build();
    }

    @POST
    @Path(OperacionesUsuarios.OP_REMOVER_AMIGO)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response removerAmigo(List<String> emails) {
        service.removerAmigo(emails.get(0), emails.get(1));
        return Response.ok().build();
    }

}
