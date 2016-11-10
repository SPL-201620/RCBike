
package co.rcbike.ventas.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.ventas.model.VentaJpa;

@Stateless
public class VentasService {

    // @Inject
    // private Logger log;

    @Inject
    private EntityManager em;

    private VentaJpa ultimaVentaConsultada;

    // ***** CONFIGURACIONES *****//

    public VentaJpa findVenta(Long idVenta) {
        VentaJpa result = null;
        if ((ultimaVentaConsultada != null) && (ultimaVentaConsultada.getId().equals(idVenta))) {
            result = ultimaVentaConsultada;
        } else {
            result = em.find(VentaJpa.class, idVenta);
            ultimaVentaConsultada = result;
        }
        return result;
    }

    /**
     * Obtienete una venta
     * 
     * @param id
     *            Identificado de la venta
     */
    public VentaJpa getVenta(Long id) {
        TypedQuery<VentaJpa> q = em.createNamedQuery(VentaJpa.SQ_findByIdVenta, VentaJpa.class);
        q.setParameter(VentaJpa.SQ_PARAM_ID, id);
        return q.getSingleResult();
    }

    /**
     * Lista todos los recorridos individuales realizados por un usuario
     * 
     * @param id
     *            Identificado de la venta
     * 
     */
    public void deleteVenta(Long id) {
        VentaJpa ventaJpa = em.find(VentaJpa.class, id);
        if (ventaJpa != null) {
            em.remove(ventaJpa);
        }
    }

    /**
     * Permite guardar una venta
     * 
     * @param venta
     *            Informacion de la venta a crear
     */
    public Long persistVenta(VentaJpa venta) {
        em.persist(venta);
        return venta.getId();
    }

    /**
     * Permite actualizar una venta
     * 
     * @param venta
     *            Informacion de la venta a crear
     */
    public Long mergeVenta(VentaJpa venta) {
        em.merge(venta);
        return venta.getId();
    }

    /**
     * Lista todas las ventas
     * 
     */
    public List<VentaJpa> listTodosVentas() {
        TypedQuery<VentaJpa> q = em.createNamedQuery(VentaJpa.SQ_findAllVentas, VentaJpa.class);
        return q.getResultList();
    }

    /**
     * Lista todas las ventas
     * 
     * @param emailCreador
     *            email del usuario creador
     */
    public List<VentaJpa> listVentas(String emailCreador) {
        TypedQuery<VentaJpa> q = em.createNamedQuery(VentaJpa.SQ_findByCreador, VentaJpa.class);
        q.setParameter(VentaJpa.SQ_PARAM_EMAIL_CREADOR, emailCreador);
        return q.getResultList();
    }

}
