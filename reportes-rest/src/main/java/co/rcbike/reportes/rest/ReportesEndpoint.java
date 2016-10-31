package co.rcbike.reportes.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.rcbike.reportes.model.ResumenWeb;
import co.rcbike.reportes.model.TipoReporte;
import co.rcbike.reportes.service.ReportesService;

@Path("/reportes")
@RequestScoped
public class ReportesEndpoint {

	/** PARAMETROS REST **/

	// Separadores
	private static final String PATH_DELIM = "/";
	// Paths
	private static final String ALIVE = "alive";
	private static final String REPORTE = "reporte";

	/** FIN PARAMETROS REST **/

	@Inject
	private ReportesService service;

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
	@Path(PATH_DELIM + REPORTE)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResumenWeb> getReporte(@QueryParam("tipo") TipoReporte tipoReporte,
			@QueryParam("emailCreador") String emailCreador, @QueryParam("fechaInicio") String fechaInicio,
			@QueryParam("fechaFinal") String fechaFinal) {
		return service.getReporte(tipoReporte, emailCreador, fechaInicio, fechaFinal);
	}

}
