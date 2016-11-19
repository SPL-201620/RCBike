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
import co.rcbike.configurador_bici.model.OperacionesConfiguracion;
import co.rcbike.configurador_bici.model.PiezaConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.configurador_bici.model.ValidacionConfiguracion;
import co.rcbike.configurador_bici.service.ConfiguradorService;
import co.rcbike.configurador_bici.service.TransformadorConfigurador;

@Path(OperacionesConfiguracion.EP_CONFIGURACION)
@RequestScoped
public class ConfiguradorBiciEndpoint {

    @Inject
    private ConfiguradorService service;

    @Inject
    private TransformadorConfigurador transformador;

    /***** CONFIGURACONES ****/

    /**
     * REST: GET,/configuracion/{id} Lista todos los recorridos por un id
     * 
     * @param id
     *            Identificador de configuracion
     */

    @GET
    @Path(OperacionesConfiguracion.EP_CONFIGURACION + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public ConfiguracionWeb getConfiguracion(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        return transformador.toConfiguracionWeb(service.getConfiguracion(id));

    }

    /**
     * REST: POST,/configuracion, save one Permite guardar un recorrido
     * 
     * @param configuracion
     *            Informacion de la configuracion a crear
     * @return Identificador de configuracion creada
     */

    @POST
    @Path(OperacionesConfiguracion.EP_CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long postConfiguracion(ConfiguracionWeb configuracion) {
        return service.persistConfiguracion(transformador.toConfiguracionJpa(configuracion));
    }

    /**
     * REST: PUT,/configuracion, update one Permite guardar un recorrido
     * 
     * @param configuracion
     *            Informacion de la configuracion a crear
     * @return Identificador de configuracion creada
     */

    @PUT
    @Path(OperacionesConfiguracion.EP_CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long putConfiguracion(ConfiguracionWeb configuracion) {
        return service.mergeConfiguracion(transformador.toConfiguracionJpa(configuracion));
    }

    /**
     * REST: DELETE,/configuracion/{id}, cancel one Lista todos los recorridos
     * por un id
     * 
     * @param id
     *            Identificador de configuracion
     */

    @DELETE
    @Path(OperacionesConfiguracion.EP_CONFIGURACION + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    public void deleteConfiguracion(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        service.deleteConfiguracion(id);
    }

    /**
     * Valida una configuracion de bicicleta
     * 
     * @param configuracion
     *            configuracion de una bicicleta
     */
    @POST
    @Path(OperacionesConfiguracion.EP_CONFIGURACION + "/" + OperacionesConfiguracion.OP_VALIDAR)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ValidacionConfiguracion validarConfiguracion(ConfiguracionWeb configuracion) {
        return service.validarConfiguracion(transformador.toConfiguracionJpa(configuracion));
    }

    /**
     * Valida una configuracion de bicicleta
     * 
     * @param configuracion
     *            configuracion de una bicicleta
     */
    @GET
    @Path(OperacionesConfiguracion.EP_CONFIGURACION + "/" + OperacionesConfiguracion.PATH_PRM_ID + "/"
            + OperacionesConfiguracion.OP_VALIDAR)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ValidacionConfiguracion validarConfiguracion(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        return service.validarConfiguracion(id);
    }

    /***** CONFIGURACIONES ****/

    /**
     * REST: GET,/configuraciones, list all Lista todos los recorridos
     * 
     */

    @GET
    @Path(OperacionesConfiguracion.OP_CONFIGURACIONES)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConfiguracionWeb> getConfiguraciones() {
        return transformador.toListConfiguracionWeb(service.listTodosConfiguraciones());
    }

    /**
     * REST: GET,/configuraciones/{email}, list Lista todos los recorridos
     * realizados por un usuario
     * 
     * @param emailCreador
     *            email del usuario
     */

    @GET
    @Path(OperacionesConfiguracion.OP_CONFIGURACIONES + "/" + OperacionesConfiguracion.OP_BY_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConfiguracionWeb> getConfiguraciones(
            @QueryParam(OperacionesConfiguracion.PARAM_EMAIL_CREADOR) String emailCreador) {
        return transformador.toListConfiguracionWeb(service.listConfiguraciones(emailCreador));

    }

    /***** PIEZA CONFIGURACION *****/

    /**
     * REST: GET,/piezaConfiguracion/{id} Lista todos los recorridos por un id
     * 
     * @param id
     *            Identificador de piezaConfiguracion
     */

    @GET
    @Path(OperacionesConfiguracion.OP_PIEZA_CONFIGURACION + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public PiezaConfiguracionWeb getPiezaConfiguracion(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        return transformador.toPiezaConfiguracionWeb(service.getPiezaConfiguracion(id));

    }

    /**
     * REST: POST,/piezaConfiguracion, save one Permite guardar un recorrido
     * 
     * @param piezaConfiguracion
     *            Informacion de la piezaConfiguracion a crear
     * @return Identificador de piezaConfiguracion creada
     */

    @POST
    @Path(OperacionesConfiguracion.OP_PIEZA_CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long postPiezaConfiguracion(PiezaConfiguracionWeb piezaConfiguracion) {
        return service.persistPiezaConfiguracion(transformador.toPiezaConfiguracionJpa(piezaConfiguracion));
    }

    /**
     * REST: PUT,/piezaConfiguracion, update one Permite guardar un recorrido
     * 
     * @param piezaConfiguracion
     *            Informacion de la piezaConfiguracion a crear
     * @return Identificador de piezaConfiguracion creada
     */

    @PUT
    @Path(OperacionesConfiguracion.OP_PIEZA_CONFIGURACION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long putPiezaConfiguracion(PiezaConfiguracionWeb piezaConfiguracion) {
        return service.mergePiezaConfiguracion(transformador.toPiezaConfiguracionJpa(piezaConfiguracion));
    }

    /**
     * REST: DELETE,/piezaConfiguracion/{id}, cancel one Lista todos los
     * recorridos por un id
     * 
     * @param id
     *            Identificador de piezaConfiguracion
     */
    @DELETE
    @Path(OperacionesConfiguracion.OP_PIEZA_CONFIGURACION + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    public void deletePiezaConfiguracion(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        service.deletePiezaConfiguracion(id);
    }

    /***** PIEZAS CONFIGURACION ****/

    /**
     * REST: GET,/piezasConfiguracion, list all Lista todos los recorridos
     * 
     */

    @GET
    @Path(OperacionesConfiguracion.OP_PIEZAS_CONFIGURACION)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PiezaConfiguracionWeb> getPiezasConfiguracion() {
        return transformador.toListPiezaConfiguracionWeb(service.listTodosPiezasConfiguracion());
    }

    /**
     * REST: GET,/configuracion/{id}/piezasConfiguracion, list Lista todos los
     * recorridos realizados por un usuario
     * 
     * @param emailCreador
     *            email del usuario
     */

    @GET
    @Path(OperacionesConfiguracion.EP_CONFIGURACION + "/" + OperacionesConfiguracion.PATH_PRM_ID + "/"
            + OperacionesConfiguracion.OP_PIEZAS_CONFIGURACION)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PiezaConfiguracionWeb> getPiezasConfiguracion(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        return transformador.toListPiezaConfiguracionWeb(service.listPiezasConfiguracion(id));
    }

    /***** PIEZA *****/

    /**
     * REST: GET,/waypoint/{id} Lista todos los recorridos individuales por un
     * id
     * 
     * @param id
     *            Identificador de waypoint
     */

    @GET
    @Path(OperacionesConfiguracion.OP_PIEZA + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public PiezaWeb getPieza(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        return transformador.toPiezaWeb(service.getPieza(id));
    }

    /**
     * REST: POST,/waypoint, save one Permite guardar un recorrido individual
     * 
     * @param waypoint
     *            Informacion de la waypoint a crear
     * @return Identificador de waypoint creada
     */

    @POST
    @Path(OperacionesConfiguracion.OP_PIEZA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
    @PUT
    @Path(OperacionesConfiguracion.OP_PIEZA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
    @DELETE
    @Path(OperacionesConfiguracion.OP_PIEZA + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    public void deletePieza(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        service.deletePieza(id);
    }

    /***** PIEZAS ****/

    /**
     * REST: GET,/piezas, list all Lista todos los recorridos individuales
     * 
     */

    @GET
    @Path(OperacionesConfiguracion.OP_PIEZAS)
    @Produces(MediaType.APPLICATION_JSON)
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

    @GET
    @Path(OperacionesConfiguracion.OP_PIEZAS + "/" + OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
    @Produces(MediaType.APPLICATION_JSON)
    public List<PiezaWeb> getPiezas(@QueryParam(OperacionesConfiguracion.PATH_PRM_TIPO) TipoPiezaBicicleta tipo) {
        return transformador.toListPiezaWeb(service.listPiezas(tipo));
    }

    /***** COLOR *****/

    /**
     * REST: GET,/waypoint/{id} Lista todos los recorridos individuales por un
     * id
     * 
     * @param id
     *            Identificador de waypoint
     */

    @GET
    @Path(OperacionesConfiguracion.OP_COLOR + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    @Produces(MediaType.APPLICATION_JSON)
    public ColorWeb getColor(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        return transformador.toColorWeb(service.getColor(id));
    }

    /**
     * REST: POST,/waypoint, save one Permite guardar un recorrido individual
     * 
     * @param waypoint
     *            Informacion de la waypoint a crear
     * @return Identificador de waypoint creada
     */

    @POST
    @Path(OperacionesConfiguracion.OP_COLOR)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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

    @PUT
    @Path(OperacionesConfiguracion.OP_COLOR)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
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
    @DELETE
    @Path(OperacionesConfiguracion.OP_COLOR + "/" + OperacionesConfiguracion.PATH_PRM_ID)
    public void deleteColor(@PathParam(OperacionesConfiguracion.PRM_ID) Long id) {
        service.deleteColor(id);

    }

    /***** COLORES ****/

    /**
     * REST: GET,/waypoints, list all Lista todos los recorridos individuales
     * 
     */

    @GET
    @Path(OperacionesConfiguracion.OP_COLORES)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ColorWeb> getColores() {
        return transformador.toListColorWeb(service.listTodosColores());
    }

}
