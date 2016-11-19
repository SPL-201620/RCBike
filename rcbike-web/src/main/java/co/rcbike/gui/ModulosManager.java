package co.rcbike.gui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;

import org.primefaces.model.menu.MenuModel;

import co.rcbike.alquiler.model.OperacionesAlquiler;
import co.rcbike.mensajeria.model.OperacionesMensajeria;
import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.ventas.model.OperacionesVentas;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@ApplicationScoped
@JBossLog
public class ModulosManager implements Serializable {

    public enum Modulo {
        autenticacion,
        usuarios,
        //
        venta,
        alquiler,
        configurador,
        reportes,
        mensajeria,
        desplazamientos,
        //
        sinc_redes;

    }

    public static class ModAutenticacion {
        public static final String ENDPNT_AUTENTICACION = "autenticar";
    }

    public static class ModMensajeria {
        public static final String ENDPNT_MENSAJERIA = "mensajeria";
    }

    public static class ModDesplazamientos {
        public static final String ENDPNT_GRUPAL = "grupal";
        public static final String ENDPNT_INDIVIDUAL = "individual";

        public static final String OP_CREAR_RUTA = "";

    }

    private Map<Modulo, SnoopServiceClient> clientesRest = new HashMap<>(10);

    @Getter
    @Inject
    @Snoop(serviceName = OperacionesUsuarios.EP_USUARIOS)
    private SnoopServiceClient usuariosService;

    @Getter
    @Inject
    @Snoop(serviceName = OperacionesVentas.EP_VENTA)
    private SnoopServiceClient ventaService;

    @Getter
    @Inject
    @Snoop(serviceName = OperacionesAlquiler.EP_ALQUILER)
    private SnoopServiceClient alquilerService;

    @Getter
    @Inject
    @Snoop(serviceName = "configurador")
    private SnoopServiceClient configuradorService;

    @Getter
    @Inject
    @Snoop(serviceName = "reportes")
    private SnoopServiceClient reportesService;

    @Getter
    @Inject
    @Snoop(serviceName = OperacionesMensajeria.EP_MENSAJERIA)
    private SnoopServiceClient mensajeriaService;

    @Getter
    @Inject
    @Snoop(serviceName = "desplazamientos")
    private SnoopServiceClient desplazamientosService;

    @Getter
    @Inject
    @Snoop(serviceName = "sinc_redes")
    private SnoopServiceClient sincRedesService;

    @Getter
    @Setter
    private MenuModel model;

    @PostConstruct
    public void init() {
        log.debug("Inicializado " + this.getClass().getName());
        clientesRest.put(Modulo.autenticacion, usuariosService);
        clientesRest.put(Modulo.usuarios, usuariosService);
        clientesRest.put(Modulo.venta, ventaService);
        clientesRest.put(Modulo.alquiler, alquilerService);
        clientesRest.put(Modulo.configurador, configuradorService);
        clientesRest.put(Modulo.reportes, reportesService);
        clientesRest.put(Modulo.mensajeria, mensajeriaService);
        clientesRest.put(Modulo.desplazamientos, desplazamientosService);
        clientesRest.put(Modulo.sinc_redes, sincRedesService);
    }

    public SnoopServiceClient clienteSnoop(Modulo modulo) {
        return clientesRest.get(modulo);
    }

    public WebTarget root(Modulo modulo) {
        return clienteSnoop(modulo).getServiceRoot();
    }
}
