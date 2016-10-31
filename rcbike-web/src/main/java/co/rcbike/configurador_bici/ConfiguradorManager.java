package co.rcbike.configurador_bici;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import co.rcbike.configurador_bici.model.OperacionesConfiguracion;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.web.util.UtilRest;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ConfiguradorManager implements Serializable {

	@Getter
	@Setter
	private Long idPieza;

	@Getter
	@Setter
	private PiezaWeb pieza;

	@Getter
	@Setter
	private PiezaWeb marcoSelected;

	@Getter
	@Setter
	private PiezaWeb llantaDelanteraSelected;

	@Getter
	@Setter
	private PiezaWeb llantaTraseraSelected;

	@Getter
	@Setter
	private PiezaWeb frenosSelected;

	@Getter
	@Setter
	private PiezaWeb cambioSelected;

	@Getter
	@Setter
	private PiezaWeb pitoSelected;

	@Getter
	@Setter
	private PiezaWeb luzSelected;

	@Getter
	@Setter
	private List<PiezaWeb> marcos = new ArrayList<PiezaWeb>();

	@Getter
	@Setter
	private List<PiezaWeb> llantaDelanteras = new ArrayList<PiezaWeb>();

	@Getter
	@Setter
	private List<PiezaWeb> llantaTraseras = new ArrayList<PiezaWeb>();

	@Getter
	@Setter
	private List<PiezaWeb> frenos = new ArrayList<PiezaWeb>();

	@Getter
	@Setter
	private List<PiezaWeb> cambios = new ArrayList<PiezaWeb>();

	@Getter
	@Setter
	private List<PiezaWeb> pitos = new ArrayList<PiezaWeb>();

	@Getter
	@Setter
	private List<PiezaWeb> luces = new ArrayList<PiezaWeb>();

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

	@PostConstruct
	public void init() {
		listCambios();
		listFrenos();
		listLlantaDelanteras();
		listLlantaTraseras();
		listLuces();
		listMarcos();
		listPitos();
	}

	public void listMarcos() {
		marcos = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("PARAM_TIPO", TipoPiezaBicicleta.MARCO.toString())
				.request().get(UtilRest.TYPE_LIST_PIEZAS);
	}

	public void listLlantaDelanteras() {
		llantaDelanteras = modulosManager
				.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("PARAM_TIPO",
						TipoPiezaBicicleta.LLANTA_DELANTERA.toString())
				.request().get(UtilRest.TYPE_LIST_PIEZAS);
	}

	public void listLlantaTraseras() {
		llantaTraseras = modulosManager
				.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("PARAM_TIPO",
						TipoPiezaBicicleta.LLANTA_TRASERA.toString()).request()
				.get(UtilRest.TYPE_LIST_PIEZAS);
	}

	public void listFrenos() {
		frenos = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("PARAM_TIPO", TipoPiezaBicicleta.FRENOS.toString())
				.request().get(UtilRest.TYPE_LIST_PIEZAS);
	}

	public void listCambios() {
		cambios = modulosManager
				.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("PARAM_TIPO", TipoPiezaBicicleta.CAMBIOS.toString())
				.request().get(UtilRest.TYPE_LIST_PIEZAS);
	}

	public void listPitos() {
		pitos = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("PARAM_TIPO", TipoPiezaBicicleta.PITO.toString())
				.request().get(UtilRest.TYPE_LIST_PIEZAS);
	}

	public void listLuces() {
		luces = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZAS)
				.path(OperacionesConfiguracion.OP_PIEZAS_BY_TIPO)
				.queryParam("PARAM_TIPO", TipoPiezaBicicleta.LUZ.toString())
				.request().get(UtilRest.TYPE_LIST_PIEZAS);
	}

	public void findPiezaById() {
		pieza = modulosManager.root(Modulo.configurador)
				.path(OperacionesConfiguracion.EP_CONFIGURACION)
				.path(OperacionesConfiguracion.OP_PIEZA_BY_ID)
				.path(idPieza.toString()).request().get(UtilRest.TYPE_PIEZA);

		if (pieza.getTipo() == TipoPiezaBicicleta.MARCO) {
			marcoSelected = pieza;
		} else if (pieza.getTipo() == TipoPiezaBicicleta.FRENOS) {
			frenosSelected = pieza;
		} else if (pieza.getTipo() == TipoPiezaBicicleta.LLANTA_DELANTERA) {
			llantaDelanteraSelected = pieza;
		} else if (pieza.getTipo() == TipoPiezaBicicleta.LLANTA_TRASERA) {
			llantaTraseraSelected = pieza;
		} else if (pieza.getTipo() == TipoPiezaBicicleta.MARCO) {
			marcoSelected = pieza;
		} else if (pieza.getTipo() == TipoPiezaBicicleta.LUZ) {
			luzSelected = pieza;
		} else if (pieza.getTipo() == TipoPiezaBicicleta.PITO) {
			pitoSelected = pieza;
		}

	}

}