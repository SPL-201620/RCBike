package co.rcbike.configurador_bici;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import co.rcbike.configurador_bici.model.ColorWeb;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.OperacionesConfiguracion;
import co.rcbike.configurador_bici.model.PiezaConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class ConfiguradorGateway extends RcbikeRestGateway {

	public static final GenericType<List<ConfiguracionWeb>> TYPE_LIST_CONFIGURACIONES = new GenericType<List<ConfiguracionWeb>>() {
	};

	public static final GenericType<List<PiezaWeb>> TYPE_LIST_PIEZAS = new GenericType<List<PiezaWeb>>() {
	};

	public static final GenericType<List<ColorWeb>> TYPE_LIST_COLOR = new GenericType<List<ColorWeb>>() {
	};

	@Inject
	@Snoop(serviceName = "configurador")
	private SnoopServiceClient service;

	@Override
	protected SnoopServiceClient client() {
		return service;
	}

	public Long crearConfiguracion(ConfiguracionWeb configuracion) {
		return webTarget().path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.EP_CONFIGURACION).request()
				.post(Entity.json(configuracion), Long.class);
	}

	public void agregarParteConfiguracion(PiezaConfiguracionWeb piezaConfigurada) {
		webTarget().path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path("piezaConfiguracion").request()
				.put(Entity.json(piezaConfigurada));
	}

	public List<ConfiguracionWeb> listConfiguracionesByEmail(String email) {
		return webTarget().path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path("configuraciones").path("porEmail")
				.queryParam("emailCreador", email).request()
				.get(TYPE_LIST_CONFIGURACIONES);
	}

	public List<PiezaWeb> listPiezasByTipo(String tipoPieza) {
		return webTarget().path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("tipo", tipoPieza).request().get(TYPE_LIST_PIEZAS);
	}

	public List<ColorWeb> listColor() {
		return webTarget().path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path("colores").request().get(TYPE_LIST_COLOR);
	}

	public void deleteConfiguracion(Long id) {
		webTarget().path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.EP_CONFIGURACION).path(id + "")
				.request().delete();
	}
}
