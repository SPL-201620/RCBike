package co.rcbike.desplazamientos.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.desplazamientos.model.Participante;
import co.rcbike.desplazamientos.model.Ruta;
import co.rcbike.desplazamientos.model.Tipo;
import co.rcbike.desplazamientos.model.Waypoint;

@Stateless
public class DesplazamientosService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	/**
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param recorrido
	 *            informacion del recorrido a crear
	 */
	public List<Ruta> listViajesGrupales() {
	    //TODO Armando - retornar los que no esten vencidos, union
		TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipo, Ruta.class);
		q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
		return q.getResultList();
	}

	/**
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param recorrido
	 *            informacion del recorrido a crear
	 */
	public List<Ruta> listViajesIndividuales(String emailCreador) {
		TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndCreador, Ruta.class);
		q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.INDIVIDUAL);
		q.setParameter(Ruta.SQ_PARAM_EMAIL_CREADOR, emailCreador);
		return q.getResultList();
	}

	/**
	 * Permite guardar un recorrido individual o crear un recorrido organizado
	 * 
	 * @param ruta
	 *            informacion de la ruta a crear
	 */
	public void guardarViaje(Ruta ruta) {
//	    Participante entity = new Participante();
//	    entity.setRuta(em.getReference(Ruta.class, 1));
//      em.persist(entity);
		em.persist(ruta);
	}

	/**
	 * Lista todos los Waypoints de una Ruta
	 * 
	 * @param idRuta 
	 *            identificador de la ruta
	 */
	public List<Waypoint> listWaypointsRuta(Long idRuta) {
		TypedQuery<Waypoint> q = em.createNamedQuery(Waypoint.SQ_findByIdRuta, Waypoint.class);
		q.setParameter(Waypoint.SQ_PARAM_ID_RUTA, idRuta);
		return q.getResultList();
	}
	
}
