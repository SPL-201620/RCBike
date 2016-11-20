package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.desplazamientos.model.RutaWeb;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name = "RutaManager")
@ApplicationScoped
public class RutaManager implements Serializable {
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
        java.lang.System.out.println(".-----inicio Ruta manager\n");

        this.email = AutenticacionManager.emailAutenticado();

        java.lang.System.out.println("El Email:" + this.email);

        rutas = gateway.listIndividualesByEmail(email);

        java.lang.System.out.println(rutas.toString());
        java.lang.System.out.println(".-----fin Ruta manager\n");
    }
}
