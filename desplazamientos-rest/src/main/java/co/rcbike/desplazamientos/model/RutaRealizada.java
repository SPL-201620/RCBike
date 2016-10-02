package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@NamedQueries({ @NamedQuery(name = "findByCreador2", query = "SELECT e FROM RutaRealizada e WHERE e.emailCreador = :emailCreador") })
public class RutaRealizada implements Serializable {

	public static final String SQ_findByCreador = "findByCreador2";
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
	private Date fecha;
	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private TipoRecorrido tipo;

	@NotNull
	@NotEmpty
	private Integer calorias;

	@NotNull
	@NotEmpty
	private String clima;

	@NotNull
	@NotEmpty
	private Integer numeroCompaneros;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoRecorrido getTipo() {
		return tipo;
	}

	public void setTipo(TipoRecorrido tipo) {
		this.tipo = tipo;
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

	public Integer getNumeroCompaneros() {
		return numeroCompaneros;
	}

	public void setNumeroCompaneros(Integer numeroCompaneros) {
		this.numeroCompaneros = numeroCompaneros;
	}

}
