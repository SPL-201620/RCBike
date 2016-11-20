package co.rcbike.desplazamientos.service;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class ClimaService {

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

        return String.format("%.2f", celsius) + " ºC, " + jsonString.substring(inicioDescripcion, finDescripcion) + ", "
                + jsonString.substring(inicioPais, finPais) + ".";
    }
}
