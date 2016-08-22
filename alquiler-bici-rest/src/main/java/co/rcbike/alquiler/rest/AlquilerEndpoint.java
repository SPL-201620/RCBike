package co.rcbike.alquiler.rest;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.alquiler.data.AlquilerRepository;
import co.rcbike.alquiler.model.Alquiler;

@Path("/sitios")
@RequestScoped
public class AlquilerEndpoint {

    @Inject
    private AlquilerRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alquiler> listAllMembers() {
        return Collections.emptyList();
    }

}
