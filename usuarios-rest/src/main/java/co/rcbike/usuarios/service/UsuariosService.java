
package co.rcbike.usuarios.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import co.rcbike.usuarios.model.Usuario;

@Stateless
public class UsuariosService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    public Usuario findUsuario(String email) {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_findByEmail, Usuario.class);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> listUsuario() {
        TypedQuery<Usuario> q = em.createNamedQuery(Usuario.SQ_listByNombre, Usuario.class);
        return q.getResultList();
    }
}
