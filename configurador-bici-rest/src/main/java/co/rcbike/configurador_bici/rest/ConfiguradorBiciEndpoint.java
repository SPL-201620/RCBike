package co.rcbike.configurador_bici.rest;

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

import co.rcbike.configurador_bici.model.ColorWeb;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.OperacionesConfigurador;
import co.rcbike.configurador_bici.model.PiezaConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.configurador_bici.model.ValidacionConfiguracion;
import co.rcbike.configurador_bici.service.ConfiguradorService;
import co.rcbike.configurador_bici.service.TransformadorConfigurador;

@Path("configuracion")
@RequestScoped
public class ConfiguradorBiciEndpoint {

<<<<<<< HEAD
	/** PARAMETROS REST **/

	// Separadores
	private static final String PATH_DELIM = "/";
	private static final String LCURL = "{";
	private static final String RCURL = "}";
	// Paths
	private static final String ALIVE = "alive";
	private static final String CONFIGURACION = "configuracion";
	private static final String VALIDAR = "validar";
	private static final String CONFIGURACIONES = "configuraciones";
	private static final String PIEZAS_CONFIGURACION = "piezasConfiguracion";
	private static final String PIEZA_CONFIGURACION = "piezaConfiguracion";
	private static final String PIEZAS = "piezas";
	private static final String PIEZA = "pieza";
	private static final String COLORES = "colores";
	private static final String COLOR = "color";
	// Operaciones
	private static final String POR_EMAIL = "porEmail";
	private static final String POR_TIPO = "porTipo";
	// Parametros
	private static final String PARAM_EMAIL_CREADOR = "emailCreador";
	private static final String PARAM_ID = "id";
	private static final String PARAM_TIPO = "tipo";

	/** FIN PARAMETROS REST **/
=======
	
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git

	@Inject
	private ConfiguradorService service;

	@Inject
	private TransformadorConfigurador transformador;

<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + ALIVE)
	@Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
	}
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.ALIVE)
    @Produces(MediaType.APPLICATION_JSON)
    public String alive() {
        return "endpoint alive";
    }
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git

	/***** CONFIGURACONES ****/

	/**
	 * REST: GET,/configuracion/{id} Lista todos los recorridos por un id
	 * 
	 * @param id
	 *            Identificador de configuracion
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + CONFIGURACION + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	@Produces(MediaType.APPLICATION_JSON)
	public ConfiguracionWeb getConfiguracion(@PathParam(PARAM_ID) Long id) {
		return transformador.toConfiguracionWeb(service.getConfiguracion(id));
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACION + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public ConfiguracionWeb getConfiguracion(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	return transformador.toConfiguracionWeb(service.getConfiguracion(id));
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/**
	 * REST: POST,/configuracion, save one Permite guardar un recorrido
	 * 
	 * @param configuracion
	 *            Informacion de la configuracion a crear
	 * @return Identificador de configuracion creada
	 */
<<<<<<< HEAD
	@POST
	@Path(PATH_DELIM + CONFIGURACION)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @POST
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long postConfiguracion(ConfiguracionWeb configuracion) {
		return service.persistConfiguracion(transformador
				.toConfiguracionJpa(configuracion));
	}

	/**
	 * REST: PUT,/configuracion, update one Permite guardar un recorrido
	 * 
	 * @param configuracion
	 *            Informacion de la configuracion a crear
	 * @return Identificador de configuracion creada
	 */
<<<<<<< HEAD
	@PUT
	@Path(PATH_DELIM + CONFIGURACION)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @PUT
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long putConfiguracion(ConfiguracionWeb configuracion) {
		return service.mergeConfiguracion(transformador
				.toConfiguracionJpa(configuracion));
	}

	/**
	 * REST: DELETE,/configuracion/{id}, cancel one Lista todos los recorridos
	 * por un id
	 * 
	 * @param id
	 *            Identificador de configuracion
	 */
