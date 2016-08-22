package co.rcbike.usuarios.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import co.rcbike.usuarios.model.Usuario;

@ApplicationScoped
public class UsuariosRepository {

    @Inject
    private EntityManager em;

    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

    public Usuario findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = criteria.from(Usuario.class);
        criteria.select(usuario).where(cb.equal(usuario.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

	public List<Usuario> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> usuario = criteria.from(Usuario.class);
		criteria.select(usuario).orderBy(cb.asc(usuario.get("nombres")));
		return em.createQuery(criteria).getResultList();
	}

}
