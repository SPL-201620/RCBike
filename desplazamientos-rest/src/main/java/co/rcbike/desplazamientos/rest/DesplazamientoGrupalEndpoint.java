package co.rcbike.desplazamientos.rest;

import java.math.BigDecimal;
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

import co.rcbike.desplazamientos.model.ParticipanteWeb;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.WaypointWeb;
import co.rcbike.desplazamientos.service.DesplazamientosService;
import co.rcbike.desplazamientos.service.TransformadorDesplazamientos;

@Path("/grupal")
@RequestScoped
public class DesplazamientoGrupalEndpoint {

	/** PARAMETROS REST **/

	//Separadores
	private static final String PATH_DELIM = "/";
	private static final String LCURL = "{";
	private static final String RCURL = "}";
	//Paths
    private static final String ALIVE = "alive";
    private static final String CLIMA = "clima";
	private static final String RUTA_GRUPAL = "rutaGrupal";
    private static final String RUTAS_GRUPALES = "rutasGrupales";
    private static final String WAYPOINT = "waypoint";
    private static final String WAYPOINTS = "waypoints";
    private static final String PARTICIPANTE = "participante";
    private static final String PARTICIPANTES = "participantes";
    //Operaciones
    private static final String CERCANOS = "cercanos";
    private static final String NO_VENCIDOS = "noVencidos";
    //Parametros
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
	
	/***** RUTA GRUPAL ****/
	
