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

import co.rcbike.ventas.model.VentaWeb;
import co.rcbike.ventas.service.TransformadorVentas;
import co.rcbike.ventas.service.VentasService;

@Path("/venta")
@RequestScoped
public class VentasEndpoint {

	/** PARAMETROS REST **/

	//Separadores
	private static final String PATH_DELIM = "/";
	private static final String LCURL = "{";
	private static final String RCURL = "}";
	//Paths
    private static final String ALIVE = "alive";
    private static final String VENTA = "venta";
    private static final String VENTAS = "ventas";
    //Operaciones
    private static final String POR_EMAIL = "porEmail";
    //Parametros
    private static final String PARAM_EMAIL_CREADOR = "emailCreador";
	private static final String PARAM_ID = "id";

	/** FIN PARAMETROS REST **/

	@Inject
	private VentasService service;

	@Inject
	private TransformadorVentas transformador;

    @GET
    @Path(PATH_DELIM + ALIVE)
    @Produces(MediaType.APPLICATION_JSON)
    public String alive() {
        return "endpoint alive";
    }

	/***** CONFIGURACONES ****/
	
    /**
	 * REST: GET,/venta/{id}
	 * Lista todos los recorridos  por un id
	 * 
	 * @param id Identificador de venta
	 */
    @GET
    @Path(PATH_DELIM + VENTA + PATH_DELIM + LCURL + PARAM_ID + RCURL)
    @Produces(MediaType.APPLICATION_JSON)
	public VentaWeb getVenta(@PathParam(PARAM_ID) Long id) {
    	return transformador.toVentaWeb(service.getVenta(id));
	}

	/**
	 * REST: POST,/venta, save one
	 * Permite guardar un recorrido 
	 * 
	 * @param venta Informacion de la venta a crear
	 * @return Identificador de venta creada
	 */
    @POST
    @Path(PATH_DELIM + VENTA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long postVenta(VentaWeb venta) {
    	return service.persistVenta(transformador.toVentaJpa(venta));
	}

	/**
	 * REST: PUT,/venta, update one
	 * Permite guardar un recorrido 
	 * 
	 * @param venta Informacion de la venta a crear
	 * @return Identificador de venta creada
	 */
    @PUT
    @Path(PATH_DELIM + VENTA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Long putVenta(VentaWeb venta) {
    	return service.mergeVenta(transformador.toVentaJpa(venta));
	}


    /**
	 * REST: DELETE,/venta/{id}, cancel one
	 * Lista todos los recorridos  por un id
	 * 
	 * @param id Identificador de venta
	 */
    @DELETE
    @Path(PATH_DELIM + VENTA + PATH_DELIM + LCURL + PARAM_ID + RCURL)
	public void deleteVenta(@PathParam(PARAM_ID) Long id) {
    	service.deleteVenta(id);
	}

	/***** VENTAS  ****/
	
	/**
	 * REST: GET,/ventas, list all 
	 * Lista todos los recorridos  
	 * 
	 */
    @GET
    @Path(PATH_DELIM + VENTAS)
    @Produces(MediaType.APPLICATION_JSON)
	public List<VentaWeb> getVentas() {
    	return transformador.toListVentaWeb(service.listTodosVentas());
	}

    /**
	 * REST: GET,/ventas/{email}, list 
	 * Lista todos los recorridos  realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario
	 */
    @GET
    @Path(PATH_DELIM + VENTAS + PATH_DELIM + POR_EMAIL)
    @Produces(MediaType.APPLICATION_JSON)
	public List<VentaWeb> getVentas(@QueryParam(PARAM_EMAIL_CREADOR) String emailCreador) {
    	return transformador.toListVentaWeb(service.listVentas(emailCreador));
	}

}
