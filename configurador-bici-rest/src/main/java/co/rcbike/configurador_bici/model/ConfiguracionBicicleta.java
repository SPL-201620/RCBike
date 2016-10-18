package co.rcbike.configurador_bici.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@NamedQueries({
    @NamedQuery(name = "findByCreador", query = "SELECT c FROM ConfiguracionBicicleta c WHERE c.emailCreador = :emailCreador")})
public class ConfiguracionBicicleta implements Serializable {

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

	@OneToMany(mappedBy = "configuracion", fetch = FetchType.EAGER)
	private Set<PiezaBicicleta> piezasBicicleta;

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

	public Set<PiezaBicicleta> getPiezasBicicleta() {
		return piezasBicicleta;
	}

	public void setPiezasBicicleta(Set<PiezaBicicleta> piezasBicicleta) {
		this.piezasBicicleta = piezasBicicleta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
