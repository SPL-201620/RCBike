package co.rcbike.desplazamientos.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import co.rcbike.desplazamientos.model.Recorrido;

@Stateless
public class DesplazamientosService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Lista todos los recorridos grupales vigentes del sistema
     * 
     * @param tipo
     *            indica si se quieren consultar los recorridos de tipo grupal,
     *            individual todos los recorridos
     * @return
     */
    public List<Recorrido> listRecorridoGrupal() {
        throw new IllegalStateException("Not Implemented");
    }

    /**
     * Lista todos los recorridos individuales vigentes del usuario
     * 
     * @param email
     * @return
     */
    public List<Recorrido> listRecorridoIndividual(String email) {
        throw new IllegalStateException("Not Implemented");
    }

    /**
     * Lista todos los recorridos realizados por un usuario
     * 
     * @param email
     *            identificador del usuario
     */
    public List<Recorrido> listRecorridoRealizado(String email) {
        throw new IllegalStateException("Not Implemented");
    }

    /**
     * Permite crear cualquier tipo de recorrido
     * 
     * @param recorrido
     *            informacion del recorrido a crear
     */
    public void crearRecorrido(Recorrido recorrido) {
        throw new IllegalStateException("Not Implemented");
    }

    /**
     * Marca un recorrido como realizado
     * 
     * @param idRecorrido
     */
    public void realizarRecorrido(Long idRecorrido) {
        throw new IllegalStateException("Not Implemented");
    }
}
