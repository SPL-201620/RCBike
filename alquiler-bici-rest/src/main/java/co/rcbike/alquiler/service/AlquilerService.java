package co.rcbike.alquiler.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.alquiler.model.Alquiler;

@Stateless
public class AlquilerService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    public List<Alquiler> listAlquiler() {
        TypedQuery<Alquiler> q = em.createNamedQuery(Alquiler.SQ_listByNombre, Alquiler.class);
        return q.getResultList();
    }
}
