
package co.rcbike.configurador_bici.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.configurador_bici.model.ColorJpa;
import co.rcbike.configurador_bici.model.ConfiguracionJpa;
import co.rcbike.configurador_bici.model.PiezaConfiguracionJpa;
import co.rcbike.configurador_bici.model.PiezaJpa;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;

@Stateless
public class ConfiguradorService {

	// @Inject
	// private Logger log;

	@Inject
	private EntityManager em;
	private ConfiguracionJpa ultimaConfiguracionConsultada;
	private PiezaJpa ultimaPiezaConsultada;

	// ***** CONFIGURACIONES *****//

	/**
	 * Valida una configuracion de bicicleta Debe tener un solo MARCO,
	 * LLANTA_DELANTERA, LLANTA_TRASERA
	 * 
	 * @param configuracion
	 *            Es una cadena de caracteres con nombres de caracteristicas
	 *            separadas por comas
	 */
	public boolean validarConfiguracion(String configuracion) {
		ValidadorConfiguracion validador = new ValidadorConfiguracion();
		boolean result = validador.validar(configuracion);
		return result;
	}

	public ConfiguracionJpa findConfiguracion(Long idConfiguracion) {
		ConfiguracionJpa result = null;
		if ((ultimaConfiguracionConsultada != null)
				&& (ultimaConfiguracionConsultada.getId().equals(idConfiguracion))) {
			result = ultimaConfiguracionConsultada;
		} else {
			result = em.find(ConfiguracionJpa.class, idConfiguracion);
			ultimaConfiguracionConsultada = result;
		}
		return result;
	}

	/**
	 * Obtienete una configuracion
	 * 
	 * @param id
	 *            Identificado de la configuracion
	 */
	public ConfiguracionJpa getConfiguracion(Long id) {
		TypedQuery<ConfiguracionJpa> q = em.createNamedQuery(ConfiguracionJpa.SQ_findByIdConfiguracion,
				ConfiguracionJpa.class);
		q.setParameter(ConfiguracionJpa.SQ_PARAM_ID, id);
		return q.getSingleResult();
	}

	/**
	 * Lista todos los recorridos individuales realizados por un usuario
	 * 
	 * @param id
	 *            Identificado de la configuracion
	 * 
	 */
	public void deleteConfiguracion(Long id) {
		ConfiguracionJpa configuracionJpa = em.find(ConfiguracionJpa.class, id);
		if (configuracionJpa != null) {
			em.remove(configuracionJpa);
		}
	}

	/**
	 * Permite guardar una configuracion
	 * 
	 * @param configuracion
	 *            Informacion de la configuracion a crear
	 */
	public Long persistConfiguracion(ConfiguracionJpa configuracion) {
		em.persist(configuracion);
		return configuracion.getId();
	}

	/**
	 * Permite actualizar una configuracion
	 * 
	 * @param configuracion
	 *            Informacion de la configuracion a crear
	 */
	public Long mergeConfiguracion(ConfiguracionJpa configuracion) {
		em.merge(configuracion);
		return configuracion.getId();
	}

	/**
	 * Lista todas las configuraciones
	 * 
	 */
	public List<ConfiguracionJpa> listTodosConfiguraciones() {
		TypedQuery<ConfiguracionJpa> q = em.createNamedQuery(ConfiguracionJpa.SQ_findAllConfiguraciones,
				ConfiguracionJpa.class);
		return q.getResultList();
	}

	/**
	 * Lista todas las configuraciones
	 * 
	 * @param emailCreador
	 *            email del usuario creador
	 */
	public List<ConfiguracionJpa> listConfiguraciones(String emailCreador) {
		TypedQuery<ConfiguracionJpa> q = em.createNamedQuery(ConfiguracionJpa.SQ_findByCreador, ConfiguracionJpa.class);
		q.setParameter(ConfiguracionJpa.SQ_PARAM_EMAIL_CREADOR, emailCreador);
		return q.getResultList();
	}

	// ***** PIEZAS CONFIGURACION *****//

	/**
	 * Obtienete un piezaConfiguracion
	 * 
	 * @param id
	 *            Identificado de la piezaConfiguracion
	 */
	public PiezaConfiguracionJpa getPiezaConfiguracion(Long id) {
		TypedQuery<PiezaConfiguracionJpa> q = em.createNamedQuery(PiezaConfiguracionJpa.SQ_findByIdPiezaConfiguracion,
				PiezaConfiguracionJpa.class);
		q.setParameter(PiezaConfiguracionJpa.SQ_PARAM_ID, id);
		return q.getSingleResult();
	}

	/**
	 * Borra un piezaConfiguracion
	 * 
	 * @param id
	 *            Identificador del piezaConfiguracion
	 * 
	 */
	public void deletePiezaConfiguracion(Long id) {
		PiezaConfiguracionJpa piezaConfiguracionJpa = em.find(PiezaConfiguracionJpa.class, id);
		if (piezaConfiguracionJpa != null) {
			if ((piezaConfiguracionJpa.getConfiguracion() != null)
					&& piezaConfiguracionJpa.getConfiguracion().getPiezasConfiguracion() != null) {
				piezaConfiguracionJpa.getConfiguracion().getPiezasConfiguracion().remove(piezaConfiguracionJpa);
			}
			piezaConfiguracionJpa.setConfiguracion(null);
			em.remove(piezaConfiguracionJpa);
		}
	}

