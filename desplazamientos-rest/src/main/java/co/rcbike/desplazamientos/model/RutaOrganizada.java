package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.time.DayOfWeek;
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
@NamedQueries({ @NamedQuery(name = "findByCreador3", query = "SELECT e FROM RutaOrganizada e WHERE e.emailCreador = :emailCreador") })
public class RutaOrganizada implements Serializable {

	public static final String SQ_findByCreador = "findByCreador3";
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
	
}
