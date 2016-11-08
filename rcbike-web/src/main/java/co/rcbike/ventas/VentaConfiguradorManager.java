package co.rcbike.ventas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.OperacionesConfiguracion;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.web.util.UtilRest;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VentaConfiguradorManager implements Serializable {

	@Getter
	@Setter
	private List<ConfiguracionWeb> listConfiguraciones = new ArrayList<ConfiguracionWeb>();

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

	@PostConstruct
	public void init() {
		configuracionesList();
	}

	public void configuracionesList() {
		listConfiguraciones = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path("configuraciones")
				.queryParam(AutenticacionManager.emailAutenticado()).request()
				.get(UtilRest.TYPE_LIST_CONFIGURACIONES);

	}
}
