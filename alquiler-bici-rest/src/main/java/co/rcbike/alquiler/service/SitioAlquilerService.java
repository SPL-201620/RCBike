package co.rcbike.alquiler.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.alquiler.model.SitioAlquilerJpa;

@Stateless
public class SitioAlquilerService {

	/*
	 * # delta distance (in meters) 1 0.1000000 11,057.43 2 0.0100000 1,105.74 3
	 * 0.0010000 110.57 4 0.0001000 11.06 5 0.0000100 1.11 6 0.0000010 0.11 7
	 * 0.0000001 0.01
	 * 
	 * Obtenido de
	 * http://stackoverflow.com/questions/15965166/what-is-the-maximum-length-of
	 * -latitude-and-longitude
	 */
	private BigDecimal DISTANCIA_RUTA_CERCANA = new BigDecimal("0.01");/* 1Km */

	// @Inject
	// private Logger log;

	@Inject
	private EntityManager em;

	private SitioAlquilerJpa ultimaSitioAlquilerConsultada;

	// ***** CONFIGURACIONES *****//

	public SitioAlquilerJpa findSitioAlquiler(Long idSitioAlquiler) {
		SitioAlquilerJpa result = null;
		if ((ultimaSitioAlquilerConsultada != null)
				&& (ultimaSitioAlquilerConsultada.getId().equals(idSitioAlquiler))) {
			result = ultimaSitioAlquilerConsultada;
		} else {
			result = em.find(SitioAlquilerJpa.class, idSitioAlquiler);
			ultimaSitioAlquilerConsultada = result;
		}
		return result;
	}

	/**
	 * Obtienete una sitioAlquiler
	 * 
	 * @param id
	 *            Identificado de la sitioAlquiler
	 */
	public SitioAlquilerJpa getSitioAlquiler(Long id) {
		TypedQuery<SitioAlquilerJpa> q = em.createNamedQuery(SitioAlquilerJpa.SQ_findByIdSitioAlquiler,
				SitioAlquilerJpa.class);
		q.setParameter(SitioAlquilerJpa.SQ_PARAM_ID, id);
		return q.getSingleResult();
	}

	/**
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param id
	 *            Identificado de la sitioAlquiler
	 * 
	 */
	public void deleteSitioAlquiler(Long id) {
		SitioAlquilerJpa sitioAlquilerJpa = em.find(SitioAlquilerJpa.class, id);
		if (sitioAlquilerJpa != null) {
			em.remove(sitioAlquilerJpa);
		}
	}

	/**
	 * Permite guardar una sitioAlquiler
	 * 
	 * @param sitioAlquiler
	 *            Informacion de la sitioAlquiler a crear
	 */
	public Long persistSitioAlquiler(SitioAlquilerJpa sitioAlquiler) {
		em.persist(sitioAlquiler);
		return sitioAlquiler.getId();
	}

	/**
	 * Permite actualizar una sitioAlquiler
	 * 
	 * @param sitioAlquiler
	 *            Informacion de la sitioAlquiler a crear
	 */
	public Long mergeSitioAlquiler(SitioAlquilerJpa sitioAlquiler) {
		em.merge(sitioAlquiler);
		return sitioAlquiler.getId();
	}

	/**
	 * Lista todas las sitiosAlquiler
	 * 
	 */
	public List<SitioAlquilerJpa> listTodosSitiosAlquiler() {
		TypedQuery<SitioAlquilerJpa> q = em.createNamedQuery(SitioAlquilerJpa.SQ_findAllSitiosAlquiler,
				SitioAlquilerJpa.class);
		return q.getResultList();
	}

	/**
	 * Lista todas las sitiosAlquiler
	 * 
	 * @param emailCreador
	 *            email del usuario creador
	 */
	public List<SitioAlquilerJpa> listSitiosAlquiler(String emailCreador) {
		TypedQuery<SitioAlquilerJpa> q = em.createNamedQuery(SitioAlquilerJpa.SQ_findByCreador, SitioAlquilerJpa.class);
		q.setParameter(SitioAlquilerJpa.SQ_PARAM_EMAIL_CREADOR, emailCreador);
		return q.getResultList();
	}

	/**
	 * Lista todos Sitios de alquiler cercanos a una latitud y  longitud
	 * 
	 * @param latitud Latitud geografica 
	 * @param longitud Longitud geografica 
	 * 
	 */
	public List<SitioAlquilerJpa> listSitiosAlquilerCercanos(BigDecimal latitud, BigDecimal longitud) {
		BigDecimal latitudInicio = latitud.subtract(DISTANCIA_RUTA_CERCANA);
		BigDecimal latitudFinal = latitud.add(DISTANCIA_RUTA_CERCANA);
		BigDecimal longitudInicio = longitud.subtract(DISTANCIA_RUTA_CERCANA);
		BigDecimal longitudFinal = longitud.add(DISTANCIA_RUTA_CERCANA);
		TypedQuery<SitioAlquilerJpa> q = em.createNamedQuery(SitioAlquilerJpa.SQ_findByPunto, SitioAlquilerJpa.class);
		q.setParameter(SitioAlquilerJpa.SQ_PARAM_LATITUD_INICIO, latitudInicio);
		q.setParameter(SitioAlquilerJpa.SQ_PARAM_LATITUD_FINAL, latitudFinal);
		q.setParameter(SitioAlquilerJpa.SQ_PARAM_LONGITUD_INICIO, longitudInicio);
		q.setParameter(SitioAlquilerJpa.SQ_PARAM_LONGITUD_FINAL, longitudFinal);
		return q.getResultList();
	}

}
