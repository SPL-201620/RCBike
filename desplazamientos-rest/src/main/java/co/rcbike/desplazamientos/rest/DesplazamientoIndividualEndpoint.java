package co.rcbike.desplazamientos.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.TransformadorDesplazamientos;
import co.rcbike.desplazamientos.model.WaypointWeb;
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

	/**
	 * Lista todos los recorridos individuales 
	 * 
	 */
    @GET
    @Path("/listTodosViajesIndividuales")
    @Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> listTodosViajesIndividuales() {
    	return TransformadorDesplazamientos.toListRutaWeb(service.listTodosViajesIndividuales());
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
	public List<RutaWeb> listViajesIndividuales(@QueryParam("emailCreador") String emailCreador) {
    	return TransformadorDesplazamientos.toListRutaWeb(service.listViajesIndividuales(emailCreador));
	}

	/**
	 * Permite guardar un recorrido individual
	 * 
	 * @param ruta
	 *            informacion de la ruta a crear
	 */
    @POST
    @Path("/guardarViaje")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long guardarViaje(RutaWeb ruta) {
    	return service.guardarViaje(TransformadorDesplazamientos.toRutaJpa(ruta));
	}

	/**
	 * Permite obtener el clima en una latitud y longitud
	 * 
	 * @param latitud
	 *            latitud geografica de la ruta
	 * @param longitud
	 *            longitud geografica de la ruta
	 */
	@GET
	@Path("/obtenerClima")
	@Produces(MediaType.APPLICATION_JSON)
	public String obtenerClima(@QueryParam("latitud") String latitud,
			@QueryParam("longitud") String longitud) {
		return service.obtenerClima(latitud, longitud);
	}
	
    /**
     * Lista todos los Waypoints existentes
     * 
     */
	@GET
	@Path("/listTodosWaypoints")
	@Produces(MediaType.APPLICATION_JSON)
    public List<WaypointWeb> listTodosWaypoints() {
        return TransformadorDesplazamientos.toListWaypointWeb(service.listTodosWaypoints());
    }

    /**
     * Lista todos las waypoints de una ruta
     * 
     * @param idRuta identificador de la ruta
     */
	@POST
	@Path("/listWaypoints")
	@Produces(MediaType.APPLICATION_JSON)
    public List<WaypointWeb> listWaypoints(Long idRuta) {
        return TransformadorDesplazamientos.toListWaypointWeb(service.listWaypoints(idRuta));
    }

    /**
     * Permite guardar un waypoint de una ruta
     * 
     * @param WaypointWeb Informacion del WaypointWeb a crear
     */
	@POST
	@Path("/guardarWaypoint")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    public Long guardarWaypoint(WaypointWeb waypoint) {
        return service.guardarWaypoint(TransformadorDesplazamientos.toWaypointJpa(waypoint));
    }

}
