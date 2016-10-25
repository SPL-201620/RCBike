package co.rcbike.desplazamientos.model;

import java.io.Serializable;

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
@NamedQueries({ @NamedQuery(name = "findAllParticipantes", query = "SELECT p FROM Participante p"),
	@NamedQuery(name = "findParticipantesByIdRuta", query = "SELECT p FROM Participante p WHERE p.idRuta = :idRuta") })
public class Participante implements Serializable {

	public static final String SQ_findAllParticipantes = "findAllParticipantes";
	public static final String SQ_findParticipantesByIdRuta = "findParticipantesByIdRuta";
	public static final String SQ_PARAM_ID_RUTA = "idRuta";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private Long idRuta;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRuta() {
		return idRuta;
	}

	public void setRuta(Long idRuta) {
		this.idRuta = idRuta;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
