package co.rcbike.desplazamientos.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.desplazamientos.model.Ruta;
import co.rcbike.desplazamientos.model.Waypoint;
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
    public List<Waypoint> listAllWaypointRuta() {
        return service.listWaypointsRuta(new Long(1));
    }
    
	/**
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
    @GET
    @Path("/listViajesIndividuales")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Ruta> listViajesIndividuales(String emailCreador) {
    	return service.listViajesIndividuales(emailCreador);
	}

	/**
	 * Permite guardar un recorrido individual
	 * 
	 * @param ruta
	 *            informacion de la ruta a crear
	 */
    @GET
    @Path("/guardarViaje")
    @Produces(MediaType.APPLICATION_JSON)
	public void guardarViaje(Ruta ruta) {
    	service.guardarViaje(ruta);
	}

    

}
