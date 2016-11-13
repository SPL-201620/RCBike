package co.rcbike.desplazamientos.rest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
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

import co.rcbike.desplazamientos.model.OperacionesDesplazamientos;
import co.rcbike.desplazamientos.model.ParticipanteWeb;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.WaypointWeb;
import co.rcbike.desplazamientos.service.DesplazamientosService;
import co.rcbike.desplazamientos.service.TransformadorDesplazamientos;

@Path("/grupal")
@RequestScoped
public class DesplazamientoGrupalEndpoint {

    @Inject
    private DesplazamientosService service;

    @Inject
    private TransformadorDesplazamientos transformadorDesplazamientos;

    @GET
    @Path("/" + OperacionesDesplazamientos.ALIVE)
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
    @Path("/" + OperacionesDesplazamientos.CLIMA)
    @Produces(MediaType.APPLICATION_JSON)
    public String getClima(@QueryParam("latitud") String latitud, @QueryParam("longitud") String longitud) {
        return service.obtenerClima(latitud, longitud);
    }

    /***** RUTA GRUPAL ****/

    /**
     * REST: GET,/rutaGrupal/{id} Lista todos los recorridos grupales por un id
     * 
     * @param id
     *            Identificador de ruta
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.RUTA_GRUPAL
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public RutaWeb getRutaGrupal(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        return transformadorDesplazamientos.toRutaWeb(service.getRuta(id));
    }

    /**
     * REST: POST,/rutaGrupal, save one Permite guardar un recorrido grupal
     * 
     * @param ruta
     *            Informacion de la ruta a crear
     * @return Identificador de ruta creada
     */
    @POST
    @Path("/" + OperacionesDesplazamientos.RUTA_GRUPAL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long postRutaGrupal(RutaWeb ruta) {
        return service.persistRuta(transformadorDesplazamientos.toRutaJpa(ruta));
    }

    /**
     * REST: PUT,/rutaGrupal, update one Permite guardar un recorrido grupal
     * 
     * @param ruta
     *            Informacion de la ruta a crear
     * @return Identificador de ruta creada
     */
    @PUT
    @Path("/" + OperacionesDesplazamientos.RUTA_GRUPAL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long putRutaGrupal(RutaWeb ruta) {
        return service.mergeRuta(transformadorDesplazamientos.toRutaJpa(ruta));
    }

    /**
     * REST: DELETE,/rutaGrupal/{id}, cancel one Lista todos los recorridos
     * grupales por un id
     * 
     * @param id
     *            Identificador de ruta
     */
    @DELETE
    @Path("/" + OperacionesDesplazamientos.RUTA_GRUPAL
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}")
    public void deleteRutaGrupal(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        service.deleteRuta(id);
    }

    /***** RUTAS *****/

    /**
     * REST: GET,/rutasIndividuales, list all Lista todos los recorridos
     * individuales
     * 
     * @param emailCreador
     * @param fechaInicio
     * @param fechaFinal
     * 
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.RUTAS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RutaWeb> getRutas(@QueryParam("emailCreador") String emailCreador,
            @QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFinal") String fechaFinal) {

        Date dateFechaInicio = null;
        Date dateFechaFinal = null;
        try {
            dateFechaInicio = OperacionesDesplazamientos.DATE_FORMAT.parse(fechaInicio);
            dateFechaFinal = OperacionesDesplazamientos.DATE_FORMAT.parse(fechaFinal);
        } catch (ParseException e) {
            return null;
        }
        return transformadorDesplazamientos
                .toListRutaWeb(service.listRutasFechas(emailCreador, dateFechaInicio, dateFechaFinal));
    }

    /***** RUTAS GRUPALES ****/

    /**
     * REST: GET,/rutasGrupales, list all Lista todos los recorridos grupales
     * 
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.RUTAS_GRUPALES)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RutaWeb> getRutasGrupales() {
        return transformadorDesplazamientos.toListRutaWeb(service.listTodosRutasGrupales());
    }

    /**
     * REST: GET,/orders/{?}, list orders by param Lista todos los recorridos
     * grupales no vencidos
     * 
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.RUTAS_GRUPALES
            + "/" + OperacionesDesplazamientos.NO_VENCIDOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RutaWeb> listRutasGrupalesNoVencidos() {
        return transformadorDesplazamientos.toListRutaWeb(service.listRutasGrupalesNoVencidos());
    }

    /**
     * REST: GET,rutasGrupales/cercanos/{?}, list RutaWeb by param Lista todos
     * los recorridos grupales no vencidos
     * 
     * @param latitud
     * @param longitud
     * 
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.RUTAS_GRUPALES
            + "/" + OperacionesDesplazamientos.CERCANOS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<RutaWeb> listRutasGrupalesCercanos(@QueryParam("latitud") BigDecimal latitud,
            @QueryParam("longitud") BigDecimal longitud) {
        return transformadorDesplazamientos.toListRutaWeb(service.listRutasGrupalesCercanos(latitud, longitud));
    }

    /***** WAYPOINT *****/

    /**
     * REST: GET,/waypoint/{id} Lista todos los recorridos grupales por un id
     * 
     * @param id
     *            Identificador de waypoint
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.WAYPOINT
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public WaypointWeb getWaypoint(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        return transformadorDesplazamientos.toWaypointWeb(service.getWaypoint(id));
    }

    /**
     * REST: POST,/waypoint, save one Permite guardar un recorrido grupal
     * 
     * @param waypoint
     *            Informacion de la waypoint a crear
     * @return Identificador de waypoint creada
     */
    @POST
    @Path("/" + OperacionesDesplazamientos.WAYPOINT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long postWaypoint(WaypointWeb waypoint) {
        return service.persistWaypoint(transformadorDesplazamientos.toWaypointJpa(waypoint));
    }

    /**
     * REST: PUT,/waypoint, update one Permite guardar un recorrido grupal
     * 
     * @param waypoint
     *            Informacion de la waypoint a crear
     * @return Identificador de waypoint creada
     */
    @PUT
    @Path("/" + OperacionesDesplazamientos.WAYPOINT)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long putWaypoint(WaypointWeb waypoint) {
        return service.mergeWaypoint(transformadorDesplazamientos.toWaypointJpa(waypoint));
    }

    /**
     * REST: DELETE,/waypoint/{id}, cancel one Lista todos los recorridos
     * grupales por un id
     * 
     * @param id
     *            Identificador de waypoint
     */
    @DELETE
    @Path("/" + OperacionesDesplazamientos.WAYPOINT
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}")
    public void deleteWaypoint(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        service.deleteWaypoint(id);
    }

    /***** WAYPOINTS ****/

    /**
     * REST: GET,/waypoints, list all Lista todos los recorridos grupales
     * 
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.WAYPOINTS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<WaypointWeb> getWaypoints() {
        return transformadorDesplazamientos.toListWaypointWeb(service.listTodosWaypoints());
    }

    /**
     * REST: GET,/rutaGrupal/{id}/waypoints, list Lista todos los recorridos
     * grupales realizados por un usuario
     * 
     * @param emailCreador
     *            email del usuario
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.RUTA_GRUPAL
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}"
            + "/" + OperacionesDesplazamientos.WAYPOINTS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<WaypointWeb> getWaypoints(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        return transformadorDesplazamientos.toListWaypointWeb(service.listWaypoints(id));
    }

    /***** PARTICIPANTE *****/

    /**
     * REST: GET,/participante/{id} Lista todos los recorridos grupales por un
     * id
     * 
     * @param id
     *            Identificador de participante
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.PARTICIPANTE
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public ParticipanteWeb getParticipante(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        return transformadorDesplazamientos.toParticipanteWeb(service.getParticipante(id));
    }

    /**
     * REST: POST,/participante, save one Permite guardar un recorrido grupal
     * 
     * @param participante
     *            Informacion de la participante a crear
     * @return Identificador de participante creada
     */
    @POST
    @Path("/" + OperacionesDesplazamientos.PARTICIPANTE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long postParticipante(ParticipanteWeb participante) {
        return service.persistParticipante(transformadorDesplazamientos.toParticipanteJpa(participante));
    }

    /**
     * REST: PUT,/participante, update one Permite guardar un recorrido grupal
     * 
     * @param participante
     *            Informacion de la participante a crear
     * @return Identificador de participante creada
     */
    @PUT
    @Path("/" + OperacionesDesplazamientos.PARTICIPANTE)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long putParticipante(ParticipanteWeb participante) {
        return service.mergeParticipante(transformadorDesplazamientos.toParticipanteJpa(participante));
    }

    /**
     * REST: DELETE,/participante/{id}, cancel one Lista todos los recorridos
     * grupales por un id
     * 
     * @param id
     *            Identificador de participante
     */
    @DELETE
    @Path("/" + OperacionesDesplazamientos.PARTICIPANTE
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}")
    public void deleteParticipante(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        service.deleteParticipante(id);
    }

    /***** PARTICIPANTES ****/

    /**
     * REST: GET,/participantes, list all Lista todos los recorridos grupales
     * 
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.PARTICIPANTES)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParticipanteWeb> getParticipantes() {
        return transformadorDesplazamientos.toListParticipanteWeb(service.listTodosParticipantes());
    }

    /**
     * REST: GET,/rutaGrupal/{id}/participantes, list Lista todos los recorridos
     * grupales realizados por un usuario
     * 
     * @param emailCreador
     *            email del usuario
     */
    @GET
    @Path("/" + OperacionesDesplazamientos.RUTA_GRUPAL
            + "/" + "{"
            + OperacionesDesplazamientos.PARAM_ID + "}"
            + "/" + OperacionesDesplazamientos.PARTICIPANTES)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ParticipanteWeb> getParticipantes(@PathParam(OperacionesDesplazamientos.PARAM_ID) Long id) {
        return transformadorDesplazamientos.toListParticipanteWeb(service.listParticipantes(id));
    }

}
