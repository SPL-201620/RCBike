package co.rcbike.desplazamientos.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.rcbike.desplazamientos.model.Participante;
import co.rcbike.desplazamientos.model.Ruta;
import co.rcbike.desplazamientos.model.Tipo;
import co.rcbike.desplazamientos.model.Waypoint;

@Stateless
public class DesplazamientosService {

    private static final String OPEN_WEATHER_LANG_ESPANOL = "es";

    private static final String OPEN_WEATHER_APP_ID = "8eee44951b419558297ae716a624c9c6";

    /**
     * Velociadad promedio bicicleta m/h
     */
    private static final BigDecimal VEL_PROMEDIO_BICI = new BigDecimal("21430");

    /*
     * # delta distance (in meters) 1 0.1000000 11,057.43 2 0.0100000 1,105.74 3
     * 0.0010000 110.57 4 0.0001000 11.06 5 0.0000100 1.11 6 0.0000010 0.11 7
     * 0.0000001 0.01
     * 
     * Obtenido de
     * http://stackoverflow.com/questions/15965166/what-is-the-maximum-length-of
     * -latitude-and-longitude
     */
    private BigDecimal DISTANCIA_RUTA_CERCANA = new BigDecimal("0.01");/* 1Km */

    @Inject
    private EntityManager em;

