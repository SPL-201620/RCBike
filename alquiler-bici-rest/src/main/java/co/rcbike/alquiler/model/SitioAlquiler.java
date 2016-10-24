package co.rcbike.alquiler.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
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
@NamedQueries({ @NamedQuery(name = "findConfiguracionesBicicleta", query = "SELECT c FROM SitioAlquiler c"),
		@NamedQuery(name = "findByCreador", query = "SELECT c FROM SitioAlquiler c WHERE c.emailCreador = :emailCreador"),
		@NamedQuery(name = "findByPunto", query = "SELECT s FROM SitioAlquiler s WHERE "
				+ "s.latitud >= :latitudInicio and s.latitud <= :latitudFinal "
				+ "and s.longitud >= :longitudInicio and s.longitud <= :longitudFinal") })
public class SitioAlquiler implements Serializable {

	public static final String SQ_find = "findConfiguracionesBicicleta";
	public static final String SQ_findByCreador = "findByCreador";
	public static final String SQ_findByPunto = "findByPunto";
	public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
	public static final String SQ_PARAM_LATITUD_INICIO = "latitudInicio";
	public static final String SQ_PARAM_LATITUD_FINAL = "latitudFinal";
	public static final String SQ_PARAM_LONGITUD_INICIO = "longitudInicio";
	public static final String SQ_PARAM_LONGITUD_FINAL = "longitudFinal";

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
