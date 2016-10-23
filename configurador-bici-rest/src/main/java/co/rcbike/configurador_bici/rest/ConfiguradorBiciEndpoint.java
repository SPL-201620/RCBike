package co.rcbike.configurador_bici.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.configurador_bici.model.ConfiguracionBicicleta;
import co.rcbike.configurador_bici.model.PiezaBicicleta;
import co.rcbike.configurador_bici.service.ConfiguradorBiciService;

@Path("/configurar")
@RequestScoped
public class ConfiguradorBiciEndpoint {

	@Inject
	private ConfiguradorBiciService service;

	@GET
	@Path("/alive")
	@Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
	}

	/**
	 * Valida una configuracion de bicicleta
	 * 
	 * @param configuracion
	 *            configuracion de una bicicleta
	 */
	@POST
	@Path("/validarConfiguracion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean validarConfiguracion(ConfiguracionBicicleta configuracion) {
		return service.validarConfiguracion(configuracion);
	}

	/**
	 * Lista todas las configuraciones de bicicleta
	 * 
	 */
	@GET
	@Path("/listTodasConfiguraciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfiguracionBicicleta> listTodasConfiguraciones() {
		return service.listTodasConfiguraciones();
	}

	/**
	 * Lista todos las configuraciones por un usuario dado su email
	 * 
	 * @param emailCreador
	 *            email del usuario creador
	 */
	@POST
	@Path("/listConfiguraciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ConfiguracionBicicleta> listConfiguraciones(String emailCreador) {
		return service.listConfiguraciones(emailCreador);
	}

    /**
     * Permite crear una pieza de bicicleta 
     * 
     * @param piezaBicicleta informacion de la pieza de la bicicleta
     */
	@POST
	@Path("/guardarPieza")
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarPieza(PiezaBicicleta piezaBicicleta) {
		service.guardarPieza(piezaBicicleta);
	}

	/**
	 * Permite guardar una Configuracion de bicicleta
	 * 
	 * @param ConfiguracionBicicleta
	 *            informacion de la ConfiguracionBicicleta a crear
	 */
	@POST
	@Path("/guardarConfiguracion")
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarConfiguracion(ConfiguracionBicicleta configuracion) {
		service.guardarConfiguracion(configuracion);
	}

	/**
	 * Lista todos las piezas de una Configuracion de Bicicleta
	 * 
	 * @param idConfiguracion
	 *            identificador de la Configuracion de Bicicleta
	 */
	@POST
	@Path("/listPiezas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PiezaBicicleta> listPiezas(Long idConfiguracion) {
		return service.listPiezas(idConfiguracion);
	}

	/**
	 * Lista todos las piezas de bicicletas
	 * 
	 */
	@GET
	@Path("/listTodasPiezas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PiezaBicicleta> listTodasPiezas() {
		return service.listTodasPiezas();
	}

}
