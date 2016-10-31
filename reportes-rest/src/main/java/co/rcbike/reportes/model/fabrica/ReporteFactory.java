package co.rcbike.reportes.model.fabrica;

import co.rcbike.reportes.model.TipoReporte;

public class ReporteFactory {

	public IReporteRecorridos getReporteRecorridos(TipoReporte tipoReporte) {
		if (tipoReporte == null) {
			return null;
		}
		if (tipoReporte.equals(TipoReporte.MENSUAL)) {
			return new ReporteMensual();

		} else if (tipoReporte.equals(TipoReporte.SEMANAL)) {
			return new ReporteSemanal();

		}
		return null;
	}
}