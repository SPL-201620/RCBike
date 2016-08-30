package co.rcbike.sinc_redes.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class SincRedesRepository {

    @Inject
    private EntityManager em;

}
