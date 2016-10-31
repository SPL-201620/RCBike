package co.rcbike.reportes.model.fabrica;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.rcbike.reportes.model.ResumenWeb;
import co.rcbike.reportes.model.RutaWeb;
import co.rcbike.reportes.model.Tipo;

public abstract class ReporteBasico implements IReporteRecorridos {

	private List<ResumenWeb> listaResumen;

	@Override
	public List<ResumenWeb> obtenerReporte(List<RutaWeb> listaRutas) {
		List<ResumenWeb> result = null;
		if (listaRutas != null) {

		}
		for (RutaWeb rutaWeb : listaRutas) {
			ResumenWeb resumen = getResumen(rutaWeb.getFecha());
			if (resumen == null) {
				resumen = crearResumen(rutaWeb.getFecha());
			}
			agregar(resumen, rutaWeb);
		}
		return result;
	}

	protected void agregar(ResumenWeb resumen, RutaWeb rutaWeb) {
		resumen.setKilometrosRecorridos(resumen.getKilometrosRecorridos().add(rutaWeb.getDistancia()));
		resumen.setVelocidadPromedio(resumen.getVelocidadPromedio()
				.add(rutaWeb.getDistancia().divide(new BigDecimal(rutaWeb.getTiempoEstimado()))));
		if (Tipo.INDIVIDUAL.equals(rutaWeb.getTipo())) {
			resumen.setViajesIndividuales(resumen.getViajesIndividuales() + 1);
		}
		if (rutaWeb.getParticipantes() != null) {
			resumen.setCompanerosPromedio(((resumen.getCompanerosPromedio() * resumen.getViajesGrupales())
					+ rutaWeb.getParticipantes().size()) / (resumen.getViajesGrupales() + 1));
		}
		// Necesariamente despues de calcular compañeros promedio
		if (Tipo.GRUPAL.equals(rutaWeb.getTipo())) {
			resumen.setViajesGrupales(resumen.getViajesGrupales() + 1);
		}
		resumen.setCaloriasQuemadas(resumen.getCaloriasQuemadas() + rutaWeb.getCalorias());
		resumen.getRutas().add(rutaWeb);
	}

	protected abstract ResumenWeb crearResumen(Date fecha);

	protected ResumenWeb getResumen(Date fecha) {
		for (ResumenWeb resumen : getListaResumen()) {
			if ((resumen.getFechaInicio().compareTo(fecha) >= 0) && (resumen.getFechaFinal().compareTo(fecha) <= 0)) {
				return resumen;
			}
		}
		return null;
	}

	protected List<ResumenWeb> getListaResumen() {
		if (listaResumen == null) {
			listaResumen = new ArrayList<>();
		}
		return listaResumen;
	}

}
