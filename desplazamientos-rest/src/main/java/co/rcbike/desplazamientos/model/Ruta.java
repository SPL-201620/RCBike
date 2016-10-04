package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findByTipoAndCreador", query = "SELECT e FROM Ruta e WHERE e.emailCreador = :emailCreador and e.tipo = :tipo"), 
	@NamedQuery(name = "findByTipo", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo") })

public class Ruta implements Serializable {

	public static final String SQ_findByTipoAndCreador = "findByTipoAndCreador";
	public static final String SQ_findByTipo = "findByTipo";
	public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
	public static final String SQ_PARAM_TIPO = "tipo";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	@Email
	private String emailCreador;
	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@NotNull
	@NotEmpty
	private BigDecimal latitudInicio;

	@NotNull
	@NotEmpty
	private BigDecimal longitudInicio;

	@NotNull
	@NotEmpty
	private BigDecimal latitudFinal;

	@NotNull
	@NotEmpty
	private BigDecimal longitudFinal;

	@NotNull
	@NotEmpty
	private BigDecimal distancia;

	@NotNull
	@NotEmpty
	private int tiempoEstimado;

	@NotNull
	@NotEmpty
	private Integer calorias;

	@NotNull
	@NotEmpty
	private String clima;

	/*Solo para individuales*/
	@NotNull
	@NotEmpty
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	/*Solo para grupales*/
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private Frecuencia frecuencia;

	@NotNull
	@NotEmpty
	private String lunes = "N";

	@NotNull
	@NotEmpty
	private String martes = "N";

	@NotNull
	@NotEmpty
	private String miercoles = "N";

	@NotNull
	@NotEmpty
	private String jueves = "N";

	@NotNull
	@NotEmpty
	private String viernes = "N";

	@NotNull
	@NotEmpty
	private String sabado = "N";

	@NotNull
	@NotEmpty
	private String domingo = "N";

	@NotNull
	@NotEmpty
	@Temporal(TemporalType.TIMESTAMP)
	private Date hora;
	
    @OneToMany(mappedBy="ruta")
	private Set<Waypoint> waypoints;

    @OneToMany(mappedBy="ruta")
	private Set<Participante> participantes;

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

	public int getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(int tiempoEstimado) {
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

	public Frecuencia getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getLunes() {
		return lunes;
	}

	public void setLunes(String lunes) {
		this.lunes = lunes;
	}

	public String getMartes() {
		return martes;
	}

	public void setMartes(String martes) {
		this.martes = martes;
	}

	public String getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(String miercoles) {
		this.miercoles = miercoles;
	}

	public String getJueves() {
		return jueves;
	}

	public void setJueves(String jueves) {
		this.jueves = jueves;
	}

	public String getViernes() {
		return viernes;
	}

	public void setViernes(String viernes) {
		this.viernes = viernes;
	}

	public String getSabado() {
		return sabado;
	}

	public void setSabado(String sabado) {
		this.sabado = sabado;
	}

	public String getDomingo() {
		return domingo;
	}

	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Set<Waypoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Set<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public Set<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
	}
}
