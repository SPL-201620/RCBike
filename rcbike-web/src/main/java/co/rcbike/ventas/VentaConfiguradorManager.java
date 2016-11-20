package co.rcbike.ventas;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.configurador_bici.ConfiguradorGateway;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.gui.ModulosManager;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VentaConfiguradorManager implements Serializable {

    @Getter
    @Setter
    private List<ConfiguracionWeb> listConfiguraciones;

    @Inject
    private ConfiguradorGateway gateway;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @PostConstruct
    public void init() {
        gateway.listConfiguracionesByEmail(AutenticacionManager.emailAutenticado());
    }

}
