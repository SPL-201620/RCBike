package co.rcbike.configurador_bici.model;

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
@NamedQueries({ @NamedQuery(name = "findConfiguracionesBicicleta", query = "SELECT c FROM ConfiguracionBicicleta c"),
		@NamedQuery(name = "findByCreador", query = "SELECT c FROM ConfiguracionBicicleta c WHERE c.emailCreador = :emailCreador") })
public class ConfiguracionBicicleta implements Serializable {

	public static final String SQ_find = "findConfiguracionesBicicleta";
	public static final String SQ_findByCreador = "findByCreador";
	public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";

	/* No es necesario llenar */
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	@Email
	private String emailCreador;

	@NotNull
	@NotEmpty
	private String descripcion;

	//@OneToMany(mappedBy = "configuracion", fetch = FetchType.LAZY)
	//private Set<PiezaBicicleta> piezasBicicleta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailCreador() {
		return emailCreador;
	}

	public void setEmailCreador(String email) {
		this.emailCreador = email;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
