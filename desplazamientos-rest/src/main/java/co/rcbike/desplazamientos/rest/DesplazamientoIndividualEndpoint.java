package co.rcbike.desplazamientos.rest;

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

import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.WaypointWeb;
import co.rcbike.desplazamientos.service.DesplazamientosService;
import co.rcbike.desplazamientos.service.TransformadorDesplazamientos;

@Path("/individual")
@RequestScoped
public class DesplazamientoIndividualEndpoint {
	
	/** PARAMETROS REST **/

	//Separadores
	private static final String PATH_DELIM = "/";
	private static final String LCURL = "{";
	private static final String RCURL = "}";
	//Paths
    private static final String ALIVE = "alive";
    private static final String CLIMA = "clima";
    private static final String RUTAS_INDIVIDUALES = "rutasIndividuales";
    private static final String WAYPOINTS = "waypoints";
    private static final String RUTA_INDIVIDUAL = "rutaIndividual";
    private static final String WAYPOINT = "waypoint";
    //Operaciones
    private static final String POR_EMAIL = "porEmail";
    //Parametros
    private static final String PARAM_EMAIL_CREADOR = "emailCreador";
	private static final String PARAM_ID = "id";

	/** FIN PARAMETROS REST **/

	@Inject
    private DesplazamientosService service;
	
	@Inject
	private TransformadorDesplazamientos transformadorDesplazamientos;

    @GET
    @Path(PATH_DELIM + ALIVE)
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
	@Path(PATH_DELIM + CLIMA)
	@Produces(MediaType.APPLICATION_JSON)
	public String getClima(@QueryParam("latitud") String latitud,
			@QueryParam("longitud") String longitud) {
		return service.obtenerClima(latitud, longitud);
	}
	
	/***** RUTA INDIVIDUAL ****/
	
    /**
	 * REST: GET,/rutaIndividual/{id}
	 * Lista todos los recorridos individuales por un id
	 * 
	 * @param id Identificador de ruta
	 */
    @GET
    @Path(PATH_DELIM + RUTA_INDIVIDUAL + PATH_DELIM + LCURL + PARAM_ID + RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public RutaWeb getRutaIndividual(@PathParam(PARAM_ID) Long id) {
    	return transformadorDesplazamientos.toRutaWeb(service.getRuta(id));
	}

	/**
	 * REST: POST,/rutaIndividual, save one
	 * Permite guardar un recorrido individual
	 * 
	 * @param ruta Informacion de la ruta a crear
	 * @return Identificador de ruta creada
	 */
    @POST
    @Path(PATH_DELIM + RUTA_INDIVIDUAL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long postRutaIndividual(RutaWeb ruta) {
    	System.out.println("---Ruta recibida: " + ruta.toString());
    	return service.persistRuta(transformadorDesplazamientos.toRutaJpa(ruta));
	}

	/**
	 * REST: PUT,/rutaIndividual, update one
	 * Permite guardar un recorrido individual
	 * 
	 * @param ruta Informacion de la ruta a crear
	 * @return Identificador de ruta creada
	 */
    @PUT
    @Path(PATH_DELIM + RUTA_INDIVIDUAL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long putRutaIndividual(RutaWeb ruta) {
    	return service.mergeRuta(transformadorDesplazamientos.toRutaJpa(ruta));
	}


    /**
	 * REST: DELETE,/rutaIndividual/{id}, cancel one
	 * Lista todos los recorridos individuales por un id
	 * 
	 * @param id Identificador de ruta
	 */
    @DELETE
    @Path(PATH_DELIM + RUTA_INDIVIDUAL + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteRutaIndividual(@PathParam(PARAM_ID) Long id) {
    	service.deleteRuta(id);
	}

	/***** RUTAS INDIVIDUALES ****/
	
	/**
	 * REST: GET,/rutasIndividuales, list all 
	 * Lista todos los recorridos individuales 
	 * 
	 */
    @GET
    @Path(PATH_DELIM + RUTAS_INDIVIDUALES)
    @Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> getRutasIndividuales() {
    	return transformadorDesplazamientos.toListRutaWeb(service.listTodosRutasIndividuales());
	}

    /**
	 * REST: GET,/rutasIndividuales/{email}, list 
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
    @GET
    @Path(PATH_DELIM + RUTAS_INDIVIDUALES + PATH_DELIM + POR_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> getRutasIndividuales(@QueryParam(PARAM_EMAIL_CREADOR) String emailCreador) {
    	return transformadorDesplazamientos.toListRutaWeb(service.listRutasIndividuales(emailCreador));
	}

    /***** WAYPOINT *****/
    
    /**
	 * REST: GET,/waypoint/{id}
	 * Lista todos los recorridos individuales por un id
	 * 
	 * @param id Identificador de waypoint
	 */
    @GET
    @Path(PATH_DELIM + WAYPOINT + PATH_DELIM + LCURL + PARAM_ID + RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public WaypointWeb getWaypoint(@PathParam(PARAM_ID) Long id) {
    	return transformadorDesplazamientos.toWaypointWeb(service.getWaypoint(id));
	}

	/**
	 * REST: POST,/waypoint, save one
	 * Permite guardar un recorrido individual
	 * 
	 * @param waypoint Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
    @POST
    @Path(PATH_DELIM + WAYPOINT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long postWaypoint(WaypointWeb waypoint) {
    	return service.persistWaypoint(transformadorDesplazamientos.toWaypointJpa(waypoint));
	}

	/**
	 * REST: PUT,/waypoint, update one
	 * Permite guardar un recorrido individual
	 * 
	 * @param waypoint Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
    @PUT
    @Path(PATH_DELIM + WAYPOINT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long putWaypoint(WaypointWeb waypoint) {
    	return service.mergeWaypoint(transformadorDesplazamientos.toWaypointJpa(waypoint));
	}


    /**
	 * REST: DELETE,/waypoint/{id}, cancel one
	 * Lista todos los recorridos individuales por un id
	 * 
	 * @param id Identificador de waypoint
	 */
    @DELETE
    @Path(PATH_DELIM + WAYPOINT + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteWaypoint(@PathParam(PARAM_ID) Long id) {
    	service.deleteWaypoint(id);
	}

	/***** WAYPOINTS ****/
	
	/**
	 * REST: GET,/waypoints, list all 
	 * Lista todos los recorridos individuales 
	 * 
	 */
    @GET
    @Path(PATH_DELIM + WAYPOINTS)
    @Produces(MediaType.APPLICATION_JSON)
	public List<WaypointWeb> getWaypoints() {
    	return transformadorDesplazamientos.toListWaypointWeb(service.listTodosWaypoints());
	}

    /**
	 * REST: GET,/rutaIndividual/{id}/waypoints, list 
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
    @GET
    @Path(PATH_DELIM + RUTA_INDIVIDUAL + PATH_DELIM + LCURL + PARAM_ID + RCURL + PATH_DELIM + WAYPOINTS)
    @Produces(MediaType.APPLICATION_JSON)
	public List<WaypointWeb> getWaypoints(@PathParam(PARAM_ID) Long id) {
    	return transformadorDesplazamientos.toListWaypointWeb(service.listWaypoints(id));
	}

}
