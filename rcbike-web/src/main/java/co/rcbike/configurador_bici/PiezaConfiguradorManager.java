package co.rcbike.configurador_bici;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import co.rcbike.configurador_bici.model.ColorWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PiezaConfiguradorManager implements Serializable {

    private Map<TipoPiezaBicicleta, List<PiezaWeb>> piezas = new HashMap<TipoPiezaBicicleta, List<PiezaWeb>>();

    @Getter
    @Setter
    private List<ColorWeb> listColor = new ArrayList<ColorWeb>();

    @Inject
    private ConfiguradorGateway gateway;

    @PostConstruct
    public void init() {
        cargarPiezas();
        color();
    }

    public void cargarPiezas() {
        for (TipoPiezaBicicleta tipoPieza : TipoPiezaBicicleta.values()) {
            List<PiezaWeb> listaPiezas = new ArrayList<PiezaWeb>();
            listaPiezas = gateway.listPiezasByTipo(tipoPieza.toString());
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
        listColor = gateway.listColor();
    }

}
