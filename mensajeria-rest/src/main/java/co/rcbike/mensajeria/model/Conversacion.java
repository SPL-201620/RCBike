package co.rcbike.mensajeria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Conversacion implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	@Email
	private String emailEmisor;

	@NotNull
	@NotEmpty
	@Email
	private String emailReceptor;

	@OneToMany(mappedBy = "conversacion", fetch = FetchType.EAGER)
	private List<Mensaje> mensajes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailEmisor() {
		return emailEmisor;
	}

	public void setEmailEmisor(String emailEmisor) {
		this.emailEmisor = emailEmisor;
	}

	public String getEmailReceptor() {
		return emailReceptor;
	}

	public void setEmailReceptor(String emailReceptor) {
		this.emailReceptor = emailReceptor;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

}
