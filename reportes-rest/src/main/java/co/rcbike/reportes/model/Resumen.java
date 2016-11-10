package co.rcbike.reportes.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Resumen {

    private Long id;

    private String emailCreador;

    private Date fechaInicio;

    private Date fechaFinal;

    private BigDecimal kilometrosRecorridos = new BigDecimal(0);

    private BigDecimal velocidadPromedio = new BigDecimal(0);

    private Long viajesIndividuales = new Long(0);

    private Long viajesGrupales = new Long(0);

    private Long companerosPromedio = new Long(0);

    private Integer caloriasQuemadas = new Integer(0);

    private List<RutaWeb> rutas = new ArrayList<>();

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

    public BigDecimal getKilometrosRecorridos() {
        return kilometrosRecorridos;
    }

    public void setKilometrosRecorridos(BigDecimal kilometrosRecorridos) {
        this.kilometrosRecorridos = kilometrosRecorridos;
    }

    public BigDecimal getVelocidadPromedio() {
        return velocidadPromedio;
    }

    public void setVelocidadPromedio(BigDecimal velocidadPromedio) {
        this.velocidadPromedio = velocidadPromedio;
    }

    public Long getViajesIndividuales() {
        return viajesIndividuales;
    }

    public void setViajesIndividuales(Long viajesIndividuales) {
        this.viajesIndividuales = viajesIndividuales;
    }

    public Long getViajesGrupales() {
        return viajesGrupales;
    }

    public void setViajesGrupales(Long viajesGrupales) {
        this.viajesGrupales = viajesGrupales;
    }

    public Long getCompanerosPromedio() {
        return companerosPromedio;
    }

    public void setCompanerosPromedio(Long companerosPromedio) {
        this.companerosPromedio = companerosPromedio;
    }

    public Integer getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public void setCaloriasQuemadas(Integer caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<RutaWeb> getRutas() {
        return rutas;
    }

    public void setRutas(List<RutaWeb> rutas) {
        this.rutas = rutas;
    }
}
