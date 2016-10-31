package co.rcbike.reportes.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.rcbike.reportes.model.ResumenWeb;
import co.rcbike.reportes.model.RutaWeb;
import co.rcbike.reportes.model.TipoReporte;
import co.rcbike.reportes.model.fabrica.IReporteRecorridos;
import co.rcbike.reportes.model.fabrica.ReporteFactory;

@Stateless
public class ReportesService {

	private static final String OPEN_WEATHER_LANG_ESPANOL = "es";

	private static final String OPEN_WEATHER_APP_ID = "8eee44951b419558297ae716a624c9c6";

	/**
	 * Optiene un objeto con la informacion del tiempo
	 * 
	 * @param latitud
	 *            latitud geografica de la ruta
	 * @param longitud
	 *            longitud geografica de la ruta
	 * 
	 */
	public String obtenerClima(String latitud, String longitud) {
		Response weatherData;
		Client client = ClientBuilder.newClient();
		weatherData = client.target("http://api.openweathermap.org/data/2.5/weather").queryParam("lat", latitud)
				.queryParam("lon", longitud).queryParam("lang", OPEN_WEATHER_LANG_ESPANOL)
				.queryParam("appid", OPEN_WEATHER_APP_ID).request(MediaType.APPLICATION_JSON).get();

		String jsonString = weatherData.readEntity(String.class);

		int inicio = jsonString.indexOf("\"temp\":") + 7;
		int fin = jsonString.indexOf(",\"pressure\"");

		float kelvin = Float.parseFloat(jsonString.substring(inicio, fin));
		// Kelvin to Degree Celsius Conversion
		float celsius = kelvin - 273.15F;
		System.out.println("Celsius: " + celsius);

		int inicioDescripcion = jsonString.indexOf("\"description\":\"") + 15;
		int finDescripcion = jsonString.indexOf("\",\"icon\"");

		int inicioPais = jsonString.indexOf("\"country\":\"") + 11;
		int finPais = jsonString.indexOf("\",\"sunrise\"");

		return celsius + " ºC, " + jsonString.substring(inicioDescripcion, finDescripcion) + ", "
				+ jsonString.substring(inicioPais, finPais) + ".";
	}

	public List<ResumenWeb> getReporte(TipoReporte tipoReporte, String emailCreador, String fechaInicio,
			String fechaFinal) {
		List<RutaWeb> listaRutas = getRutas(tipoReporte, emailCreador, fechaInicio,
				fechaFinal);
		ReporteFactory factory = new ReporteFactory();
		IReporteRecorridos reporteRecorridos = factory.getReporteRecorridos(tipoReporte);
		List<ResumenWeb> result = reporteRecorridos.obtenerReporte(listaRutas);
		return result;
	}

	public List<RutaWeb> getRutas(TipoReporte tipoReporte, String emailCreador, String fechaInicio, String fechaFinal) {

		List<RutaWeb> result = null;
		Client client = ClientBuilder.newClient();
		try {

			Response response = client.target("http://localhost:8080/desplazamientos-rest/rest/individual/rutas")
					.queryParam("emailCreador", emailCreador).queryParam("fechaInicio", fechaInicio)
					.queryParam("fechaFinal", fechaFinal).request(MediaType.APPLICATION_JSON).get(Response.class);

			result = response.readEntity(new GenericType<List<RutaWeb>>() {
			});
		} finally {
			client.close();
		}
		return result;
	}

}
