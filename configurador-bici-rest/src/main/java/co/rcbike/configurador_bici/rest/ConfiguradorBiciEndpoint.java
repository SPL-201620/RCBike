package co.rcbike.configurador_bici.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.rcbike.configurador_bici.model.ConfiguracionBicicleta;
import co.rcbike.configurador_bici.model.PiezaBicicleta;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.configurador_bici.service.ConfiguradorBiciService;

@Path("/configurar")
@RequestScoped
public class ConfiguradorBiciEndpoint {

    @Inject
    private ConfiguradorBiciService service;
    
    @GET
    @Path("/alive")
    @Produces(MediaType.APPLICATION_JSON)
    public String alive() {
        return "endpoint alive";
    }

    /**
     * Valida una configuracion de bicicleta
     * 
     * @param configuracion
     *            configuracion de una bicicleta
     */
    @GET
    @Path("/validarConfiguracion")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean validarConfiguracion(ConfiguracionBicicleta configuracion) {
        return service.validarConfiguracion(configuracion);
    }
    
    /**
     * Lista todos las configuraciones por un usuario dado su email
     * 
     * @param emailCreador
     *            email del usuario creador
     */
    @GET
    @Path("/listConfiguraciones")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConfiguracionBicicleta> listConfiguraciones(String emailCreador) {
        return service.listConfiguraciones(emailCreador);
    }
    
    /**
     * Permite crear una pieza de bicicleta en una Configuracion de Bicicleta conociendo su id
     * 
     * @param idConfiguracion
     *            identificador de la Configuracion de Bicicleta
     * @param tipo
     *            tipo de la pieza de bicicleta
     * @param descripcion
     *            descripcion de la pieza de bicicleta
     */
    @POST
    @Path("/guardarPieza")
    @Consumes(MediaType.APPLICATION_JSON)
	public void guardarPieza(@QueryParam("idConfiguracion") Long idConfiguracion, @QueryParam("tipo") TipoPiezaBicicleta tipo, @QueryParam("descripcion") String descripcion) {
    	service.guardarPieza(idConfiguracion, tipo, descripcion);
	}

    /**
     * Permite guardar una Configuracion de bicicleta
     * 
     * @param ConfiguracionBicicleta
     *            informacion de la ConfiguracionBicicleta a crear
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	public void guardarConfiguracion(ConfiguracionBicicleta configuracion) {
    	service.guardarConfiguracion(configuracion);
	}

    /**
     * Lista todos las piezas de una Configuracion de Bicicleta
     * 
     * @param idConfiguracion
     *            identificador de la Configuracion de Bicicleta
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public List<PiezaBicicleta> listPiezas(Long idConfiguracion) {
    	return service.listPiezas(idConfiguracion);
	}


}
