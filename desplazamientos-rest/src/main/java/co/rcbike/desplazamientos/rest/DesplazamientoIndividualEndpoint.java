package co.rcbike.desplazamientos.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.desplazamientos.model.WaypointRuta;
import co.rcbike.desplazamientos.service.DesplazamientosService;

@Path("/individual")
@RequestScoped
public class DesplazamientoIndividualEndpoint {

    @Inject
    private DesplazamientosService service;

    @GET
    @Path("/alive")
    @Produces(MediaType.APPLICATION_JSON)
    public String alive() {
        return "endpoint alive";
    }

    @GET
    @Path("/listAllWaypointRuta")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WaypointRuta> listAllWaypointRuta() {
        return service.listWaypointsRuta(new Long(1));
    }

}
