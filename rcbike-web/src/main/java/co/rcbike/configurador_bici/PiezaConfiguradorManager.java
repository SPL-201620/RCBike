package co.rcbike.configurador_bici;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.configurador_bici.model.ColorWeb;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.OperacionesConfiguracion;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.web.util.UtilRest;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PiezaConfiguradorManager implements Serializable {

	private Map<TipoPiezaBicicleta, List<PiezaWeb>> piezas = new HashMap<TipoPiezaBicicleta, List<PiezaWeb>>();

	@Getter
	@Setter
	private List<ColorWeb> listColor = new ArrayList<ColorWeb>();

	@Getter
	@Setter
	private List<ConfiguracionWeb> listConfiguraciones = new ArrayList<ConfiguracionWeb>();

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

	@PostConstruct
	public void init() {
		cargarPiezas();
		color();
		configuracionesList();
	}

	public void cargarPiezas() {

		for (TipoPiezaBicicleta tipoPieza : TipoPiezaBicicleta.values()) {
			List<PiezaWeb> listaPiezas = new ArrayList<PiezaWeb>();
			listaPiezas = modulosManager.root(Modulo.configurador)
					.path(OperacionesConfiguracion.EP_CONFIGURACION)
					.path(OperacionesConfiguracion.OP_PIEZAS)
					.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
					.queryParam("tipo", tipoPieza.toString()).request()
					.get(UtilRest.TYPE_LIST_PIEZAS);

			piezas.put(tipoPieza, listaPiezas);

		}

	}

	public List<PiezaWeb> piezaPorTipo(TipoPiezaBicicleta tipo) {
		return piezas.get(tipo);

	}

	public List<TipoPiezaBicicleta> tiposPiezasBiclicleta() {
		return Arrays.asList(TipoPiezaBicicleta.values());

	}

	public void color() {
		listColor = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path("colores").request().get(UtilRest.TYPE_LIST_COLOR);

	}

	public void configuracionesList() {
		listConfiguraciones = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path("configuraciones")
				.queryParam(AutenticacionManager.emailAutenticado()).request()
				.get(UtilRest.TYPE_LIST_CONFIGURACIONES);

	}

}
