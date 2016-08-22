package co.rcbike.alquiler.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class AlquilerRepository {

    @Inject
    private EntityManager em;

}
