package co.rcbike.reportes.model.fabrica;

import java.util.List;

import co.rcbike.reportes.model.ResumenWeb;
import co.rcbike.reportes.model.RutaWeb;

public interface IReporteRecorridos {
    public List<ResumenWeb> obtenerReporte(List<RutaWeb> listaRutas);

}
