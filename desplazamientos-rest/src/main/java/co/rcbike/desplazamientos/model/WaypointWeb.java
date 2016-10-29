package co.rcbike.desplazamientos.model;

import java.io.Serializable;

public class WaypointWeb extends Waypoint implements Serializable {

	private static final long serialVersionUID = -6476217528204318554L;
	
	private Long idRuta;

	public Long getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Long idRuta) {
		this.idRuta = idRuta;
	}
}
