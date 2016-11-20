package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.desplazamientos.model.RutaWeb;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class RutaGrupalManager implements Serializable {
    @Getter
    @Setter
    private List<RutaWeb> rutas;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private RutaWeb ruta;

    @Inject
    private DesplazamientosGateway gateway;

    @PostConstruct
    public void init() {
        this.email = AutenticacionManager.emailAutenticado();
        rutas = gateway.listGrupales();
    }
}
