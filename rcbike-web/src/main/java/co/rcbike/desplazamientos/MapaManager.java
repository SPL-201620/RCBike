package co.rcbike.desplazamientos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.Marker;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class MapaManager implements Serializable{

    @Getter
    @Setter
    public Marker origen;
    
    @Getter
    @Setter
    public Marker destino;
    
    @Getter
    @Setter
    public String distancia;
    
    @PostConstruct
    public void reset(){
        origen=null;
        destino=null;
        distancia=null;
    }
}
