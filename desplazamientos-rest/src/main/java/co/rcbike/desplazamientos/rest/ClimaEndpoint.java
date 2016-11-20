package co.rcbike.desplazamientos.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.rcbike.desplazamientos.service.ClimaService;

@Path("/clima")
@RequestScoped
public class ClimaEndpoint {

    @Inject
    private ClimaService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getClima(@QueryParam("latitud") String latitud, @QueryParam("longitud") String longitud) {
        return service.obtenerClima(latitud, longitud);
    }
}
