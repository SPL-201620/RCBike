package co.rcbike.desplazamientos.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.desplazamientos.model.Ruta;
import co.rcbike.desplazamientos.model.RutaRealizada;
import co.rcbike.desplazamientos.model.WaypointRuta;

@Stateless
public class DesplazamientosService {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	/**
	 * Lista todos los recorridos realizados por un usuario
	 * 
	 * @param email
	 *            identificador del usuario
	 */
	public List<RutaRealizada> listRecorridoRealizado(String email) {
		throw new IllegalStateException("Not Implemented");
	}

	/**
	 * Permite crear cualquier tipo de recorrido
	 * 
	 * @param recorrido
	 *            informacion del recorrido a crear
	 */
	public void crearRuta(Ruta ruta) {
		em.persist(ruta);
	}

	/**
	 * Permite crear cualquier tipo de recorrido
	 * 
	 * @param recorrido
	 *            informacion del recorrido a crear
	 */
	public void registrarViaje(RutaRealizada rutaRealizada) {
		em.persist(rutaRealizada);
	}

	/**
	 * Lista todos los Waypoints de una Ruta
	 * 
	 * @param idRuta 
	 *            identificador de la ruta
	 */
	public List<WaypointRuta> listWaypointsRuta(Long idRuta) {
		TypedQuery<WaypointRuta> q = em.createNamedQuery(WaypointRuta.SQ_findByIdRuta, WaypointRuta.class);
		q.setParameter(WaypointRuta.SQ_PARAM_ID_RUTA, idRuta);
		return q.getResultList();
	}
	
	//Creacion Ruta
	
	//Listar rutas grupales, no vencidas, donde 

}
