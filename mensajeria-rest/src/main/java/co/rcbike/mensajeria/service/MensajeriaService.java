package co.rcbike.mensajeria.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import co.rcbike.mensajeria.model.Mensaje;

@Stateless
public class MensajeriaService {

	@Inject
	private EntityManager em;
	
	

	public List<Mensaje> findMensajes(String emailEmisor,
			String emailReceptor) {
		TypedQuery<Mensaje> q = em
						.createNamedQuery(Mensaje.SQ_LISTBYPARTICIPANTES, Mensaje.class);		
		q.setParameter("emailEmisor", emailEmisor);
		q.setParameter("emailReceptor", emailReceptor);
			List<Mensaje> c =q.getResultList();
			return c;
		

	}

	public Mensaje createMensaje(Mensaje mensaje) {
		    em.persist(mensaje);
			em.flush();
			return mensaje;
	}
	
}
