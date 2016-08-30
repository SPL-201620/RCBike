package co.rcbike.reportes.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ReportesRepository {

    @Inject
    private EntityManager em;

}
