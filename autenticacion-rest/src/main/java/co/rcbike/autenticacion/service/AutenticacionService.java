package co.rcbike.autenticacion.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import co.rcbike.autenticacion.model.AutenticacionUsuario;

@Stateless
public class AutenticacionService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

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
        TypedQuery<AutenticacionUsuario> q = em.createNamedQuery(AutenticacionUsuario.SQ_findByEmail,
                AutenticacionUsuario.class);
		q.setParameter(AutenticacionUsuario.SQ_PARAM_EMAIL, email);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
