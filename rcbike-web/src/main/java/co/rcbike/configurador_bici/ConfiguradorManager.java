package co.rcbike.configurador_bici;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Entity;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.OperacionesConfiguracion;
import co.rcbike.configurador_bici.model.PiezaConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaWeb;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.web.util.UtilRest;
import lombok.Getter;
import lombok.Setter;

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
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @PostConstruct
    public void init() {
        pieza = null;
        color = null;
        descripcionConfiguracion = null;
        piezas.clear();
        configuracionesList();
    }

    public void piezasConfiguradas() {
        piezas.put(pieza.getTipo(), pieza);
    }

    public void insertConfiguracion() {
        ConfiguracionWeb configuracion = new ConfiguracionWeb();

        configuracion.setDescripcion(descripcionConfiguracion);
        configuracion.setEmailCreador(AutenticacionManager.emailAutenticado());
        Long idConfiguracion = modulosManager.root(Modulo.configurador).path(OperacionesConfiguracion.EP_CONFIGURACION)
                .path(OperacionesConfiguracion.EP_CONFIGURACION).request().post(Entity.json(configuracion), Long.class);

        PiezaConfiguracionWeb piezaConfigurada = new PiezaConfiguracionWeb();
        for (Entry<TipoPiezaBicicleta, PiezaWeb> pieza : piezas.entrySet()) {
            piezaConfigurada.setIdConfiguracion(idConfiguracion);
            piezaConfigurada.setIdPieza(pieza.getValue().getId());
            piezaConfigurada.setDescripcion(pieza.getValue().getDescripcion());
            piezaConfigurada.setTipo(pieza.getValue().getTipo());
            piezaConfigurada.setColor(color);
            modulosManager.root(Modulo.configurador).path(OperacionesConfiguracion.EP_CONFIGURACION)
                    .path("piezaConfiguracion").request().put(Entity.json(piezaConfigurada));

        }
        init();
    }

    public void configuracionesList() {
        listConfiguraciones = modulosManager.root(Modulo.configurador).path(OperacionesConfiguracion.EP_CONFIGURACION)
                .path("configuraciones").path("porEmail")
                .queryParam("emailCreador", AutenticacionManager.emailAutenticado()).request()
                .get(UtilRest.TYPE_LIST_CONFIGURACIONES);

    }
}