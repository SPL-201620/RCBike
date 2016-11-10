package co.rcbike.reportes.model.fabrica;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            for (RutaWeb rutaWeb : listaRutas) {
                ResumenWeb resumen = getResumen(rutaWeb.getFecha());
                if (resumen == null) {
                    resumen = crearResumen(rutaWeb);
                }
                agregar(resumen, rutaWeb);
            }
            return getListaResumen();
        }
        return result;
    }

    protected void agregar(ResumenWeb resumen, RutaWeb rutaWeb) {
        resumen.setKilometrosRecorridos(resumen.getKilometrosRecorridos().add(rutaWeb.getDistancia()));
        resumen.setVelocidadPromedio(resumen.getVelocidadPromedio().add(
                rutaWeb.getDistancia().divide(new BigDecimal(rutaWeb.getTiempoEstimado()), 4, RoundingMode.HALF_UP)));
        if (Tipo.INDIVIDUAL.equals(rutaWeb.getTipo())) {
            resumen.setViajesIndividuales(resumen.getViajesIndividuales() + 1);
        }
        if (Tipo.GRUPAL.equals(rutaWeb.getTipo())) {
            if (rutaWeb.getParticipantes() != null) {
                resumen.setCompanerosPromedio(((resumen.getCompanerosPromedio() * resumen.getViajesGrupales())
                        + rutaWeb.getParticipantes().size()) / (resumen.getViajesGrupales() + 1));
            }
            // Necesariamente despues de calcular compañeros promedio
            resumen.setViajesGrupales(resumen.getViajesGrupales() + 1);
        }
        resumen.setCaloriasQuemadas(resumen.getCaloriasQuemadas() + rutaWeb.getCalorias());
        resumen.getRutas().add(rutaWeb);
        agregarAListaResumen(resumen);
    }

    private void agregarAListaResumen(ResumenWeb resumen) {
        for (ResumenWeb resumenExistente : getListaResumen()) {
            if (resumenExistente.getId().compareTo(resumen.getId()) == 0) {
                return;
            }
        }
        getListaResumen().add(resumen);
    }

    protected ResumenWeb crearResumen(RutaWeb ruta) {
        ResumenWeb result = new ResumenWeb();
        result.setId(new Long(getListaResumen().size() + 1));
        result.setEmailCreador(ruta.getEmailCreador());
        return result;
    }

    protected ResumenWeb getResumen(Date fecha) {
        for (ResumenWeb resumen : getListaResumen()) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(fecha);
            // ! clear would not reset the hour of day !
            gc.set(Calendar.HOUR_OF_DAY, 0);
            gc.clear(Calendar.MINUTE);
            gc.clear(Calendar.SECOND);
            gc.clear(Calendar.MILLISECOND);

            if ((resumen.getFechaInicio().compareTo(gc.getTime()) <= 0)
                    && (resumen.getFechaFinal().compareTo(gc.getTime()) >= 0)) {
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
