
package co.rcbike.configurador_bici.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.configurador_bici.model.ConfiguracionBicicleta;
import co.rcbike.configurador_bici.model.PiezaBicicleta;
import co.rcbike.configurador_bici.model.TipoPiezaBicicleta;

@Stateless
public class ConfiguradorBiciService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Valida una configuracion de bicicleta
     * 
     * @param configuracion
     *            configuracion de una bicicleta
     */
    public boolean validarConfiguracion(ConfiguracionBicicleta configuracion) {
        return (configuracion != null);
    }

    /**
     * Lista todos las configuraciones por un usuario dado su email
     * 
     * @param emailCreador
     *            email del usuario creador
     */
    public List<ConfiguracionBicicleta> listConfiguraciones(String emailCreador) {
        TypedQuery<ConfiguracionBicicleta> q = em.createNamedQuery(ConfiguracionBicicleta.SQ_findByCreador, ConfiguracionBicicleta.class);
        q.setParameter(ConfiguracionBicicleta.SQ_PARAM_EMAIL_CREADOR, emailCreador);
        return q.getResultList();
    }

    /**
     * Permite crear una pieza de bicicleta en una Configuracion de Bicicleta conociendo su id
     * 
     * @param idConfiguracion
     *            identificador de la Configuracion de Bicicleta
     * @param tipo
     *            tipo de la pieza de bicicleta
     * @param descripcion
     *            descripcion de la pieza de bicicleta
     */
    public void guardarPieza(Long idConfiguracion, TipoPiezaBicicleta tipo, String descripcion) {
    	log.info("\n La descripcion: " + descripcion);
    	log.info("\n El tipo: " + tipo);
    	log.info("\n El idConfiguracion: " + idConfiguracion + "\n");
        PiezaBicicleta pieza = new PiezaBicicleta();
        pieza.setConfiguracion(em.getReference(ConfiguracionBicicleta.class, idConfiguracion));
        pieza.setTipo(tipo);
        pieza.setDescripcion(descripcion);
        em.persist(pieza);
    }

    /**
     * Permite guardar una Configuracion de bicicleta
     * 
     * @param ConfiguracionBicicleta
     *            informacion de la ConfiguracionBicicleta a crear
     */
    public void guardarConfiguracion(ConfiguracionBicicleta configuracion) {
        em.persist(configuracion);
    }

    /**
     * Lista todos las piezas de una Configuracion de Bicicleta
     * 
     * @param idConfiguracion
     *            identificador de la Configuracion de Bicicleta
     */
    public List<PiezaBicicleta> listPiezas(Long idConfiguracion) {
        TypedQuery<PiezaBicicleta> q = em.createNamedQuery(PiezaBicicleta.SQ_findByidConfiguracion, PiezaBicicleta.class);
        q.setParameter(PiezaBicicleta.SQ_PARAM_ID_CONFIGURACION, idConfiguracion);
        return q.getResultList();
    }
}
