package co.rcbike.desplazamientos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.Marker;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@SessionScoped
public class MapaManager implements Serializable {

    @Getter
    @Setter
    public static Marker origen;

    @Getter
    @Setter
    public static Marker destino;

    @Getter
    @Setter
    public static String distancia;

    @PostConstruct
    public void reset() {
        origen = null;
        destino = null;
        distancia = null;
    }
}
