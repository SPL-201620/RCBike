package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import eu.agilejava.snoop.client.SnoopServiceClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ViewScoped
@JBossLog
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

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        java.lang.System.out.println(".-----inicio Ruta manager\n");

        this.email = AutenticacionManager.emailAutenticado();

        java.lang.System.out.println("El Email:" + this.email);

        SnoopServiceClient desplazamientoRest = modulosManager.clienteSnoop(Modulo.desplazamientos);
        
        rutas = desplazamientoRest.getServiceRoot().path("individual").path("rutasIndividuales").path("porEmail")
                .queryParam("emailCreador", email).request().get(List.class);
        
        java.lang.System.out.println(rutas.toString());
        java.lang.System.out.println(".-----fin Ruta manager\n");
    }
}
