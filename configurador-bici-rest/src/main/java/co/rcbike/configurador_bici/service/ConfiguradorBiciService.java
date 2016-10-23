
package co.rcbike.configurador_bici.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.configurador_bici.model.ConfiguracionBicicleta;
import co.rcbike.configurador_bici.model.PiezaBicicleta;

@Stateless
public class ConfiguradorBiciService {

    //@Inject
    //private Logger log;

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
     * Lista todas las configuraciones de bicicleta existentes
     * 
     * @param emailCreador
     *            email del usuario creador
     */
    public List<ConfiguracionBicicleta> listTodasConfiguraciones() {
        TypedQuery<ConfiguracionBicicleta> q = em.createNamedQuery(ConfiguracionBicicleta.SQ_find, ConfiguracionBicicleta.class);
        return q.getResultList();
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
     * Permite crear una pieza de bicicleta 
     * 
     * @param piezaBicicleta informacion de la pieza de la bicicleta
     */
    public void guardarPieza(PiezaBicicleta piezaBicicleta) {
        em.persist(piezaBicicleta);
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
        TypedQuery<PiezaBicicleta> q = em.createNamedQuery(PiezaBicicleta.SQ_findByIdConfiguracion, PiezaBicicleta.class);
        q.setParameter(PiezaBicicleta.SQ_PARAM_ID_CONFIGURACION, idConfiguracion);
        return q.getResultList();
    }

    /**
     * Lista todos las piezas
     * 
     */
    public List<PiezaBicicleta> listTodasPiezas() {
        TypedQuery<PiezaBicicleta> q = em.createNamedQuery(PiezaBicicleta.SQ_findAll, PiezaBicicleta.class);
        return q.getResultList();
    }
}
