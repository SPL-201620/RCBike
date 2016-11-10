package co.rcbike.reportes.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Waypoint {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal latitud;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal longitud;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitudInicio) {
        this.latitud = latitudInicio;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitudInicio) {
        this.longitud = longitudInicio;
    }

}
