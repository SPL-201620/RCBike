package co.rcbike.autenticacion.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import co.rcbike.autenticacion.model.AutenticacionUsuario;

@Stateless
public class AutenticacionService {

    @Inject
    private EntityManager em;

    public void registrar(String email, String clave) {
        AutenticacionUsuario entity = new AutenticacionUsuario();
        entity.setEmail(email);
        entity.setClave(clave);
        em.persist(entity);
    }

    public enum EstadoAutenticacion {
        OK,
        CLAVE_ERRONEA,
        NO_EXISTE_USUARIO;
    }

    public EstadoAutenticacion autenticar(String email, String clave) {
        AutenticacionUsuario aut = findByEmail(email);
        if (aut != null) {
            return aut.getClave().equals(clave) ? EstadoAutenticacion.OK : EstadoAutenticacion.CLAVE_ERRONEA;
        } else {
            return EstadoAutenticacion.NO_EXISTE_USUARIO;
        }
    }

    private AutenticacionUsuario findByEmail(String email) {
        TypedQuery<AutenticacionUsuario> q = em.createNamedQuery(AutenticacionUsuario.SQ_autByEmail,
                AutenticacionUsuario.class);
        q.setParameter(AutenticacionUsuario.SQ_PARAM_EMAIL, email);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