	/**
	 * Permite guardar un piezaConfiguracion
	 * 
	 * @param PiezaConfiguracionJpa
	 *            Informacion del PiezaConfiguracionJpa a crear
	 */
	public Long persistPiezaConfiguracion(PiezaConfiguracionJpa piezaConfiguracion) {
		em.persist(piezaConfiguracion);
		return piezaConfiguracion.getId();
	}

	/**
	 * Permite actualizar un piezaConfiguracion
	 * 
	 * @param piezaConfiguracion
	 *            Informacion de la piezaConfiguracion a crear
	 */
	public Long mergePiezaConfiguracion(PiezaConfiguracionJpa piezaConfiguracion) {
		em.merge(piezaConfiguracion);
		return piezaConfiguracion.getId();
	}

	/**
	 * Lista todos los PiezasConfiguracion existentes
	 * 
	 */
	public List<PiezaConfiguracionJpa> listTodosPiezasConfiguracion() {
		TypedQuery<PiezaConfiguracionJpa> q = em.createNamedQuery(PiezaConfiguracionJpa.SQ_findAllPiezasConfiguracion,
				PiezaConfiguracionJpa.class);
		return q.getResultList();
	}

	/**
	 * Lista todos las piezasConfiguracion de una configuracion
	 * 
	 * @param idConfiguracion
	 *            identificador de la configuracion
	 */
	public List<PiezaConfiguracionJpa> listPiezasConfiguracion(Long idConfiguracion) {
		TypedQuery<PiezaConfiguracionJpa> q = em.createNamedQuery(
				PiezaConfiguracionJpa.SQ_findPiezasConfiguracionByIdConfiguracion, PiezaConfiguracionJpa.class);
		q.setParameter(PiezaConfiguracionJpa.SQ_PARAM_ID_CONFIGURACION, idConfiguracion);
		return q.getResultList();
	}

	// ***** PIEZAS *****//

	public PiezaJpa findPieza(Long idPieza) {
		PiezaJpa result = null;
		if ((ultimaPiezaConsultada != null) && (ultimaPiezaConsultada.getId().equals(idPieza))) {
			result = ultimaPiezaConsultada;
		} else {
			result = em.find(PiezaJpa.class, idPieza);
			ultimaPiezaConsultada = result;
		}
		return result;
	}

	/**
	 * Obtienete un pieza
	 * 
	 * @param id
	 *            Identificado de la pieza
	 */
	public PiezaJpa getPieza(Long id) {
		TypedQuery<PiezaJpa> q = em.createNamedQuery(PiezaJpa.SQ_findByIdPieza, PiezaJpa.class);
		q.setParameter(PiezaJpa.SQ_PARAM_ID, id);
		return q.getSingleResult();
	}

	/**
	 * Borra un pieza
	 * 
	 * @param id
	 *            Identificador del pieza
	 * 
	 */
	public void deletePieza(Long id) {
		PiezaJpa piezaJpa = em.find(PiezaJpa.class, id);
		em.remove(piezaJpa);
	}

	/**
	 * Permite guardar un pieza
	 * 
	 * @param PiezaJpa
	 *            Informacion del PiezaJpa a crear
	 */
	public Long persistPieza(PiezaJpa pieza) {
		em.persist(pieza);
		return pieza.getId();
	}

	/**
	 * Permite actualizar un pieza
	 * 
	 * @param pieza
	 *            Informacion de la pieza a crear
	 */
	public Long mergePieza(PiezaJpa pieza) {
		em.merge(pieza);
		return pieza.getId();
	}

	/**
	 * Lista todas las piezas
	 * 
	 */
	public List<PiezaJpa> listTodasPiezas() {
		TypedQuery<PiezaJpa> q = em.createNamedQuery(PiezaJpa.SQ_findAllPiezas, PiezaJpa.class);
		return q.getResultList();
	}

	/**
	 * Lista todas las piezas
	 * 
	 */
	public List<PiezaJpa> listPiezas(TipoPiezaBicicleta tipo) {
		TypedQuery<PiezaJpa> q = em.createNamedQuery(PiezaJpa.SQ_findByTipo, PiezaJpa.class);
		q.setParameter(PiezaJpa.SQ_PARAM_TIPO, tipo);
		return q.getResultList();
	}

	// ***** COLORES *****//

	/**
	 * Obtienete un color
	 * 
	 * @param id
	 *            Identificado de la color
	 */
	public ColorJpa getColor(Long id) {
		TypedQuery<ColorJpa> q = em.createNamedQuery(ColorJpa.SQ_findByIdColor, ColorJpa.class);
		q.setParameter(ColorJpa.SQ_PARAM_ID, id);
		return q.getSingleResult();
	}

	/**
	 * Borra un color
	 * 
	 * @param id
	 *            Identificador del color
	 * 
	 */
	public void deleteColor(Long id) {
		ColorJpa colorJpa = em.find(ColorJpa.class, id);
		em.remove(colorJpa);
	}

	/**
	 * Permite guardar un color
	 * 
	 * @param ColorJpa
	 *            Informacion del ColorJpa a crear
	 */
	public Long persistColor(ColorJpa color) {
		em.persist(color);
		return color.getId();
	}

	/**
	 * Permite actualizar un color
	 * 
	 * @param color
	 *            Informacion de la color a crear
	 */
	public Long mergeColor(ColorJpa color) {
		em.merge(color);
		return color.getId();
	}

	/**
	 * Lista todos los colores
	 * 
	 */
	public List<ColorJpa> listTodosColores() {
		TypedQuery<ColorJpa> q = em.createNamedQuery(ColorJpa.SQ_findAllColores, ColorJpa.class);
		return q.getResultList();
	}

}
