package co.rcbike.ventas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findAllPiezas", query = "SELECT p FROM PiezaVentaBicicleta p"),
		@NamedQuery(name = "findByIdVenta", query = "SELECT p FROM PiezaVentaBicicleta p WHERE p.idVenta = :idVenta") })
public class PiezaVentaBicicleta implements Serializable {

	public static final String SQ_findAllPiezas = "findAllPiezas";
	public static final String SQ_findByIdVenta = "findByIdVenta";
	public static final String SQ_PARAM_ID_CONFIGURACION = "idVenta";
	
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

	private Long idVenta;
	

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

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idConfiguracion) {
		this.idVenta = idConfiguracion;
	}

}
