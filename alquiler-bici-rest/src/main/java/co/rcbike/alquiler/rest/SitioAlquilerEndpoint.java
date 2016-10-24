package co.rcbike.alquiler.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.alquiler.model.SitioAlquiler;
import co.rcbike.alquiler.service.SitioAlquilerService;

@Path("/alquiler")
@RequestScoped
public class SitioAlquilerEndpoint {

	@Inject
	private SitioAlquilerService service;

	@GET
	@Path("/alive")
	@Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
	}

	/**
	 * Lista todos los Sitios de Alquileres de bicicleta existentes
	 * 
	 */
	@GET
	@Path("/listTodosSitiosAlquiler")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SitioAlquiler> listTodosSitiosAlquiler() {
		return service.listTodosSitiosAlquiler();
	}

	/**
	 * Lista todos las Sitios de Alquileres por un usuario dado su email
	 * 
	 * @param emailCreador
	 *            email del usuario creador
	 */
	@POST
	@Path("/listSitiosAlquiler")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<SitioAlquiler> listSitiosAlquiler(String emailCreador) {
		return service.listSitiosAlquiler(emailCreador);
	}

	/**
	 * Permite guardar un Sitio de Alquiler de bicicleta
	 * 
	 * @param SitioAlquiler
	 *            informacion de la SitioAlquiler a crear
	 */
	@POST
	@Path("/guardarSitioAlquiler")
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarSitioAlquiler(SitioAlquiler sitioAlquiler) {
		service.guardarSitioAlquiler(sitioAlquiler);
	}

	/**
	 * Lista todos los recorridos grupales no vencidos cercanos a una latitud y
	 * longitud
	 * 
	 * @param latitud
	 *            latitud geografica de la ruta
	 * @param longitud
	 *            longitud geografica de la ruta
	 * 
	 */
	@POST
	@Path("/listSitiosAlquilerCercanos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<SitioAlquiler> listSitiosAlquilerCercanos(@FormParam("latitud") BigDecimal latitud,
			@FormParam("longitud") BigDecimal longitud) {
		return service.listSitiosAlquilerCercanos(latitud, longitud);
	}
//  Implementacion con GET	
//	@GET
//	@Path("/listSitiosAlquilerCercanos")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<SitioAlquiler> listSitiosAlquilerCercanos(@QueryParam("latitud") BigDecimal latitud,
//			@QueryParam("longitud") BigDecimal longitud) {
//		return service.listSitiosAlquilerCercanos(latitud, longitud);
//	}

}
