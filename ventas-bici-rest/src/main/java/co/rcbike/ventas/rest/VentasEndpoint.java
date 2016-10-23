package co.rcbike.ventas.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.ventas.model.PiezaVentaBicicleta;
import co.rcbike.ventas.model.VentaBicicleta;
import co.rcbike.ventas.service.VentasService;

@Path("/venta")
@RequestScoped
public class VentasEndpoint {

    @Inject
    private VentasService service;
    
	@GET
	@Path("/alive")
	@Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
	}

	/**
	 * Lista todas las ventas de bicicleta
	 * 
	 */
	@GET
	@Path("/listTodasVentas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VentaBicicleta> listTodasVentas() {
		return service.listTodasVentas();
	}

	/**
	 * Lista todas las ventas por un usuario dado su email
	 * 
	 * @param emailCreador email del usuario creador
	 */
	@POST
	@Path("/listVentas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VentaBicicleta> listVentas(String emailCreador) {
		return service.listVentas(emailCreador);
	}

    /**
     * Permite crear una pieza de bicicleta 
     * 
     * @param piezaVentaBicicleta informacion de la pieza de la bicicleta
     */
	@POST
	@Path("/guardarPieza")
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarPieza(PiezaVentaBicicleta piezaVentaBicicleta) {
		service.guardarPieza(piezaVentaBicicleta);
	}

	/**
	 * Permite guardar una Venta de bicicleta
	 * 
	 * @param VentaBicicleta informacion de la VentaBicicleta a crear
	 */
	@POST
	@Path("/guardarVenta")
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardarVenta(VentaBicicleta venta) {
		service.guardarVenta(venta);
	}

	/**
	 * Lista todos las piezas de una Venta de Bicicleta
	 * 
	 * @param idVenta identificador de la Venta de Bicicleta
	 */
	@POST
	@Path("/listPiezas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PiezaVentaBicicleta> listPiezas(Long idVenta) {
		return service.listPiezas(idVenta);
	}

	/**
	 * Lista todos las piezas de bicicletas
	 * 
	 */
	@GET
	@Path("/listTodasPiezas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PiezaVentaBicicleta> listTodasPiezas() {
		return service.listTodasPiezas();
	}

}
