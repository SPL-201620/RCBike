package co.rcbike.desplazamientos.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.rcbike.desplazamientos.model.Ruta;
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
	public List<Ruta> listViajesGrupalesNoVencidos() {
		return service.listViajesGrupalesNoVencidos();
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
	public List<Ruta> listViajesGrupalesCercanos(@QueryParam("latitud") BigDecimal latitud,
			@QueryParam("longitud") BigDecimal longitud) {
		return service.listViajesGrupalesCercanos(latitud, longitud);
	}

	/**
	 * Lista todos los recorridos grupales
	 * 
	 */
	@GET
	@Path("/listViajesGrupales")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ruta> listViajesGrupales() {
		return service.listViajesGrupales();
	}

	/**
	 * Permite crear un participante en una ruta
	 * 
	 * @param idRuta
	 *            identificador de la ruta
	 * @param emailParticipante
	 *            email del usuario participante
	 */
	@GET
	@Path("/guardarParticipante")
	@Produces(MediaType.APPLICATION_JSON)
	public void guardarParticipante(@QueryParam("idRuta") Long idRuta, @QueryParam("email") String emailParticipante) {
		service.guardarParticipante(idRuta, emailParticipante);
	}

	/**
	 * Permite guardar un recorrido individual o crear un recorrido organizado
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
