package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
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
@NamedQueries({ @NamedQuery(name = "findByIdRuta", query = "SELECT e FROM WaypointRuta e WHERE e.ruta.id = :idRuta") })
public class WaypointRuta implements Serializable {

	public static final String SQ_findByIdRuta = "findByIdRuta";
	public static final String SQ_PARAM_ID_RUTA = "idRuta";

	/*No es necesario llenar*/
	@Id
	@GeneratedValue
	private Long id;

	/*No es necesario llenar*/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RUTA")
	private Ruta ruta;

	@NotNull
	@NotEmpty
	private BigDecimal latitudInicio;

	@NotNull
	@NotEmpty
	private BigDecimal longitudInicio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

}
