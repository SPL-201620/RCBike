package co.rcbike.desplazamientos.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import co.rcbike.desplazamientos.model.ParticipanteJpa;
import co.rcbike.desplazamientos.model.ParticipanteWeb;
import co.rcbike.desplazamientos.model.RutaJpa;
import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.desplazamientos.model.WaypointJpa;
import co.rcbike.desplazamientos.model.WaypointWeb;

@RequestScoped
public class TransformadorDesplazamientos extends Transformador {

	@Inject
    private  DesplazamientosService service;

	public  WaypointWeb toWaypointWeb(WaypointJpa waypointJpa) {
		WaypointWeb result = crearObjetoDestino(waypointJpa, WaypointWeb.class);
		if (waypointJpa.getRuta() != null) {
			result.setIdRuta(waypointJpa.getRuta().getId());
		}
		return result;
	}

	public  WaypointJpa toWaypointJpa(WaypointWeb waypointWeb) {
		WaypointJpa result = crearObjetoDestino(waypointWeb, WaypointJpa.class);
		if (waypointWeb.getIdRuta() != null) {
			result.setRuta(service.findRuta(waypointWeb.getIdRuta()));
		}
		return result;
	}

	public  ParticipanteWeb toParticipanteWeb(ParticipanteJpa participanteJpa) {
		ParticipanteWeb result = crearObjetoDestino(participanteJpa, ParticipanteWeb.class);
		if (participanteJpa.getRuta() != null) {
			result.setIdRuta(participanteJpa.getRuta().getId());
		}
		return result;
	}

	public  ParticipanteJpa toParticipanteJpa(ParticipanteWeb participanteWeb) {
		ParticipanteJpa result = crearObjetoDestino(participanteWeb, ParticipanteJpa.class);
		if (participanteWeb.getIdRuta() != null) {
			result.setRuta(service.findRuta(participanteWeb.getIdRuta()));
		}
		return result;
	}

	public  RutaWeb toRutaWeb(RutaJpa rutaJpa) {
		RutaWeb result = crearObjetoDestino(rutaJpa, RutaWeb.class);
		if (rutaJpa.getWaypoints() != null) {
			Set<WaypointWeb> waypoints = new HashSet<>();
			for (WaypointJpa waypoint : rutaJpa.getWaypoints()) {
				waypoints.add(toWaypointWeb(waypoint));
			}
			result.setWaypoints(waypoints);
		}
		if (rutaJpa.getParticipantes() != null) {
			Set<ParticipanteWeb> participantes = new HashSet<>();
			for (ParticipanteJpa participante : rutaJpa.getParticipantes()) {
				participantes.add(toParticipanteWeb(participante));
			}
			result.setParticipantes(participantes);
		}
		return result;
	}

	public  RutaJpa toRutaJpa(RutaWeb rutaWeb) {
		RutaJpa result = crearObjetoDestino(rutaWeb, RutaJpa.class);
		if (rutaWeb.getWaypoints() != null) {
			Set<WaypointJpa> waypoints = new HashSet<>();
			for (WaypointWeb waypoint : rutaWeb.getWaypoints()) {
				WaypointJpa waypointJpa =toWaypointJpa(waypoint);
				waypointJpa.setRuta(result);
				waypoints.add(waypointJpa);
			}
			result.setWaypoints(waypoints);
		}
		if (rutaWeb.getParticipantes() != null) {
			Set<ParticipanteJpa> participantes = new HashSet<>();
			for (ParticipanteWeb participante : rutaWeb.getParticipantes()) {
				ParticipanteJpa participanteJpa = toParticipanteJpa(participante);
				participanteJpa.setRuta(result);
				participantes.add(participanteJpa);
			}
			result.setParticipantes(participantes);
		}
		return result;
	}
	
	public  List<WaypointWeb> toListWaypointWeb(List<WaypointJpa> listaWaypointJpa) {
    	List<WaypointWeb> result = new ArrayList<>();
    	for (WaypointJpa waypointJpa : listaWaypointJpa) {
			result.add(toWaypointWeb(waypointJpa));
		}
    	return result;
	}

	public  List<WaypointJpa> toListWaypointJpa(List<WaypointWeb> listaWaypointWeb) {
    	List<WaypointJpa> result = new ArrayList<>();
    	for (WaypointWeb waypointWeb : listaWaypointWeb) {
			result.add(toWaypointJpa(waypointWeb));
		}
    	return result;
	}

	public  List<ParticipanteWeb> toListParticipanteWeb(List<ParticipanteJpa> listaParticipanteJpa) {
    	List<ParticipanteWeb> result = new ArrayList<>();
    	for (ParticipanteJpa participanteJpa : listaParticipanteJpa) {
			result.add(toParticipanteWeb(participanteJpa));
		}
    	return result;
	}

	public  List<ParticipanteJpa> toListParticipanteJpa(List<ParticipanteWeb> listaParticipanteWeb) {
    	List<ParticipanteJpa> result = new ArrayList<>();
    	for (ParticipanteWeb participanteWeb : listaParticipanteWeb) {
			result.add(toParticipanteJpa(participanteWeb));
		}
    	return result;
	}

	public  List<RutaWeb> toListRutaWeb(List<RutaJpa> listaRutaJpa) {
    	List<RutaWeb> result = new ArrayList<>();
    	for (RutaJpa rutaJpa : listaRutaJpa) {
			result.add(toRutaWeb(rutaJpa));
		}
    	return result;
	}

	public  List<RutaJpa> toListRutaJpa(List<RutaWeb> listaRutaWeb) {
    	List<RutaJpa> result = new ArrayList<>();
    	for (RutaWeb rutaWeb : listaRutaWeb) {
			result.add(toRutaJpa(rutaWeb));
		}
    	return result;
	}

}