<<<<<<< HEAD
	@DELETE
	@Path(PATH_DELIM + CONFIGURACION + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteConfiguracion(@PathParam(PARAM_ID) Long id) {
		service.deleteConfiguracion(id);
=======
    @DELETE
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACION + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
	public void deleteConfiguracion(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	service.deleteConfiguracion(id);
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/**
	 * Valida una configuracion de bicicleta
	 * 
	 * @param configuracion
	 *            configuracion de una bicicleta
	 */
	@POST
	@Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACION + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.VALIDAR)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ValidacionConfiguracion validarConfiguracion(
			ConfiguracionWeb configuracion) {
		return service.validarConfiguracion(transformador
				.toConfiguracionJpa(configuracion));
	}

	/**
	 * Valida una configuracion de bicicleta
	 * 
	 * @param configuracion
	 *            configuracion de una bicicleta
	 */
	@GET
<<<<<<< HEAD
	@Path(PATH_DELIM + CONFIGURACION + PATH_DELIM + LCURL + PARAM_ID + RCURL
			+ PATH_DELIM + VALIDAR)
=======
	@Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACION + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.VALIDAR)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
	public ValidacionConfiguracion validarConfiguracion(
			@PathParam(PARAM_ID) Long id) {
=======
	public ValidacionConfiguracion validarConfiguracion(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
		return service.validarConfiguracion(id);
	}

	/***** CONFIGURACIONES ****/

	/**
	 * REST: GET,/configuraciones, list all Lista todos los recorridos
	 * 
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + CONFIGURACIONES)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACIONES)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public List<ConfiguracionWeb> getConfiguraciones() {
		return transformador.toListConfiguracionWeb(service
				.listTodosConfiguraciones());
	}

	/**
	 * REST: GET,/configuraciones/{email}, list Lista todos los recorridos
	 * realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + CONFIGURACIONES + PATH_DELIM + POR_EMAIL)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfiguracionWeb> getConfiguraciones(
			@QueryParam(PARAM_EMAIL_CREADOR) String emailCreador) {
		return transformador.toListConfiguracionWeb(service
				.listConfiguraciones(emailCreador));
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACIONES + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.POR_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
	public List<ConfiguracionWeb> getConfiguraciones(@QueryParam(OperacionesConfigurador.PARAM_EMAIL_CREADOR) String emailCreador) {
    	return transformador.toListConfiguracionWeb(service.listConfiguraciones(emailCreador));
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/***** PIEZA CONFIGURACION *****/

	/**
	 * REST: GET,/piezaConfiguracion/{id} Lista todos los recorridos por un id
	 * 
	 * @param id
	 *            Identificador de piezaConfiguracion
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + PIEZA_CONFIGURACION + PATH_DELIM + LCURL + PARAM_ID
			+ RCURL)
	@Produces(MediaType.APPLICATION_JSON)
	public PiezaConfiguracionWeb getPiezaConfiguracion(
			@PathParam(PARAM_ID) Long id) {
		return transformador.toPiezaConfiguracionWeb(service
				.getPiezaConfiguracion(id));
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA_CONFIGURACION + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public PiezaConfiguracionWeb getPiezaConfiguracion(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	return transformador.toPiezaConfiguracionWeb(service.getPiezaConfiguracion(id));
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/**
	 * REST: POST,/piezaConfiguracion, save one Permite guardar un recorrido
	 * 
	 * @param piezaConfiguracion
	 *            Informacion de la piezaConfiguracion a crear
	 * @return Identificador de piezaConfiguracion creada
	 */
<<<<<<< HEAD
	@POST
	@Path(PATH_DELIM + PIEZA_CONFIGURACION)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @POST
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA_CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long postPiezaConfiguracion(PiezaConfiguracionWeb piezaConfiguracion) {
		return service.persistPiezaConfiguracion(transformador
				.toPiezaConfiguracionJpa(piezaConfiguracion));
	}

	/**
	 * REST: PUT,/piezaConfiguracion, update one Permite guardar un recorrido
	 * 
	 * @param piezaConfiguracion
	 *            Informacion de la piezaConfiguracion a crear
	 * @return Identificador de piezaConfiguracion creada
	 */
<<<<<<< HEAD
	@PUT
	@Path(PATH_DELIM + PIEZA_CONFIGURACION)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @PUT
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA_CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long putPiezaConfiguracion(PiezaConfiguracionWeb piezaConfiguracion) {
		return service.mergePiezaConfiguracion(transformador
				.toPiezaConfiguracionJpa(piezaConfiguracion));
	}

	/**
	 * REST: DELETE,/piezaConfiguracion/{id}, cancel one Lista todos los
	 * recorridos por un id
	 * 
	 * @param id
	 *            Identificador de piezaConfiguracion
	 */
<<<<<<< HEAD
	@DELETE
	@Path(PATH_DELIM + PIEZA_CONFIGURACION + PATH_DELIM + LCURL + PARAM_ID
			+ RCURL)
	public void deletePiezaConfiguracion(@PathParam(PARAM_ID) Long id) {
		service.deletePiezaConfiguracion(id);
=======
    @DELETE
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA_CONFIGURACION + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
	public void deletePiezaConfiguracion(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	service.deletePiezaConfiguracion(id);
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/***** PIEZAS CONFIGURACION ****/

	/**
	 * REST: GET,/piezasConfiguracion, list all Lista todos los recorridos
	 * 
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + PIEZAS_CONFIGURACION)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZAS_CONFIGURACION)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public List<PiezaConfiguracionWeb> getPiezasConfiguracion() {
		return transformador.toListPiezaConfiguracionWeb(service
				.listTodosPiezasConfiguracion());
	}

	/**
	 * REST: GET,/configuracion/{id}/piezasConfiguracion, list Lista todos los
	 * recorridos realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + CONFIGURACION + PATH_DELIM + LCURL + PARAM_ID + RCURL
			+ PATH_DELIM + PIEZAS_CONFIGURACION)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PiezaConfiguracionWeb> getPiezasConfiguracion(
			@PathParam(PARAM_ID) Long id) {
		return transformador.toListPiezaConfiguracionWeb(service
				.listPiezasConfiguracion(id));
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.CONFIGURACION + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZAS_CONFIGURACION)
    @Produces(MediaType.APPLICATION_JSON)
	public List<PiezaConfiguracionWeb> getPiezasConfiguracion(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	return transformador.toListPiezaConfiguracionWeb(service.listPiezasConfiguracion(id));
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/***** PIEZA *****/

	/**
	 * REST: GET,/waypoint/{id} Lista todos los recorridos individuales por un
	 * id
	 * 
	 * @param id
	 *            Identificador de waypoint
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + PIEZA + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	@Produces(MediaType.APPLICATION_JSON)
	public PiezaWeb getPieza(@PathParam(PARAM_ID) Long id) {
		return transformador.toPiezaWeb(service.getPieza(id));
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public PiezaWeb getPieza(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	return transformador.toPiezaWeb(service.getPieza(id));
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/**
	 * REST: POST,/waypoint, save one Permite guardar un recorrido individual
	 * 
	 * @param waypoint
	 *            Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
<<<<<<< HEAD
	@POST
	@Path(PATH_DELIM + PIEZA)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @POST
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long postPieza(PiezaWeb waypoint) {
		return service.persistPieza(transformador.toPiezaJpa(waypoint));
	}

	/**
	 * REST: PUT,/waypoint, update one Permite guardar un recorrido individual
	 * 
	 * @param waypoint
	 *            Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
<<<<<<< HEAD
	@PUT
	@Path(PATH_DELIM + PIEZA)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @PUT
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long putPieza(PiezaWeb waypoint) {
		return service.mergePieza(transformador.toPiezaJpa(waypoint));
	}

	/**
	 * REST: DELETE,/waypoint/{id}, cancel one Lista todos los recorridos
	 * individuales por un id
	 * 
	 * @param id
	 *            Identificador de waypoint
	 */
<<<<<<< HEAD
	@DELETE
	@Path(PATH_DELIM + PIEZA + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deletePieza(@PathParam(PARAM_ID) Long id) {
		service.deletePieza(id);
=======
    @DELETE
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZA + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
	public void deletePieza(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	service.deletePieza(id);
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/***** PIEZAS ****/

	/**
	 * REST: GET,/piezas, list all Lista todos los recorridos individuales
	 * 
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + PIEZAS)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZAS)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public List<PiezaWeb> getPiezas() {
		return transformador.toListPiezaWeb(service.listTodasPiezas());
	}

	/**
	 * REST: GET,/piezas/porTipo?tipo=?, list Lista todos los recorridos
	 * realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + PIEZAS + PATH_DELIM + POR_TIPO)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PiezaWeb> getPiezas(@QueryParam(PARAM_TIPO) String tipo) {
		return transformador.toListPiezaWeb(service
				.listPiezas(TipoPiezaBicicleta.valueOf(tipo)));
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.PIEZAS + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.POR_TIPO)
    @Produces(MediaType.APPLICATION_JSON)
	public List<PiezaWeb> getPiezas(@QueryParam(OperacionesConfigurador.PARAM_TIPO) TipoPiezaBicicleta tipo) {
    	return transformador.toListPiezaWeb(service.listPiezas(tipo));
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/***** COLOR *****/

	/**
	 * REST: GET,/waypoint/{id} Lista todos los recorridos individuales por un
	 * id
	 * 
	 * @param id
	 *            Identificador de waypoint
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + COLOR + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	@Produces(MediaType.APPLICATION_JSON)
	public ColorWeb getColor(@PathParam(PARAM_ID) Long id) {
		return transformador.toColorWeb(service.getColor(id));
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.COLOR + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public ColorWeb getColor(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	return transformador.toColorWeb(service.getColor(id));
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/**
	 * REST: POST,/waypoint, save one Permite guardar un recorrido individual
	 * 
	 * @param waypoint
	 *            Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
<<<<<<< HEAD
	@POST
	@Path(PATH_DELIM + COLOR)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @POST
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.COLOR)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long postColor(ColorWeb waypoint) {
		return service.persistColor(transformador.toColorJpa(waypoint));
	}

	/**
	 * REST: PUT,/waypoint, update one Permite guardar un recorrido individual
	 * 
	 * @param waypoint
	 *            Informacion de la waypoint a crear
	 * @return Identificador de waypoint creada
	 */
<<<<<<< HEAD
	@PUT
	@Path(PATH_DELIM + COLOR)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @PUT
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.COLOR)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public Long putColor(ColorWeb waypoint) {
		return service.mergeColor(transformador.toColorJpa(waypoint));
	}

	/**
	 * REST: DELETE,/waypoint/{id}, cancel one Lista todos los recorridos
	 * individuales por un id
	 * 
	 * @param id
	 *            Identificador de waypoint
	 */
<<<<<<< HEAD
	@DELETE
	@Path(PATH_DELIM + COLOR + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteColor(@PathParam(PARAM_ID) Long id) {
		service.deleteColor(id);
=======
    @DELETE
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.COLOR + OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.LCURL + OperacionesConfigurador.PARAM_ID + OperacionesConfigurador.RCURL)
	public void deleteColor(@PathParam(OperacionesConfigurador.PARAM_ID) Long id) {
    	service.deleteColor(id);
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	}

	/***** COLORES ****/

	/**
	 * REST: GET,/waypoints, list all Lista todos los recorridos individuales
	 * 
	 */
<<<<<<< HEAD
	@GET
	@Path(PATH_DELIM + COLORES)
	@Produces(MediaType.APPLICATION_JSON)
=======
    @GET
    @Path(OperacionesConfigurador.PATH_DELIM + OperacionesConfigurador.COLORES)
    @Produces(MediaType.APPLICATION_JSON)
>>>>>>> branch 'master' of https://juanca0517@github.com/SPL-201620/RCBike.git
	public List<ColorWeb> getColores() {
		return transformador.toListColorWeb(service.listTodosColores());
	}

}
