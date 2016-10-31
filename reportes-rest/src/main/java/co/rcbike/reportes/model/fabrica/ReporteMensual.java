package co.rcbike.reportes.model.fabrica;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.rcbike.reportes.model.ResumenWeb;

public class ReporteMensual extends ReporteBasico implements IReporteRecorridos {

	@Override
	protected ResumenWeb crearResumen(Date fecha) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		GregorianCalendar fechaInicio = new GregorianCalendar(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 1);
		GregorianCalendar fechaFinal = new GregorianCalendar(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 1);
		fechaFinal.add(Calendar.DAY_OF_MONTH, -1);
		ResumenWeb result = new ResumenWeb();
		result.setFechaInicio(fechaInicio.getTime());
		result.setFechaFinal(fechaFinal.getTime());
		result.setId(new Long(getListaResumen().size() + 1));
		return result;
	}

}
