package co.rcbike.desplazamientos.model;

import java.util.ArrayList;
import java.util.List;

public class TransformadorDesplazamientos extends Transformador {

	public static WaypointWeb toWaypointWeb(WaypointJpa waypointJpa) {
		return crearObjetoDestino(waypointJpa, WaypointWeb.class);
	}

	public static WaypointJpa toWaypointJpa(WaypointWeb waypointWeb) {
		return crearObjetoDestino(waypointWeb, WaypointJpa.class);
	}

	public static ParticipanteWeb toParticipanteWeb(ParticipanteJpa participanteJpa) {
		return crearObjetoDestino(participanteJpa, ParticipanteWeb.class);
	}

	public static ParticipanteJpa toParticipanteJpa(ParticipanteWeb participanteWeb) {
		return crearObjetoDestino(participanteWeb, ParticipanteJpa.class);
	}

	public static RutaWeb toRutaWeb(RutaJpa rutaJpa) {
		RutaWeb result = crearObjetoDestino(rutaJpa, RutaWeb.class);
		result.setWaypoints(crearSetObjetosDestino(rutaJpa.getWaypoints(), WaypointWeb.class));
		result.setParticipantes(crearSetObjetosDestino(rutaJpa.getParticipantes(), ParticipanteWeb.class));
		return result;
	}

	public static RutaJpa toRutaJpa(RutaWeb rutaWeb) {
		RutaJpa result = crearObjetoDestino(rutaWeb, RutaJpa.class);
		result.setWaypoints(crearSetObjetosDestino(rutaWeb.getWaypoints(), WaypointJpa.class));
		result.setParticipantes(crearSetObjetosDestino(rutaWeb.getParticipantes(), ParticipanteJpa.class));
		return result;
	}
	
	public static List<WaypointWeb> toListWaypointWeb(List<WaypointJpa> listaWaypointJpa) {
    	List<WaypointWeb> result = new ArrayList<>();
    	for (WaypointJpa waypointJpa : listaWaypointJpa) {
			result.add(toWaypointWeb(waypointJpa));
		}
    	return result;
	}

	public static List<WaypointJpa> toListWaypointJpa(List<WaypointWeb> listaWaypointWeb) {
    	List<WaypointJpa> result = new ArrayList<>();
    	for (WaypointWeb waypointWeb : listaWaypointWeb) {
			result.add(toWaypointJpa(waypointWeb));
		}
    	return result;
	}

	public static List<ParticipanteWeb> toListParticipanteWeb(List<ParticipanteJpa> listaParticipanteJpa) {
    	List<ParticipanteWeb> result = new ArrayList<>();
    	for (ParticipanteJpa participanteJpa : listaParticipanteJpa) {
			result.add(toParticipanteWeb(participanteJpa));
		}
    	return result;
	}

	public static List<ParticipanteJpa> toListParticipanteJpa(List<ParticipanteWeb> listaParticipanteWeb) {
    	List<ParticipanteJpa> result = new ArrayList<>();
    	for (ParticipanteWeb participanteWeb : listaParticipanteWeb) {
			result.add(toParticipanteJpa(participanteWeb));
		}
    	return result;
	}

	public static List<RutaWeb> toListRutaWeb(List<RutaJpa> listaRutaJpa) {
    	List<RutaWeb> result = new ArrayList<>();
    	for (RutaJpa rutaJpa : listaRutaJpa) {
			result.add(toRutaWeb(rutaJpa));
		}
    	return result;
	}

	public static List<RutaJpa> toListRutaJpa(List<RutaWeb> listaRutaWeb) {
    	List<RutaJpa> result = new ArrayList<>();
    	for (RutaWeb rutaWeb : listaRutaWeb) {
			result.add(toRutaJpa(rutaWeb));
		}
    	return result;
	}

}
