package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
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
@NamedQueries({ @NamedQuery(name = "findByIdRuta", query = "SELECT e FROM WaypointRuta e WHERE e.idRuta = :idRuta") })
public class WaypointRuta implements Serializable {

	public static final String SQ_findByIdRuta = "findByIdRuta";
	public static final String SQ_PARAM_ID_RUTA = "idRuta";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	private Long idRuta;

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

	public Long getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Long idRuta) {
		this.idRuta = idRuta;
	}

}
