package co.rcbike.ventas.rest;

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

import co.rcbike.ventas.model.OperacionesVentas;
import co.rcbike.ventas.model.VentaWeb;
import co.rcbike.ventas.service.TransformadorVentas;
import co.rcbike.ventas.service.VentasService;

@Path(OperacionesVentas.EP_VENTA)
@RequestScoped
public class VentasEndpoint {

    @Inject
    private VentasService service;

    @Inject
    private TransformadorVentas transformador;

    /***** CONFIGURACONES ****/

    /**
     * REST: GET,/venta/{id} Lista todos los recorridos por un id
     * 
     * @param id
     *            Identificador de venta
     */
    @GET
    @Path(OperacionesVentas.EP_VENTA + "/" + "{" + OperacionesVentas.PARAM_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public VentaWeb getVenta(@PathParam(OperacionesVentas.PARAM_ID) Long id) {
        return transformador.toVentaWeb(service.getVenta(id));
    }

    /**
     * REST: POST,/venta, save one Permite guardar un recorrido
     * 
     * @param venta
     *            Informacion de la venta a crear
     * @return Identificador de venta creada
     */
    @POST
    @Path(OperacionesVentas.EP_VENTA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long postVenta(VentaWeb venta) {
        return service.persistVenta(transformador.toVentaJpa(venta));
    }

    /**
     * REST: PUT,/venta, update one Permite guardar un recorrido
     * 
     * @param venta
     *            Informacion de la venta a crear
     * @return Identificador de venta creada
     */
    @PUT
    @Path(OperacionesVentas.EP_VENTA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Long putVenta(VentaWeb venta) {
        return service.mergeVenta(transformador.toVentaJpa(venta));
    }

    /**
     * REST: DELETE,/venta/{id}, cancel one Lista todos los recorridos por un id
     * 
     * @param id
     *            Identificador de venta
     */
    @DELETE
    @Path(OperacionesVentas.EP_VENTA + "/" + "{" + OperacionesVentas.PARAM_ID + "}")
    public void deleteVenta(@PathParam(OperacionesVentas.PARAM_ID) Long id) {
        service.deleteVenta(id);
    }

    /***** VENTAS ****/

    /**
     * REST: GET,/ventas, list all Lista todos los recorridos
     * 
     */

    @GET
    @Path(OperacionesVentas.VENTAS)
    @Produces(MediaType.APPLICATION_JSON)
    public List<VentaWeb> getVentas() {
        return transformador.toListVentaWeb(service.listTodosVentas());
    }

    /**
     * REST: GET,/ventas/{email}, list Lista todos los recorridos realizados por
     * un usuario
     * 
     * @param emailCreador
     *            email del usuario
     */

    @GET
    @Path(OperacionesVentas.VENTAS + "/" + OperacionesVentas.POR_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
    public List<VentaWeb> getVentas(@QueryParam(OperacionesVentas.PARAM_EMAIL_CREADOR) String emailCreador) {
        return transformador.toListVentaWeb(service.listVentas(emailCreador));
    }

}
