package co.rcbike.autenticacion.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AutenticacionUsuario> criteria = cb.createQuery(AutenticacionUsuario.class);
		Root<AutenticacionUsuario> aut = criteria.from(AutenticacionUsuario.class);
		criteria.select(aut).where(cb.equal(aut.get("email"), email));
		try {
			return em.createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
