package co.rcbike.desplazamientos;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.rcbike.desplazamientos.model.RutaWeb;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "dtSelectionView")
@ViewScoped
public class SelectionView implements Serializable {

    private RutaWeb selectedCar;
    private List<RutaWeb> selectedCars;
    // private List<RutaWeb> rutas;

    @Getter
    @Setter
    @ManagedProperty(value = "#{RutaManager}")
    private RutaManager service;
        
    @PostConstruct
    public void init() {
        selectedCars = service.getRutas();
    }

    public void setService(RutaManager service) {
        this.service = service;
    }

    public RutaWeb getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(RutaWeb selectedCar) {
        this.selectedCar = selectedCar;
    }

    public List<RutaWeb> getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(List<RutaWeb> selectedCars) {
        this.selectedCars = selectedCars;
    }

    public String submit(String id) {
        return "/site/pb/desplazamiento.xhtml?faces-redirect=true&id=" + id;
    }
}