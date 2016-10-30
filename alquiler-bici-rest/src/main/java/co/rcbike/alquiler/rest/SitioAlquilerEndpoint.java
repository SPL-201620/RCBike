package co.rcbike.alquiler.rest;

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

import co.rcbike.alquiler.model.SitioAlquilerWeb;
import co.rcbike.alquiler.service.SitioAlquilerService;
import co.rcbike.alquiler.service.TransformadorAlquiler;

@Path("/alquiler")
@RequestScoped
public class SitioAlquilerEndpoint {

	/** PARAMETROS REST **/

	// Separadores
	private static final String PATH_DELIM = "/";
	private static final String LCURL = "{";
	private static final String RCURL = "}";
	// Paths
	private static final String ALIVE = "alive";
	private static final String SITIO_ALQUILER = "sitioAlquiler";
	private static final String SITIOS_ALQUILER = "sitiosAlquiler";
	// Operaciones
	private static final String POR_EMAIL = "porEmail";
	private static final String CERCANOS = "cercanos";
	// Parametros
	private static final String PARAM_EMAIL_CREADOR = "emailCreador";
	private static final String PARAM_ID = "id";

	/** FIN PARAMETROS REST **/

	@Inject
	private SitioAlquilerService service;

	@Inject
	private TransformadorAlquiler transformador;

	@GET
	@Path(PATH_DELIM + ALIVE)
	@Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
	}

	/***** CONFIGURACONES ****/

	/**
	 * REST: GET,/sitioAlquiler/{id} Lista todos los recorridos por un id
	 * 
	 * @param id
	 *            Identificador de sitioAlquiler
	 */
	@GET
	@Path(PATH_DELIM + SITIO_ALQUILER + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	@Produces(MediaType.APPLICATION_JSON)
	public SitioAlquilerWeb getSitioAlquiler(@PathParam(PARAM_ID) Long id) {
		return transformador.toSitioAlquilerWeb(service.getSitioAlquiler(id));
	}

	/**
	 * REST: POST,/sitioAlquiler, save one Permite guardar un recorrido
	 * 
	 * @param sitioAlquiler
	 *            Informacion de la sitioAlquiler a crear
	 * @return Identificador de sitioAlquiler creada
	 */
	@POST
	@Path(PATH_DELIM + SITIO_ALQUILER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long postSitioAlquiler(SitioAlquilerWeb sitioAlquiler) {
		return service.persistSitioAlquiler(transformador.toSitioAlquilerJpa(sitioAlquiler));
	}

	/**
	 * REST: PUT,/sitioAlquiler, update one Permite guardar un recorrido
	 * 
	 * @param sitioAlquiler
	 *            Informacion de la sitioAlquiler a crear
	 * @return Identificador de sitioAlquiler creada
	 */
	@PUT
	@Path(PATH_DELIM + SITIO_ALQUILER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Long putSitioAlquiler(SitioAlquilerWeb sitioAlquiler) {
		return service.mergeSitioAlquiler(transformador.toSitioAlquilerJpa(sitioAlquiler));
	}

	/**
	 * REST: DELETE,/sitioAlquiler/{id}, cancel one Lista todos los recorridos
	 * por un id
	 * 
	 * @param id
	 *            Identificador de sitioAlquiler
	 */
	@DELETE
	@Path(PATH_DELIM + SITIO_ALQUILER + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteSitioAlquiler(@PathParam(PARAM_ID) Long id) {
		service.deleteSitioAlquiler(id);
	}

	/***** SITIOS_ALQUILER ****/

	/**
	 * REST: GET,/sitiosAlquiler, list all Lista todos los recorridos
	 * 
	 */
	@GET
	@Path(PATH_DELIM + SITIOS_ALQUILER)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SitioAlquilerWeb> getSitiosAlquiler() {
		return transformador.toListSitioAlquilerWeb(service.listTodosSitiosAlquiler());
	}

	/**
	 * REST: GET,/sitiosAlquiler/{email}, list Lista todos los recorridos
	 * realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
	@GET
	@Path(PATH_DELIM + SITIOS_ALQUILER + PATH_DELIM + POR_EMAIL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SitioAlquilerWeb> getSitiosAlquiler(@QueryParam(PARAM_EMAIL_CREADOR) String emailCreador) {
		return transformador.toListSitioAlquilerWeb(service.listSitiosAlquiler(emailCreador));
	}

	/**
	 * Lista todos Sitios de alquiler cercanos a una latitud y longitud
	 * 
	 * @param latitud
	 *            Latitud geografica
	 * @param longitud
	 *            Longitud geografica
	 * 
	 */
	@GET
	@Path(PATH_DELIM + SITIOS_ALQUILER + PATH_DELIM + CERCANOS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<SitioAlquilerWeb> getSitiosAlquilerCercanos(@QueryParam("latitud") BigDecimal latitud,
			@QueryParam("longitud") BigDecimal longitud) {
		return transformador.toListSitioAlquilerWeb(service.listSitiosAlquilerCercanos(latitud, longitud));
	}
}
