package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findAllWaypoints", query = "SELECT w FROM Waypoint w"),
	@NamedQuery(name = "findWaypointsByIdRuta", query = "SELECT w FROM Waypoint w WHERE w.idRuta = :idRuta") })
public class Waypoint implements Serializable {

	public static final String SQ_findAllWaypoints = "findAllWaypoints";
	public static final String SQ_findWaypointsByIdRuta = "findWaypointsByIdRuta";
	public static final String SQ_PARAM_ID_RUTA = "idRuta";

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private Long idRuta;

	@NotNull
	@Column(precision = 20, scale = 15)
	private BigDecimal latitud;

	@NotNull
	@Column(precision = 20, scale = 15)
	private BigDecimal longitud;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getLatitud() {
		return latitud;
	}

	public void setLatitud(BigDecimal latitudInicio) {
		this.latitud = latitudInicio;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitudInicio) {
		this.longitud = longitudInicio;
	}

	public Long getIdRuta() {
		return idRuta;
	}

	public void setRuta(Long idRuta) {
		this.idRuta = idRuta;
	}

}
