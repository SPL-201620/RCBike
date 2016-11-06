package co.rcbike.configurador_bici;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Entity;

import lombok.Getter;
import lombok.Setter;
import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.OperacionesConfiguracion;
import co.rcbike.configurador_bici.model.PiezaConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ConfiguradorManager implements Serializable {

	@Getter
	@Setter
	private Long idConfiguracion;

	@Getter
	@Setter
	private ConfiguracionWeb configuracion = new ConfiguracionWeb();

	@Getter
	@Setter
	private String descripcionConfiguracion;

	@Getter
	@Setter
	private String color;

	@Getter
	@Setter
	public PiezaConfiguracionWeb piezaConfigurada = new PiezaConfiguracionWeb();

	@Getter
	@Setter
	private PiezaWeb pieza = new PiezaWeb();

	@Getter
	@Setter
	private Map<TipoPiezaBicicleta, PiezaWeb> piezas = new HashMap<TipoPiezaBicicleta, PiezaWeb>();

	private PiezaConfiguradorManager piezaConfiguradorManager = new PiezaConfiguradorManager();

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

	public void piezasConfiguradas() {
		piezas.put(pieza.getTipo(), pieza);
	}

	public void insertConfiguracion() {
		configuracion.setDescripcion(descripcionConfiguracion);
		configuracion.setEmailCreador(AutenticacionManager.emailAutenticado());
		idConfiguracion = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.EP_CONFIGURACION).request()
				.post(Entity.json(configuracion), Long.class);

		for (Entry<TipoPiezaBicicleta, PiezaWeb> pieza : piezas.entrySet()) {
			piezaConfigurada.setIdConfiguracion(idConfiguracion);
			piezaConfigurada.setIdPieza(pieza.getValue().getId());
			piezaConfigurada.setDescripcion(pieza.getValue().getDescripcion());
			piezaConfigurada.setTipo(pieza.getValue().getTipo());
			piezaConfigurada.setColor(color);
			modulosManager.root(Modulo.configurador)
					.path(OperacionesConfiguracion.EP_CONFIGURACION)
					.path("piezaConfiguracion").request()
					.put(Entity.json(piezaConfigurada));

		}
		piezaConfiguradorManager.configuracionesList();
	}
}