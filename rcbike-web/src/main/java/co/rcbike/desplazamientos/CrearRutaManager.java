package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.desplazamientos.model.Ruta;
import co.rcbike.desplazamientos.model.Tipo;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModDesplazamientos;
import co.rcbike.gui.ModulosManager.Modulo;
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
    private String descRuta;

    @Setter
    @Getter
    private Date fechaHora;

    @Setter
    @Getter
    private List<Integer> dias = new ArrayList<>();

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    public void crearRuta() {
        WebTarget root = modulosManager.root(Modulo.desplazamientos);

        Ruta ruta = new Ruta();
        if (grupal) {
            root = root.path(ModDesplazamientos.ENDPNT_GRUPAL);
            ruta.setFecha(fechaHora == null ? new Date() : fechaHora);
        } else {
            root = root.path(ModDesplazamientos.ENDPNT_INDIVIDUAL);
            ruta.setFecha(new Date());
        }


        ruta.setEmailCreador(AutenticacionManager.emailAutenticado());
        ruta.setNombre(nombreRuta);
        ruta.setDescripcion(descRuta);
        ruta.setTipo(grupal ? Tipo.GRUPAL : Tipo.INDIVIDUAL);

        ruta.setLatitudInicio("0");
        ruta.setLongitudInicio("0");
        ruta.setLatitudFinal("0");
        ruta.setLongitudFinal("0");

        ruta.setDistancia(BigDecimal.ZERO);

        ruta.setTiempoEstimado(1);
        ruta.setCalorias(1);

        ruta.setClima("clima");

        ruta.setFrecuente(repetir);
        ruta.setDias(dias.toString());
        root.request().post(Entity.json(ruta));
    }
}
