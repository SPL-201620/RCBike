package co.rcbike.reportes.model.fabrica;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import co.rcbike.reportes.model.ResumenWeb;

public class ReporteMensual extends ReporteBasico implements IReporteRecorridos {

	@Override
	protected ResumenWeb crearResumen(Date fecha) {
		ResumenWeb result = new ResumenWeb();

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(fecha);

		GregorianCalendar gc = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		gc.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		gc.clear(Calendar.MINUTE);
		gc.clear(Calendar.SECOND);
		gc.clear(Calendar.MILLISECOND);
		result.setFechaInicio(gc.getTime());
		
		gc.add(Calendar.MONTH, 1);
		gc.add(Calendar.DAY_OF_MONTH, -1);
		result.setFechaFinal(gc.getTime());

		result.setId(new Long(getListaResumen().size() + 1));
		return result;
	}

}
