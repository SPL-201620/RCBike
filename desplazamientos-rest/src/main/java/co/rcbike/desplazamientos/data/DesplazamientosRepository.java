package co.rcbike.desplazamientos.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class DesplazamientosRepository {

    @Inject
    private EntityManager em;

}
