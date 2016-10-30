package co.rcbike.ventas.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import co.rcbike.ventas.model.VentaJpa;
import co.rcbike.ventas.model.VentaWeb;

@RequestScoped
public class TransformadorVentas extends Transformador {

	//***** VENTA *****//
	
	public VentaWeb toVentaWeb(VentaJpa rutaJpa) {
		VentaWeb result = crearObjetoDestino(rutaJpa, VentaWeb.class);
		return result;
	}

	public List<VentaWeb> toListVentaWeb(List<VentaJpa> listaVentaJpa) {
		List<VentaWeb> result = new ArrayList<>();
		for (VentaJpa rutaJpa : listaVentaJpa) {
			result.add(toVentaWeb(rutaJpa));
		}
		return result;
	}

	public VentaJpa toVentaJpa(VentaWeb rutaWeb) {
		VentaJpa result = crearObjetoDestino(rutaWeb, VentaJpa.class);
		return result;
	}

	public List<VentaJpa> toListVentaJpa(List<VentaWeb> listaVentaWeb) {
		List<VentaJpa> result = new ArrayList<>();
		for (VentaWeb rutaWeb : listaVentaWeb) {
			result.add(toVentaJpa(rutaWeb));
		}
		return result;
	}

}
