package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "findByidConfiguracion", query = "SELECT p FROM PiezaBicicleta p WHERE p.configuracion.id = :idConfiguracion")})
public class PiezaBicicleta implements Serializable {

    public static final String SQ_findByidConfiguracion = "findByidConfiguracion";
    public static final String SQ_PARAM_ID_CONFIGURACION = "idConfiguracion";
	/* No es necesario llenar */
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoPiezaBicicleta tipo;

	@NotNull
	@NotEmpty
	private String descripcion;

	/* No es necesario llenar */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CONFIGURACION")
	private ConfiguracionBicicleta configuracion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPiezaBicicleta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPiezaBicicleta tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ConfiguracionBicicleta getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(ConfiguracionBicicleta configuracion) {
		this.configuracion = configuracion;
	}

}
