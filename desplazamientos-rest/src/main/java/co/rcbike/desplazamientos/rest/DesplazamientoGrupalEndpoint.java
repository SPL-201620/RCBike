package co.rcbike.desplazamientos.rest;

import java.math.BigDecimal;
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

import co.rcbike.desplazamientos.model.ParticipanteWeb;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.TransformadorDesplazamientos;
import co.rcbike.desplazamientos.model.WaypointWeb;
import co.rcbike.desplazamientos.service.DesplazamientosService;

@Path("/grupal")
@RequestScoped
public class DesplazamientoGrupalEndpoint {

	@Inject
	private DesplazamientosService service;

	@GET
	@Path("/alive")
	@Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
	}

	/**
	 * Lista todos los recorridos grupales no vencidos
	 * 
	 */
	@GET
	@Path("/listViajesGrupalesNoVencidos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> listViajesGrupalesNoVencidos() {
		return TransformadorDesplazamientos.toListRutaWeb(service.listViajesGrupalesNoVencidos());
	}

	/**
	 * Lista todos los recorridos grupales no vencidos
	 * 
	 * @param latitud
	 * @param longitud
	 * 
	 */
	@GET
	@Path("/listViajesGrupalesCercanos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> listViajesGrupalesCercanos(@QueryParam("latitud") BigDecimal latitud,
			@QueryParam("longitud") BigDecimal longitud) {
		return TransformadorDesplazamientos.toListRutaWeb(service.listViajesGrupalesCercanos(latitud, longitud));
	}

	/**
	 * Lista todos los recorridos grupales
	 * 
	 */
	@GET
	@Path("/listTodosViajesGrupales")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RutaWeb> listTodosViajesGrupales() {
		return TransformadorDesplazamientos.toListRutaWeb(service.listTodosViajesGrupales());
	}

	/**
	 * Permite guardar un recorrido individual o crear un recorrido organizado
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
	public String obtenerClima(@QueryParam("latitud") String latitud, @QueryParam("longitud") String longitud) {
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
	 * @param idRuta
	 *            identificador de la ruta
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
	 * @param WaypointWeb
	 *            Informacion del WaypointWeb a crear
	 */
	@POST
	@Path("/guardarWaypoint")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long guardarWaypoint(WaypointWeb waypoint) {
		return service.guardarWaypoint(TransformadorDesplazamientos.toWaypointJpa(waypoint));
	}

	/**
	 * Lista todos los participantes
	 * 
	 */
	@GET
	@Path("/listTodosParticipantes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ParticipanteWeb> listTodosParticipantes() {
		return TransformadorDesplazamientos.toListParticipanteWeb(service.listTodosParticipantes());
	}

	/**
	 * Lista todos los participantes de una ruta
	 * 
	 * @param idRuta
	 *            identificador de la ruta
	 */
	@POST
	@Path("/listParticipantes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ParticipanteWeb> listParticipantes(Long idRuta) {
		return TransformadorDesplazamientos.toListParticipanteWeb(service.listParticipantes(idRuta));
	}

	/**
	 * Permite crear un participante de ruta
	 * 
	 * @param participante
	 *            informacion de la participante de la bicicleta
	 */
	@POST
	@Path("/guardarParticipante")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long guardarParticipante(ParticipanteWeb participante) {
		return service.guardarParticipante(TransformadorDesplazamientos.toParticipanteJpa(participante));
	}

}
