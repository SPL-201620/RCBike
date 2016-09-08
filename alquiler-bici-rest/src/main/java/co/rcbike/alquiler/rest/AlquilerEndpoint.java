package co.rcbike.alquiler.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.alquiler.model.Alquiler;
import co.rcbike.alquiler.service.AlquilerService;

@Path("/alquiler")
@RequestScoped
public class AlquilerEndpoint {

    @Inject
    private AlquilerService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alquiler> listAllMembers() {
        return service.listAlquiler();
    }

}
