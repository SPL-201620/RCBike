package co.rcbike.desplazamientos.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.desplazamientos.data.DesplazamientosRepository;

@Path("/grupal")
@RequestScoped
public class DesplazamientoGrupalEndpoint {

    @Inject
    private DesplazamientosRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllMembers() {
        return "Root Rest get";
    }

}
