package co.rcbike.desplazamientos.rest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.rcbike.desplazamientos.model.OperacionesDesplazamientos;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.WaypointWeb;
import co.rcbike.desplazamientos.service.DesplazamientosService;
import co.rcbike.desplazamientos.service.TransformadorDesplazamientos;

@Path("/individual")
@RequestScoped
public class DesplazamientoIndividualEndpoint {

	

	@Inject
	private DesplazamientosService service;

	@Inject
	private TransformadorDesplazamientos transformadorDesplazamientos;

	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.ALIVE)
	@Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
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
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.CLIMA)
	@Produces(MediaType.APPLICATION_JSON)
	public String getClima(@QueryParam("latitud") String latitud, @QueryParam("longitud") String longitud) {
		return service.obtenerClima(latitud, longitud);
	}

	/***** RUTA INDIVIDUAL ****/

	/**
	 * REST: GET,/rutaIndividual/{id} Lista todos los recorridos individuales
	 * por un id
	 * 
	 * @param id
	 *            Identificador de ruta
	 */
	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTA_INDIVIDUAL + OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.LCURL + OperacionesDesplazamientos.PARAM_ID + OperacionesDesplazamientos.RCURL)
	@Produces(MediaType.APPLICATION_JSON)
	public RutaWeb getRutaIndividual(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
		return transformadorDesplazamientos.toRutaWeb(service.getRuta(id));
	}

	/**
	 * REST: POST,/rutaIndividual, save one Permite guardar un recorrido
	 * individual
	 * 
	 * @param ruta
	 *            Informacion de la ruta a crear
	 * @return Identificador de ruta creada
	 */
	@POST
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTA_INDIVIDUAL)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long postRutaIndividual(RutaWeb ruta) {
    	System.out.println("---Ruta recibida: " + ruta.toString());
		return service.persistRuta(transformadorDesplazamientos.toRutaJpa(ruta));
	}

	/**
	 * REST: PUT,/rutaIndividual, update one Permite guardar un recorrido
	 * individual
	 * 
	 * @param ruta
	 *            Informacion de la ruta a crear
	 * @return Identificador de ruta creada
	 */
	@PUT
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTA_INDIVIDUAL)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long putRutaIndividual(RutaWeb ruta) {
		return service.mergeRuta(transformadorDesplazamientos.toRutaJpa(ruta));
	}

	/**
	 * REST: DELETE,/rutaIndividual/{id}, cancel one Lista todos los recorridos
	 * individuales por un id
	 * 
	 * @param id
	 *            Identificador de ruta
	 */
	@DELETE
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTA_INDIVIDUAL + OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.LCURL + OperacionesDesplazamientos.PARAM_ID + OperacionesDesplazamientos.RCURL)
	public void deleteRutaIndividual(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
		service.deleteRuta(id);
	}

	/***** RUTAS *****/

	/**
	 * REST: GET,/rutasIndividuales, list all Lista todos los recorridos
	 * individuales
	 * 
	 * @param emailCreador
	 * @param fechaInicio
	 * @param fechaFinal
	 * 
	 */
	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTAS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> getRutas(@QueryParam("emailCreador") String emailCreador,
			@QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFinal") String fechaFinal) {
		
		Date dateFechaInicio = null;
        Date dateFechaFinal = null;
		try {
			dateFechaInicio = OperacionesDesplazamientos.DATE_FORMAT.parse(fechaInicio);
	        dateFechaFinal = OperacionesDesplazamientos.DATE_FORMAT.parse(fechaFinal);
		} catch (ParseException e) {
			return null;
		}
		return transformadorDesplazamientos
				.toListRutaWeb(service.listRutasFechas(emailCreador, dateFechaInicio, dateFechaFinal));
	}

	/***** RUTAS INDIVIDUALES ****/

	/**
	 * REST: GET,/rutasIndividuales, list all Lista todos los recorridos
	 * individuales
	 * 
	 */
	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTAS_INDIVIDUALES)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> getRutasIndividuales() {
		return transformadorDesplazamientos.toListRutaWeb(service.listTodosRutasIndividuales());
	}

	/**
	 * REST: GET,/rutasIndividuales/{email}, list Lista todos los recorridos
	 * individuales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTAS_INDIVIDUALES + OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.POR_EMAIL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> getRutasIndividuales(@QueryParam(OperacionesDesplazamientos.PARAM_EMAIL_CREADOR) String emailCreador) {
		return transformadorDesplazamientos.toListRutaWeb(service.listRutasIndividuales(emailCreador));
	}

	/***** WAYPOINT *****/

	/**
	 * REST: GET,/waypoint/{id} Lista todos los recorridos individuales por un
	 * id
	 * 
	 * @param id
	 *            Identificador de waypoint
	 */
	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.WAYPOINT + OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.LCURL + OperacionesDesplazamientos.PARAM_ID + OperacionesDesplazamientos.RCURL)
	@Produces(MediaType.APPLICATION_JSON)
	public WaypointWeb getWaypoint(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
		return transformadorDesplazamientos.toWaypointWeb(service.getWaypoint(id));
	}

	/**
	 * REST: POST,/waypoint, save one Permite guardar un recorrido individual
	 * 
	 * @param waypoint
	 *            Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
	@POST
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.WAYPOINT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long postWaypoint(WaypointWeb waypoint) {
		return service.persistWaypoint(transformadorDesplazamientos.toWaypointJpa(waypoint));
	}

	/**
	 * REST: PUT,/waypoint, update one Permite guardar un recorrido individual
	 * 
	 * @param waypoint
	 *            Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
	@PUT
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.WAYPOINT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long putWaypoint(WaypointWeb waypoint) {
		return service.mergeWaypoint(transformadorDesplazamientos.toWaypointJpa(waypoint));
	}

	/**
	 * REST: DELETE,/waypoint/{id}, cancel one Lista todos los recorridos
	 * individuales por un id
	 * 
	 * @param id
	 *            Identificador de waypoint
	 */
	@DELETE
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.WAYPOINT + OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.LCURL + OperacionesDesplazamientos.PARAM_ID + OperacionesDesplazamientos.RCURL)
	public void deleteWaypoint(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
		service.deleteWaypoint(id);
	}

	/***** WAYPOINTS ****/

	/**
	 * REST: GET,/waypoints, list all Lista todos los recorridos individuales
	 * 
	 */
	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.WAYPOINTS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<WaypointWeb> getWaypoints() {
		return transformadorDesplazamientos.toListWaypointWeb(service.listTodosWaypoints());
	}

	/**
	 * REST: GET,/rutaIndividual/{id}/waypoints, list Lista todos los recorridos
	 * individuales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
	@GET
	@Path(OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.RUTA_INDIVIDUAL + OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.LCURL + OperacionesDesplazamientos.PARAM_ID + OperacionesDesplazamientos.RCURL + OperacionesDesplazamientos.PATH_DELIM + OperacionesDesplazamientos.WAYPOINTS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<WaypointWeb> getWaypoints(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
		return transformadorDesplazamientos.toListWaypointWeb(service.listWaypoints(id));
	}

}
