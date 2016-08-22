package co.rcbike.ventas.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.ventas.data.VentasRepository;

@Path("/vender")
@RequestScoped
public class VentasEndpoint {

    @Inject
    private VentasRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllMembers() {
        return "Root Rest get";
    }

}