    /**
	 * REST: GET,/rutaGrupal/{id}
	 * Lista todos los recorridos grupales por un id
	 * 
	 * @param id Identificador de ruta
	 */
    @GET
    @Path(PATH_DELIM + RUTA_GRUPAL + PATH_DELIM + LCURL + PARAM_ID + RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public RutaWeb getRutaGrupal(@PathParam(PARAM_ID) Long id) {
    	return transformadorDesplazamientos.toRutaWeb(service.getRuta(id));
	}

	/**
	 * REST: POST,/rutaGrupal, save one
	 * Permite guardar un recorrido grupal
	 * 
	 * @param ruta Informacion de la ruta a crear
	 * @return Identificador de ruta creada
	 */
    @POST
    @Path(PATH_DELIM + RUTA_GRUPAL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long postRutaGrupal(RutaWeb ruta) {
    	return service.persistRuta(transformadorDesplazamientos.toRutaJpa(ruta));
	}

	/**
	 * REST: PUT,/rutaGrupal, update one
	 * Permite guardar un recorrido grupal
	 * 
	 * @param ruta Informacion de la ruta a crear
	 * @return Identificador de ruta creada
	 */
    @PUT
    @Path(PATH_DELIM + RUTA_GRUPAL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long putRutaGrupal(RutaWeb ruta) {
    	return service.mergeRuta(transformadorDesplazamientos.toRutaJpa(ruta));
	}


    /**
	 * REST: DELETE,/rutaGrupal/{id}, cancel one
	 * Lista todos los recorridos grupales por un id
	 * 
	 * @param id Identificador de ruta
	 */
    @DELETE
    @Path(PATH_DELIM + RUTA_GRUPAL + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteRutaGrupal(@PathParam(PARAM_ID) Long id) {
    	service.deleteRuta(id);
	}

	/***** RUTAS GRUPALES ****/
	
	/**
	 * REST: GET,/rutasGrupales, list all 
	 * Lista todos los recorridos grupales 
	 * 
	 */
    @GET
    @Path(PATH_DELIM + RUTAS_GRUPALES)
    @Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> getRutasGrupales() {
    	return transformadorDesplazamientos.toListRutaWeb(service.listTodosRutasGrupales());
	}

	/**
	 * REST: GET,/orders/{?}, list orders by param
	 * Lista todos los recorridos grupales no vencidos
	 * 
	 */
	@GET
	@Path(PATH_DELIM + RUTAS_GRUPALES + PATH_DELIM + NO_VENCIDOS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> listRutasGrupalesNoVencidos() {
		return transformadorDesplazamientos.toListRutaWeb(service.listRutasGrupalesNoVencidos());
	}

	/**
	 * REST: GET,/orders/{?}, list orders by param
	 * Lista todos los recorridos grupales no vencidos
	 * 
	 * @param latitud
	 * @param longitud
	 * 
	 */
	@GET
	@Path(PATH_DELIM + RUTAS_GRUPALES + PATH_DELIM + CERCANOS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> listRutasGrupalesCercanos(@QueryParam("latitud") BigDecimal latitud,
			@QueryParam("longitud") BigDecimal longitud) {
		return transformadorDesplazamientos.toListRutaWeb(service.listRutasGrupalesCercanos(latitud, longitud));
	}


    /***** WAYPOINT *****/
    
    /**
	 * REST: GET,/waypoint/{id}
	 * Lista todos los recorridos grupales por un id
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
	 * Permite guardar un recorrido grupal
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
	 * Permite guardar un recorrido grupal
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
	 * Lista todos los recorridos grupales por un id
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
	 * Lista todos los recorridos grupales 
	 * 
	 */
    @GET
    @Path(PATH_DELIM + WAYPOINTS)
    @Produces(MediaType.APPLICATION_JSON)
	public List<WaypointWeb> getWaypoints() {
    	return transformadorDesplazamientos.toListWaypointWeb(service.listTodosWaypoints());
	}

    /**
	 * REST: GET,/rutaGrupal/{id}/waypoints, list 
	 * Lista todos los recorridos grupales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
    @GET
    @Path(PATH_DELIM + RUTA_GRUPAL + PATH_DELIM + LCURL + PARAM_ID + RCURL + PATH_DELIM + WAYPOINTS)
    @Produces(MediaType.APPLICATION_JSON)
	public List<WaypointWeb> getWaypoints(@PathParam(PARAM_ID) Long id) {
    	return transformadorDesplazamientos.toListWaypointWeb(service.listWaypoints(id));
	}

    /***** PARTICIPANTE *****/
    
    /**
	 * REST: GET,/participante/{id}
	 * Lista todos los recorridos grupales por un id
	 * 
	 * @param id Identificador de participante
	 */
    @GET
    @Path(PATH_DELIM + PARTICIPANTE + PATH_DELIM + LCURL + PARAM_ID + RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public ParticipanteWeb getParticipante(@PathParam(PARAM_ID) Long id) {
    	return transformadorDesplazamientos.toParticipanteWeb(service.getParticipante(id));
	}

	/**
	 * REST: POST,/participante, save one
	 * Permite guardar un recorrido grupal
	 * 
	 * @param participante Informacion de la participante a crear
	 * @return Identificador de participante creada
	 */
    @POST
    @Path(PATH_DELIM + PARTICIPANTE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long postParticipante(ParticipanteWeb participante) {
    	return service.persistParticipante(transformadorDesplazamientos.toParticipanteJpa(participante));
	}

	/**
	 * REST: PUT,/participante, update one
	 * Permite guardar un recorrido grupal
	 * 
	 * @param participante Informacion de la participante a crear
	 * @return Identificador de participante creada
	 */
    @PUT
    @Path(PATH_DELIM + PARTICIPANTE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long putParticipante(ParticipanteWeb participante) {
    	return service.mergeParticipante(transformadorDesplazamientos.toParticipanteJpa(participante));
	}


    /**
	 * REST: DELETE,/participante/{id}, cancel one
	 * Lista todos los recorridos grupales por un id
	 * 
	 * @param id Identificador de participante
	 */
    @DELETE
    @Path(PATH_DELIM + PARTICIPANTE + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteParticipante(@PathParam(PARAM_ID) Long id) {
    	service.deleteParticipante(id);
	}

	/***** PARTICIPANTES ****/
	
	/**
	 * REST: GET,/participantes, list all 
	 * Lista todos los recorridos grupales 
	 * 
	 */
    @GET
    @Path(PATH_DELIM + PARTICIPANTES)
    @Produces(MediaType.APPLICATION_JSON)
	public List<ParticipanteWeb> getParticipantes() {
    	return transformadorDesplazamientos.toListParticipanteWeb(service.listTodosParticipantes());
	}

    /**
	 * REST: GET,/rutaGrupal/{id}/participantes, list 
	 * Lista todos los recorridos grupales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
    @GET
    @Path(PATH_DELIM + RUTA_GRUPAL + PATH_DELIM + LCURL + PARAM_ID + RCURL + PATH_DELIM + PARTICIPANTES)
    @Produces(MediaType.APPLICATION_JSON)
	public List<ParticipanteWeb> getParticipantes(@PathParam(PARAM_ID) Long id) {
    	return transformadorDesplazamientos.toListParticipanteWeb(service.listParticipantes(id));
	}

}
