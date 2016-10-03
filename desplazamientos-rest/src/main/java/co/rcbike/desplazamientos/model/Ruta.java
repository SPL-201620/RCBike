package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.DayOfWeek;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findByCreador", query = "SELECT e FROM Ruta e WHERE e.emailCreador = :emailCreador") })
public class Ruta implements Serializable {

	public static final String SQ_findByCreador = "findByCreador";
	public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";

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
	private TipoRecorrido tipo;

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
	private Date tiempoEstimado;

	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private FrecuenciaViaje frecuencia;

	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private DayOfWeek cada;

	@NotNull
	@NotEmpty
	private Date diafinal;

	@NotNull
	@NotEmpty
	private Date hora;
	
	private String emailParticipantes;

	@NotNull
	@NotEmpty
	private Integer calorias;

    @OneToMany(mappedBy="ruta")
	private Set<WaypointRuta> waypoints;

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

	public TipoRecorrido getTipo() {
		return tipo;
	}

	public void setTipo(TipoRecorrido tipo) {
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

	public Date getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(Date tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	
	public FrecuenciaViaje getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(FrecuenciaViaje frecuencia) {
		this.frecuencia = frecuencia;
	}

	public DayOfWeek getCada() {
		return cada;
	}

	public void setCada(DayOfWeek cada) {
		this.cada = cada;
	}

	public Date getDiafinal() {
		return diafinal;
	}

	public void setDiafinal(Date diafinal) {
		this.diafinal = diafinal;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public Set<WaypointRuta> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Set<WaypointRuta> waypoints) {
		this.waypoints = waypoints;
	}

	public String getEmailParticipantes() {
		return emailParticipantes;
	}

	public void setEmailParticipantes(String emailParticipantes) {
		this.emailParticipantes = emailParticipantes;
	}
	
	public Integer getCalorias() {
		return calorias;
	}

	public void setCalorias(Integer calorias) {
		this.calorias = calorias;
	}
	
}