    /**
     * Lista todos los recorridos grupales frecuentes
     * 
     */
    private List<Ruta> listViajesGrupalesFrecuentes() {
        TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndFrecuente, Ruta.class);
        q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
        q.setParameter(Ruta.SQ_PARAM_FRECUENTE, true);
        return q.getResultList();
    }

    /**
     * Lista todos los recorridos grupales no frecuentes no vencidos
     * 
     */
    private List<Ruta> listViajesGrupalesNoFrecuentesNoVencidos() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndFechaAndFrecuente, Ruta.class);
        q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
        q.setParameter(Ruta.SQ_PARAM_FECHA, cal.getTime());
        q.setParameter(Ruta.SQ_PARAM_FRECUENTE, false);
        return q.getResultList();
    }

    /**
     * Lista todos los recorridos grupales no vencidos
     * 
     */
    public List<Ruta> listViajesGrupalesNoVencidos() {
        List<Ruta> result = new ArrayList<Ruta>();
        result.addAll(listViajesGrupalesFrecuentes());
        result.addAll(listViajesGrupalesNoFrecuentesNoVencidos());
        return result;
    }

    /**
     * Lista todos los recorridos grupales frecuentes cercanos a una latitud y
     * longitud
     * 
     * @param latitud
     *            latitud Inicial de la ruta
     * @param longitud
     *            longitud Inicial de la ruta
     * @param latitud
     *            latitud Final de la ruta
     * @param longitud
     *            longitud Final de la ruta
     */
    private List<Ruta> listViajesGrupalesFrecuentesPunto(BigDecimal latitudInicio, BigDecimal latitudFinal,
            BigDecimal longitudInicio, BigDecimal longitudFinal) {
        TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndFrecuenteAndPunto, Ruta.class);
        q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
        q.setParameter(Ruta.SQ_PARAM_FRECUENTE, true);
        q.setParameter(Ruta.SQ_PARAM_LATITUD_INICIO, latitudInicio);
        q.setParameter(Ruta.SQ_PARAM_LATITUD_FINAL, latitudFinal);
        q.setParameter(Ruta.SQ_PARAM_LONGITUD_INICIO, longitudInicio);
        q.setParameter(Ruta.SQ_PARAM_LONGITUD_FINAL, longitudFinal);
        return q.getResultList();
    }

    /**
     * Lista todos los recorridos grupales no frecuentes no vencidos cercanos a
     * una latitud y longitud
     * 
     * @param latitud
     *            latitud Inicial de la ruta
     * @param longitud
     *            longitud Inicial de la ruta
     * @param latitud
     *            latitud Final de la ruta
     * @param longitud
     *            longitud Final de la ruta
     * 
     */
    private List<Ruta> listViajesGrupalesNoFrecuentesNoVencidosPunto(BigDecimal latitudInicio, BigDecimal latitudFinal,
            BigDecimal longitudInicio, BigDecimal longitudFinal) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndFechaAndFrecuenteAndPunto, Ruta.class);
        q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
        q.setParameter(Ruta.SQ_PARAM_FECHA, cal.getTime());
        q.setParameter(Ruta.SQ_PARAM_FRECUENTE, false);
        q.setParameter(Ruta.SQ_PARAM_LATITUD_INICIO, latitudInicio);
        q.setParameter(Ruta.SQ_PARAM_LATITUD_FINAL, latitudFinal);
        q.setParameter(Ruta.SQ_PARAM_LONGITUD_INICIO, longitudInicio);
        q.setParameter(Ruta.SQ_PARAM_LONGITUD_FINAL, longitudFinal);
        return q.getResultList();
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
    public List<Ruta> listViajesGrupalesCercanos(BigDecimal latitud, BigDecimal longitud) {
        BigDecimal latitudInicio = latitud.subtract(DISTANCIA_RUTA_CERCANA);
        BigDecimal latitudFinal = latitud.add(DISTANCIA_RUTA_CERCANA);
        BigDecimal longitudInicio = longitud.subtract(DISTANCIA_RUTA_CERCANA);
        BigDecimal longitudFinal = longitud.add(DISTANCIA_RUTA_CERCANA);
        List<Ruta> result = new ArrayList<Ruta>();
        result.addAll(listViajesGrupalesFrecuentesPunto(latitudInicio, latitudFinal, longitudInicio, longitudFinal));
        result.addAll(listViajesGrupalesNoFrecuentesNoVencidosPunto(latitudInicio, latitudFinal, longitudInicio,
                longitudFinal));
        return result;
    }

    /**
     * Lista todos los recorridos grupales
     * 
     */
    public List<Ruta> listViajesGrupales() {
        TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipo, Ruta.class);
        q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
        return q.getResultList();
    }

    /**
     * Lista todos los recorridos individuales realizados por un usuario
     * 
     * @param emailCreador
     *            email del usuario creador
     */
    public List<Ruta> listViajesIndividuales(String emailCreador) {
        TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndCreador, Ruta.class);
        q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.INDIVIDUAL);
        q.setParameter(Ruta.SQ_PARAM_EMAIL_CREADOR, emailCreador);
        return q.getResultList();
    }

    /**
     * Permite crear un participante en una ruta
     * 
     * @param idRuta
     *            identificador de la ruta
     * @param emailParticipante
     *            email del usuario participante
     */
    public void guardarParticipante(Long idRuta, String emailParticipante) {
        java.lang.System.out.print("\n El Email: " + emailParticipante);
        java.lang.System.out.print("\n El idRuta: " + idRuta + "\n");
        Participante participante = new Participante();
        participante.setRuta(em.getReference(Ruta.class, idRuta));
        participante.setEmail(emailParticipante);
        em.persist(participante);
    }

    /**
     * Permite guardar un recorrido individual o crear un recorrido organizado
     * 
     * @param ruta
     *            informacion de la ruta a crear
     */
    public void guardarViaje(Ruta ruta) {
        ruta.setCalorias(200);
        em.persist(ruta);
    }

    /**
     * TEMP Lista todos los Waypoints de una Ruta
     * 
     * @param idRuta
     *            identificador de la ruta
     */
    public List<Waypoint> listWaypointsRuta(Long idRuta) {
        TypedQuery<Waypoint> q = em.createNamedQuery(Waypoint.SQ_findByIdRuta, Waypoint.class);
        q.setParameter(Waypoint.SQ_PARAM_ID_RUTA, idRuta);
        return q.getResultList();
    }

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
}
