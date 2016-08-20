package co.rcbike.autenticacion.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.autenticacion.data.AutenticacionRepository;
import co.rcbike.autenticacion.model.AutenticacionUsuario;

@Path("/autenticar")
@RequestScoped
public class AutenticacionEndpoint {

    @Inject
    private AutenticacionRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AutenticacionUsuario> listAllMembers() {
        return repository.findAll();
    }

}
