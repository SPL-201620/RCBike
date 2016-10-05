package co.rcbike.desplazamientos.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

	//@Inject
	//private Logger log;

	@Inject
	private EntityManager em;

	/**
	 * Lista todos los recorridos grupales frecuentes
	 * 
	 */
	private List<Ruta> listViajesGrupalesFrecuentes() {
		TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndFrecuente, Ruta.class);
		q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
		q.setParameter(Ruta.SQ_PARAM_FRECUENTE, true);
		return q.getResultList();
	}

	/**
	 * Lista todos los recorridos grupales no frecuentes no vencidos
	 * 
	 */
	private List<Ruta> listViajesGrupalesNoFrecuentesNoVencidos() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndFechaAndFrecuente, Ruta.class);
		q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
		q.setParameter(Ruta.SQ_PARAM_FECHA, cal.getTime());
		q.setParameter(Ruta.SQ_PARAM_FRECUENTE, false);
		return q.getResultList();
	}

	/**
	 * Lista todos los recorridos grupales no vencidos
	 * 
	 */
	public List<Ruta> listViajesGrupalesNoVencidos() {
		List<Ruta> result = new ArrayList<Ruta>();
		result.addAll(listViajesGrupalesFrecuentes());
		result.addAll(listViajesGrupalesNoFrecuentesNoVencidos());
		return result;
	}

	/**
	 * Lista todos los recorridos grupales
	 * 
	 */
	public List<Ruta> listViajesGrupales() {
		TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipo, Ruta.class);
		q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.GRUPAL);
		return q.getResultList();
	}

	/**
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param emailCreador
	 *            email del usuario creador
	 */
	public List<Ruta> listViajesIndividuales(String emailCreador) {
		TypedQuery<Ruta> q = em.createNamedQuery(Ruta.SQ_findByTipoAndCreador, Ruta.class);
		q.setParameter(Ruta.SQ_PARAM_TIPO, Tipo.INDIVIDUAL);
		q.setParameter(Ruta.SQ_PARAM_EMAIL_CREADOR, emailCreador);
		return q.getResultList();
	}

	/**
	 * Permite crear un participante en una ruta
	 * 
	 * @param idRuta
	 *            identificador de la ruta
	 * @param emailParticipante
	 *            email del usuario participante
	 */
	public void guardarParticipante(Long idRuta, String emailParticipante) {
		Participante participante = new Participante();
		participante.setRuta(em.getReference(Ruta.class, idRuta));
		participante.setEmail(emailParticipante);
		em.persist(participante);
	}

	/**
	 * Permite guardar un recorrido individual o crear un recorrido organizado
	 * 
	 * @param ruta
	 *            informacion de la ruta a crear
	 */
	public void guardarViaje(Ruta ruta) {
		em.persist(ruta);
	}

	/**
	 * TEMP Lista todos los Waypoints de una Ruta
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
