package co.rcbike.alquiler.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class SitioAlquiler {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Email
    private String emailCreador;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal latitud;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal longitud;

    @NotNull
    @NotEmpty
    private String tarifas;

    @NotNull
    @NotEmpty
    private String limiteRecorridos;

    @NotNull
    @NotEmpty
    private String estacionesEntrega;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailCreador() {
        return emailCreador;
    }

    public void setEmailCreador(String emailCreador) {
        this.emailCreador = emailCreador;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getTarifas() {
        return tarifas;
    }

    public void setTarifas(String tarifas) {
        this.tarifas = tarifas;
    }

    public String getLimiteRecorridos() {
        return limiteRecorridos;
    }

    public void setLimiteRecorridos(String limiteRecorridos) {
        this.limiteRecorridos = limiteRecorridos;
    }

    public String getEstacionesEntrega() {
        return estacionesEntrega;
    }

    public void setEstacionesEntrega(String estacionesEntrega) {
        this.estacionesEntrega = estacionesEntrega;
    }
}
