package co.rcbike.desplazamientos.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class Ruta {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Email
    private String emailCreador;

    @NotNull
    private String nombre;

    private String descripcion;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal latitudInicio;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal longitudInicio;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal latitudFinal;

    @NotNull
    @Column(precision = 20, scale = 15)
    private BigDecimal longitudFinal;

    @NotNull
    private BigDecimal distancia;

    @NotNull
    private Integer tiempoEstimado;

    @NotNull
    private Integer calorias;

    @NotNull
    @NotEmpty
    private String clima;

    /**
     * Grupal: si no es frecuente representa la fecha y hora del recorrido, si
     * es frecuente representa la hora del dia del recorrido. <br>
     * Individial: Fecha en la que se hizo el recorrido.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    /* Solo para grupales */
    @Convert(converter = BooleanSNConverter.class)
    private boolean frecuente;

    private String dias;

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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getLatitudInicio() {
        return latitudInicio;
    }

    public void setLatitudInicio(BigDecimal latitudInicio) {
        this.latitudInicio = latitudInicio;
    }

    public BigDecimal getLongitudInicio() {
        return longitudInicio;
    }

    public void setLongitudInicio(BigDecimal longitudInicio) {
        this.longitudInicio = longitudInicio;
    }

    public BigDecimal getLatitudFinal() {
        return latitudFinal;
    }

    public void setLatitudFinal(BigDecimal latitudFinal) {
        this.latitudFinal = latitudFinal;
    }

    public BigDecimal getLongitudFinal() {
        return longitudFinal;
    }

    public void setLongitudFinal(BigDecimal longitudFinal) {
        this.longitudFinal = longitudFinal;
    }

    public BigDecimal getDistancia() {
        return distancia;
    }

    public void setDistancia(BigDecimal distancia) {
        this.distancia = distancia;
    }

    public Integer getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(Integer tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isFrecuente() {
        return frecuente;
    }

    public void setFrecuente(boolean frecuente) {
        this.frecuente = frecuente;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Ruta [id=" + id + ", emailCreador=" + emailCreador + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", tipo=" + tipo + ", latitudInicio=" + latitudInicio + ", longitudInicio="
                + longitudInicio + ", latitudFinal=" + latitudFinal + ", longitudFinal=" + longitudFinal
                + ", distancia=" + distancia + ", tiempoEstimado=" + tiempoEstimado + ", calorias=" + calorias
                + ", clima=" + clima + ", fecha=" + fecha + ", frecuente=" + frecuente + ", dias=" + dias + "]";
    }

}
