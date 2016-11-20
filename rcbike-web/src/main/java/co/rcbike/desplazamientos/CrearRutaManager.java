package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.Tipo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class CrearRutaManager implements Serializable {

    @Setter
    @Getter
    private boolean repetir;

    @Setter
    @Getter
    private boolean grupal;

    @Setter
    @Getter
    private String nombreRuta;

    @Setter
    @Getter
    private String distancia;

    @Setter
    @Getter
    private Integer tiempo;

    @Setter
    @Getter
    private String calorias;

    @Setter
    @Getter
    private String descRuta;

    @Setter
    @Getter
    private Date fechaHora;

    @Setter
    @Getter
    private List<Integer> dias = new ArrayList<>();

    @Inject
    private DesplazamientosGateway gateway;

    @Getter
    @Setter
    @ManagedProperty(value = "#{mapaManager}")
    private MapaManager mapaManager;

    @Getter
    @Setter
    private String clima;

    @PostConstruct
    public void init() {
        log.debug(this);
    }

    public String crearRuta() {
        RutaWeb ruta = new RutaWeb();
        if (grupal) {
            // desplazamientoRest.getServiceRoot().path("individual").path("grupal");
            // desplazamientoRest =
            // desplazamientoRest.path(ModDesplazamientos.ENDPNT_GRUPAL);
            ruta.setFecha(fechaHora == null ? new Date() : fechaHora);
        } else {
            // desplazamientoRest.getServiceRoot().path("individual").path("rutaIndividual");
            // root = root.path(ModDesplazamientos.ENDPNT_INDIVIDUAL);
            ruta.setFecha(new Date());
        }

        ruta.setEmailCreador(AutenticacionManager.emailAutenticado());
        ruta.setNombre(nombreRuta);
        ruta.setDescripcion(descRuta);
        ruta.setTipo(grupal ? Tipo.GRUPAL : Tipo.INDIVIDUAL);

        ruta.setLatitudInicio(
                new BigDecimal(mapaManager.getOrigen().getLatlng().getLat()).setScale(8, RoundingMode.HALF_UP));
        ruta.setLongitudInicio(
                new BigDecimal(mapaManager.getOrigen().getLatlng().getLng()).setScale(8, RoundingMode.HALF_UP));
        ruta.setLatitudFinal(
                new BigDecimal(mapaManager.getDestino().getLatlng().getLat()).setScale(8, RoundingMode.HALF_UP));
        ruta.setLongitudFinal(
                new BigDecimal(mapaManager.getDestino().getLatlng().getLng()).setScale(8, RoundingMode.HALF_UP));

        java.lang.System.out.print("\nDistancia: " + distancia);
        try {
            ruta.setDistancia(new BigDecimal(distancia));
        } catch (Exception e) {
            ruta.setDistancia(new BigDecimal("200.0"));
        }
        ruta.setTiempoEstimado(tiempo);
        ruta.setCalorias(Integer.parseInt(calorias));

        ruta.setClima(clima);

        ruta.setFrecuente(repetir);
        ruta.setDias(dias.size() == 0 ? null : dias.toString());

        java.lang.System.out.print("----Entidad:" + ruta.toString());

        Response response;

        if (grupal) {
            gateway.crearRutaGrupal(ruta);
        } else {
            gateway.crearRutaIndividual(ruta);
        }

        return "recorrido";
    }

    public void rutaCalculada() {
        this.clima = gateway.clima(mapaManager.getOrigen().getLatlng().getLat(),
                mapaManager.getOrigen().getLatlng().getLng());
    }

}
