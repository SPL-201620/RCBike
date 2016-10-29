package co.rcbike.configurador_bici.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import co.rcbike.configurador_bici.model.ColorJpa;
import co.rcbike.configurador_bici.model.ColorWeb;
import co.rcbike.configurador_bici.model.ConfiguracionJpa;
import co.rcbike.configurador_bici.model.ConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaConfiguracionJpa;
import co.rcbike.configurador_bici.model.PiezaConfiguracionWeb;
import co.rcbike.configurador_bici.model.PiezaJpa;
import co.rcbike.configurador_bici.model.PiezaWeb;

@RequestScoped
public class TransformadorConfigurador extends Transformador {

	@Inject
	private ConfiguradorService service;
	
	//***** PIEZAS *****//

	public PiezaWeb toPiezaWeb(PiezaJpa piezaJpa) {
		PiezaWeb result = crearObjetoDestino(piezaJpa, PiezaWeb.class);
		return result;
	}

	public List<PiezaWeb> toListPiezaWeb(List<PiezaJpa> listaPiezaJpa) {
		List<PiezaWeb> result = new ArrayList<>();
		for (PiezaJpa piezaJpa : listaPiezaJpa) {
			result.add(toPiezaWeb(piezaJpa));
		}
		return result;
	}

	public PiezaJpa toPiezaJpa(PiezaWeb piezaWeb) {
		PiezaJpa result = crearObjetoDestino(piezaWeb, PiezaJpa.class);
		return result;
	}
	
	public List<PiezaJpa> toListPiezaJpa(List<PiezaWeb> listaPiezaWeb) {
		List<PiezaJpa> result = new ArrayList<>();
		for (PiezaWeb piezaWeb : listaPiezaWeb) {
			result.add(toPiezaJpa(piezaWeb));
		}
		return result;
	}

	//***** COLORES *****//

	public ColorWeb toColorWeb(ColorJpa colorJpa) {
		ColorWeb result = crearObjetoDestino(colorJpa, ColorWeb.class);
		return result;
	}

	public List<ColorWeb> toListColorWeb(List<ColorJpa> listaColorJpa) {
		List<ColorWeb> result = new ArrayList<>();
		for (ColorJpa colorJpa : listaColorJpa) {
			result.add(toColorWeb(colorJpa));
		}
		return result;
	}

	public ColorJpa toColorJpa(ColorWeb colorWeb) {
		ColorJpa result = crearObjetoDestino(colorWeb, ColorJpa.class);
		return result;
	}
	
	public List<ColorJpa> toListColorJpa(List<ColorWeb> listaColorWeb) {
		List<ColorJpa> result = new ArrayList<>();
		for (ColorWeb colorWeb : listaColorWeb) {
			result.add(toColorJpa(colorWeb));
		}
		return result;
	}

	//***** PIEZAS CONFIGURACION *****//

	public PiezaConfiguracionWeb toPiezaConfiguracionWeb(PiezaConfiguracionJpa piezaConfiguracionJpa) {
		PiezaConfiguracionWeb result = crearObjetoDestino(piezaConfiguracionJpa, PiezaConfiguracionWeb.class);
		if (piezaConfiguracionJpa.getConfiguracion() != null) {
			result.setIdConfiguracion(piezaConfiguracionJpa.getConfiguracion().getId());
		}
		if (piezaConfiguracionJpa.getPieza() != null) {
			result.setIdPieza(piezaConfiguracionJpa.getPieza().getId());
			result.setTipo(piezaConfiguracionJpa.getPieza().getTipo());
			result.setDescripcion(piezaConfiguracionJpa.getPieza().getDescripcion());
		}
		return result;
	}

	public List<PiezaConfiguracionWeb> toListPiezaConfiguracionWeb(
			List<PiezaConfiguracionJpa> listaPiezaConfiguracionJpa) {
		List<PiezaConfiguracionWeb> result = new ArrayList<>();
		for (PiezaConfiguracionJpa piezaConfiguracionJpa : listaPiezaConfiguracionJpa) {
			result.add(toPiezaConfiguracionWeb(piezaConfiguracionJpa));
		}
		return result;
	}

	public PiezaConfiguracionJpa toPiezaConfiguracionJpa(PiezaConfiguracionWeb piezaConfiguracionWeb) {
		PiezaConfiguracionJpa result = crearObjetoDestino(piezaConfiguracionWeb, PiezaConfiguracionJpa.class);
		if (piezaConfiguracionWeb.getIdConfiguracion() != null) {
			result.setConfiguracion(service.findConfiguracion(piezaConfiguracionWeb.getIdConfiguracion()));
		}
		if (piezaConfiguracionWeb.getIdPieza() != null) {
			result.setPieza(service.findPieza(piezaConfiguracionWeb.getIdPieza()));
		}
		return result;
	}

	public List<PiezaConfiguracionJpa> toListPiezaConfiguracionJpa(
			List<PiezaConfiguracionWeb> listaPiezaConfiguracionWeb) {
		List<PiezaConfiguracionJpa> result = new ArrayList<>();
		for (PiezaConfiguracionWeb piezaConfiguracionWeb : listaPiezaConfiguracionWeb) {
			result.add(toPiezaConfiguracionJpa(piezaConfiguracionWeb));
		}
		return result;
	}

	//***** CONFIGURACION *****//
	
	public ConfiguracionWeb toConfiguracionWeb(ConfiguracionJpa rutaJpa) {
		ConfiguracionWeb result = crearObjetoDestino(rutaJpa, ConfiguracionWeb.class);
		if (rutaJpa.getPiezasConfiguracion() != null) {
			Set<PiezaConfiguracionWeb> piezasConfiguracion = new HashSet<>();
			for (PiezaConfiguracionJpa piezaConfiguracion : rutaJpa.getPiezasConfiguracion()) {
				piezasConfiguracion.add(toPiezaConfiguracionWeb(piezaConfiguracion));
			}
			result.setPiezasConfiguracion(piezasConfiguracion);
		}
		return result;
	}

	public List<ConfiguracionWeb> toListConfiguracionWeb(List<ConfiguracionJpa> listaConfiguracionJpa) {
		List<ConfiguracionWeb> result = new ArrayList<>();
		for (ConfiguracionJpa rutaJpa : listaConfiguracionJpa) {
			result.add(toConfiguracionWeb(rutaJpa));
		}
		return result;
	}

	public ConfiguracionJpa toConfiguracionJpa(ConfiguracionWeb rutaWeb) {
		ConfiguracionJpa result = crearObjetoDestino(rutaWeb, ConfiguracionJpa.class);
		if (rutaWeb.getPiezasConfiguracion() != null) {
			Set<PiezaConfiguracionJpa> piezasConfiguracion = new HashSet<>();
			for (PiezaConfiguracionWeb piezaConfiguracion : rutaWeb.getPiezasConfiguracion()) {
				PiezaConfiguracionJpa piezaConfiguracionJpa = toPiezaConfiguracionJpa(piezaConfiguracion);
				piezaConfiguracionJpa.setConfiguracion(result);
				piezasConfiguracion.add(piezaConfiguracionJpa);
			}
			result.setPiezasConfiguracion(piezasConfiguracion);
		}
		return result;
	}

	public List<ConfiguracionJpa> toListConfiguracionJpa(List<ConfiguracionWeb> listaConfiguracionWeb) {
		List<ConfiguracionJpa> result = new ArrayList<>();
		for (ConfiguracionWeb rutaWeb : listaConfiguracionWeb) {
			result.add(toConfiguracionJpa(rutaWeb));
		}
		return result;
	}

}
