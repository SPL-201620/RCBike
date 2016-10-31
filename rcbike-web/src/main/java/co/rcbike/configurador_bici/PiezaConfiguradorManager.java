package co.rcbike.configurador_bici;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.gui.ModulosManager;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PiezaConfiguradorManager implements Serializable {

	private Map<TipoPiezaBicicleta, List<PiezaWeb>> piezas = new HashMap<TipoPiezaBicicleta, List<PiezaWeb>>();

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

	public List<PiezaWeb> piezaPorTipo(TipoPiezaBicicleta tipo) {
		return piezas.get(tipo);

	}

	public List<TipoPiezaBicicleta> tiposPiezasBiclicleta() {
		return Arrays.asList(TipoPiezaBicicleta.values());

	}
}
