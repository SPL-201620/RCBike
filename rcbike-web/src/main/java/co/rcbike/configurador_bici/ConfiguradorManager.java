package co.rcbike.configurador_bici;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.context.RequestContext;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ConfiguradorManager implements Serializable {

	@Getter
	@Setter
	private String descripcionConfiguracion;

	@Getter
	@Setter
	private String color;

	@Getter
	@Setter
	private PiezaWeb pieza;

	@Getter
	@Setter
	private Map<TipoPiezaBicicleta, PiezaWeb> piezas = new HashMap<TipoPiezaBicicleta, PiezaWeb>();

	@Getter
	@Setter
	private List<ConfiguracionWeb> listConfiguraciones;

	@Getter
	@Setter
	private Long identificador;

	@Inject
	private ConfiguradorGateway gateway;

	@PostConstruct
	public void init() {
		configuracionesList();
	}

	public void piezasConfiguradas() {
		piezas.put(pieza.getTipo(), pieza);
	}

	public void insertConfiguracion() {

		String validarConfig = new String();
		for (Entry<TipoPiezaBicicleta, PiezaWeb> piezas : piezas.entrySet()) {
			validarConfig = validarConfig + piezas.getValue().getDescripcion()
					+ ",";
		}
		String cadenaFinal = validarConfig.substring(0,
				validarConfig.length() - 1);

		Boolean validarConfiguracion = new Boolean(false);

		validarConfiguracion = gateway.validacionConfiguracion(cadenaFinal);

		if (validarConfiguracion == true) {

			ConfiguracionWeb configuracion = new ConfiguracionWeb();
			configuracion.setDescripcion(descripcionConfiguracion);
			configuracion.setEmailCreador(AutenticacionManager
					.emailAutenticado());
			Long idConfiguracion = gateway.crearConfiguracion(configuracion);

			PiezaConfiguracionWeb piezaConfigurada = new PiezaConfiguracionWeb();
			for (Entry<TipoPiezaBicicleta, PiezaWeb> pieza : piezas.entrySet()) {
				piezaConfigurada.setIdConfiguracion(idConfiguracion);
				piezaConfigurada.setIdPieza(pieza.getValue().getId());
				piezaConfigurada.setDescripcion(pieza.getValue()
						.getDescripcion());
				piezaConfigurada.setTipo(pieza.getValue().getTipo());
				piezaConfigurada.setColor(color);
				gateway.agregarParteConfiguracion(piezaConfigurada);
			}
			pieza = null;
			color = null;
			descripcionConfiguracion = null;
			piezas.clear();
			configuracionesList();
		} else {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Configuracion no valida", " Intente nuevamente");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			pieza = null;
			color = null;
			descripcionConfiguracion = null;
			piezas.clear();

		}
	}

	public void configuracionesList() {
		listConfiguraciones = gateway
				.listConfiguracionesByEmail(AutenticacionManager
						.emailAutenticado());

	}

	public void deleteConfiguracion(Long id) {
		gateway.deleteConfiguracion(id);
		configuracionesList();

	}
}