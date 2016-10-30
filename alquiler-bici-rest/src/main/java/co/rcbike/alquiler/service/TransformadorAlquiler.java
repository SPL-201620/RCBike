package co.rcbike.alquiler.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import co.rcbike.alquiler.model.SitioAlquilerJpa;
import co.rcbike.alquiler.model.SitioAlquilerWeb;

@RequestScoped
public class TransformadorAlquiler extends Transformador {

	//***** SITIO ALQUILER *****//
	
	public SitioAlquilerWeb toSitioAlquilerWeb(SitioAlquilerJpa rutaJpa) {
		SitioAlquilerWeb result = crearObjetoDestino(rutaJpa, SitioAlquilerWeb.class);
		return result;
	}

	public List<SitioAlquilerWeb> toListSitioAlquilerWeb(List<SitioAlquilerJpa> listaSitioAlquilerJpa) {
		List<SitioAlquilerWeb> result = new ArrayList<>();
		for (SitioAlquilerJpa rutaJpa : listaSitioAlquilerJpa) {
			result.add(toSitioAlquilerWeb(rutaJpa));
		}
		return result;
	}

	public SitioAlquilerJpa toSitioAlquilerJpa(SitioAlquilerWeb rutaWeb) {
		SitioAlquilerJpa result = crearObjetoDestino(rutaWeb, SitioAlquilerJpa.class);
		return result;
	}

	public List<SitioAlquilerJpa> toListSitioAlquilerJpa(List<SitioAlquilerWeb> listaSitioAlquilerWeb) {
		List<SitioAlquilerJpa> result = new ArrayList<>();
		for (SitioAlquilerWeb rutaWeb : listaSitioAlquilerWeb) {
			result.add(toSitioAlquilerJpa(rutaWeb));
		}
		return result;
	}

}
