package co.rcbike.reportes.model.fabrica;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import co.rcbike.reportes.model.ResumenWeb;
import co.rcbike.reportes.model.RutaWeb;

public class ReporteSemanal extends ReporteBasico implements IReporteRecorridos {

	@Override
	protected ResumenWeb crearResumen(Date fecha) {
		ResumenWeb result = new ResumenWeb();

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		gc.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		gc.clear(Calendar.MINUTE);
		gc.clear(Calendar.SECOND);
		gc.clear(Calendar.MILLISECOND);
		
		gc.set(Calendar.DAY_OF_WEEK, gc.getFirstDayOfWeek());
		result.setFechaInicio(gc.getTime());
		
		gc.add(Calendar.WEEK_OF_YEAR, 1);
		gc.add(Calendar.DAY_OF_MONTH, -1);
		result.setFechaFinal(gc.getTime());
		
		result.setId(new Long(getListaResumen().size() + 1));
		return result;
	}

}
