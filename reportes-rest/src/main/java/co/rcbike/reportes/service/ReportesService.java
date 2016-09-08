
package co.rcbike.reportes.service;

import java.time.LocalDate;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import co.rcbike.reportes.model.Reporte;

@Stateless
public class ReportesService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    public Reporte generarReporte(LocalDate inicio, LocalDate fin) {
        throw new IllegalStateException();
    }
}
