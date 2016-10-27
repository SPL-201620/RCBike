package co.rcbike.desplazamientos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity(name = "Waypoint")
@Table(name = "Waypoint")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findAllWaypoints", query = "SELECT w FROM Waypoint w"),
	@NamedQuery(name = "findWaypointsByIdRuta", query = "SELECT w FROM Waypoint w WHERE w.ruta.id = :idRuta") })
public class WaypointJpa extends Waypoint implements Serializable {

	public static final String SQ_findAllWaypoints = "findAllWaypoints";
	public static final String SQ_findWaypointsByIdRuta = "findWaypointsByIdRuta";
	public static final String SQ_PARAM_ID_RUTA = "idRuta";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRuta")
	private RutaJpa ruta;

	public RutaJpa getRuta() {
		return ruta;
	}

	public void setRuta(RutaJpa ruta) {
		this.ruta = ruta;
	}
	
}
