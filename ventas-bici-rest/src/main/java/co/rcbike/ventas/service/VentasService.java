
package co.rcbike.ventas.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.ventas.model.PiezaVentaBicicleta;
import co.rcbike.ventas.model.VentaBicicleta;

@Stateless
public class VentasService {


    //@Inject
    //private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Lista todas las ventas de bicicleta existentes
     * 
     */
    public List<VentaBicicleta> listTodasVentas() {
        TypedQuery<VentaBicicleta> q = em.createNamedQuery(VentaBicicleta.SQ_findAll, VentaBicicleta.class);
        return q.getResultList();
    }

    /**
     * Lista todos las ventas por un usuario dado su email
     * 
     * @param emailCreador
     *            email del usuario creador
     */
    public List<VentaBicicleta> listVentas(String emailCreador) {
        TypedQuery<VentaBicicleta> q = em.createNamedQuery(VentaBicicleta.SQ_findByCreador, VentaBicicleta.class);
        q.setParameter(VentaBicicleta.SQ_PARAM_EMAIL_CREADOR, emailCreador);
        return q.getResultList();
    }

    /**
     * Permite crear una pieza venta de bicicleta 
     * 
     * @param piezaVentaBicicleta informacion de la pieza de la bicicleta
     */
    public void guardarPieza(PiezaVentaBicicleta piezaVentaBicicleta) {
        em.persist(piezaVentaBicicleta);
    }

    /**
     * Permite guardar una Venta de bicicleta
     * 
     * @param VentaBicicleta informacion de la VentaBicicleta a crear
     */
    public void guardarVenta(VentaBicicleta ventaBicicleta) {
        em.persist(ventaBicicleta);
    }

    /**
     * Lista todas las piezas de una Venta de Bicicleta
     * 
     * @param idVenta identificador de la Venta de Bicicleta
     */
    public List<PiezaVentaBicicleta> listPiezas(Long idVenta) {
        TypedQuery<PiezaVentaBicicleta> q = em.createNamedQuery(PiezaVentaBicicleta.SQ_findByIdVenta, PiezaVentaBicicleta.class);
        q.setParameter(PiezaVentaBicicleta.SQ_PARAM_ID_CONFIGURACION, idVenta);
        return q.getResultList();
    }

    /**
     * Lista todos las piezas
     * 
     */
    public List<PiezaVentaBicicleta> listTodasPiezas() {
        TypedQuery<PiezaVentaBicicleta> q = em.createNamedQuery(PiezaVentaBicicleta.SQ_findAllPiezas, PiezaVentaBicicleta.class);
        return q.getResultList();
    }
}
