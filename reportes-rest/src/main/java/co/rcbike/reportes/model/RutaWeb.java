package co.rcbike.reportes.model;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RutaWeb extends Ruta implements Serializable {

	private static final long serialVersionUID = -333741673965970120L;

    private Set<WaypointWeb> waypoints;
    
    private Set<ParticipanteWeb> participantes;

	public Set<WaypointWeb> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Set<WaypointWeb> waypoints) {
		this.waypoints = waypoints;
	}

	public Set<ParticipanteWeb> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<ParticipanteWeb> participantes) {
		this.participantes = participantes;
	}
	
}
