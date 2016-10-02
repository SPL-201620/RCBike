package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findByCreador4", query = "SELECT e FROM Ruta e WHERE e.emailCreador = :emailCreador") })
public class Ruta implements Serializable {

	public static final String SQ_findByCreador = "findByCreador4";
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
}
