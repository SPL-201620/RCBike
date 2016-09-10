package co.rcbike.mensajeria.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import co.rcbike.mensajeria.model.Conversacion;

@Stateless
public class MensajeriaService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	public List<Conversacion> listConversacion(String emailParticipante) {
		throw new IllegalStateException("Not Implemented");
	}

}
