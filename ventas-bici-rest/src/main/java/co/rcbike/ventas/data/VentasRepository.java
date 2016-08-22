package co.rcbike.ventas.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class VentasRepository {

    @Inject
    private EntityManager em;

}
