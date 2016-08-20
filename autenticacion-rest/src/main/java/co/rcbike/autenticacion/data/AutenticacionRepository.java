package co.rcbike.autenticacion.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import co.rcbike.autenticacion.model.AutenticacionUsuario;

@ApplicationScoped
public class AutenticacionRepository {

    @Inject
    private EntityManager em;

    public List<AutenticacionUsuario> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AutenticacionUsuario> criteria = cb.createQuery(AutenticacionUsuario.class);
        Root<AutenticacionUsuario> member = criteria.from(AutenticacionUsuario.class);
        criteria.select(member);
        return em.createQuery(criteria).getResultList();
    }
}
