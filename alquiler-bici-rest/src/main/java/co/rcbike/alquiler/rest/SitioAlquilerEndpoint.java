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

import co.rcbike.alquiler.model.OperacionesAlquiler;
import co.rcbike.alquiler.model.SitioAlquilerWeb;
import co.rcbike.alquiler.service.SitioAlquilerService;
import co.rcbike.alquiler.service.TransformadorAlquiler;

@Path("/alquiler")
@RequestScoped
public class SitioAlquilerEndpoint {

    @Inject
    private SitioAlquilerService service;

    @Inject
    private TransformadorAlquiler transformador;

    @GET
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.ALIVE)
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
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.SITIO_ALQUILER + OperacionesAlquiler.PATH_DELIM
            + OperacionesAlquiler.LCURL + OperacionesAlquiler.PARAM_ID + OperacionesAlquiler.RCURL)
    @Produces(MediaType.APPLICATION_JSON)
    public SitioAlquilerWeb getSitioAlquiler(@PathParam(OperacionesAlquiler.PARAM_ID) Long id) {
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
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.SITIO_ALQUILER)
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
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.SITIO_ALQUILER)
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
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.SITIO_ALQUILER + OperacionesAlquiler.PATH_DELIM
            + OperacionesAlquiler.LCURL + OperacionesAlquiler.PARAM_ID + OperacionesAlquiler.RCURL)
    public void deleteSitioAlquiler(@PathParam(OperacionesAlquiler.PARAM_ID) Long id) {
        service.deleteSitioAlquiler(id);
    }

    /***** SITIOS_ALQUILER ****/

    /**
     * REST: GET,/sitiosAlquiler, list all Lista todos los recorridos
     * 
     */
    @GET
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.SITIOS_ALQUILER)
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
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.SITIOS_ALQUILER + OperacionesAlquiler.PATH_DELIM
            + OperacionesAlquiler.POR_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
    public List<SitioAlquilerWeb> getSitiosAlquiler(
            @QueryParam(OperacionesAlquiler.PARAM_EMAIL_CREADOR) String emailCreador) {
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
    @Path(OperacionesAlquiler.PATH_DELIM + OperacionesAlquiler.SITIOS_ALQUILER + OperacionesAlquiler.PATH_DELIM
            + OperacionesAlquiler.CERCANOS)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<SitioAlquilerWeb> getSitiosAlquilerCercanos(@QueryParam("latitud") BigDecimal latitud,
            @QueryParam("longitud") BigDecimal longitud) {
        return transformador.toListSitioAlquilerWeb(service.listSitiosAlquilerCercanos(latitud, longitud));
    }
}
