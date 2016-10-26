package co.rcbike.alquiler.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.alquiler.model.SitioAlquiler;

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

    //@Inject
    //private Logger log;

    @Inject
    private EntityManager em;

    /**
     * Lista todos los Sitios de Alquileres de bicicleta existentes
     * 
     */
    public List<SitioAlquiler> listTodosSitiosAlquiler() {
        TypedQuery<SitioAlquiler> q = em.createNamedQuery(SitioAlquiler.SQ_find, SitioAlquiler.class);
        return q.getResultList();
    }

    /**
     * Lista todos las Sitios de Alquileres por un usuario dado su email
     * 
     * @param emailCreador
     *            email del usuario creador
     */
    public List<SitioAlquiler> listSitiosAlquiler(String emailCreador) {
        TypedQuery<SitioAlquiler> q = em.createNamedQuery(SitioAlquiler.SQ_findByCreador, SitioAlquiler.class);
        q.setParameter(SitioAlquiler.SQ_PARAM_EMAIL_CREADOR, emailCreador);
        return q.getResultList();
    }

    /**
     * Permite guardar un Sitio de Alquiler de bicicleta
     * 
     * @param SitioAlquiler
     *            informacion de la SitioAlquiler a crear
     */
    public void guardarSitioAlquiler(SitioAlquiler sitioAlquiler) {
        em.persist(sitioAlquiler);
    }
    
    /**
     * Lista todos los recorridos grupales no vencidos cercanos a una latitud y
     * longitud
     * 
     * @param latitud
     *            latitud geografica de la ruta
     * @param longitud
     *            longitud geografica de la ruta
     * 
     */
    public List<SitioAlquiler> listSitiosAlquilerCercanos(BigDecimal latitud, BigDecimal longitud) {
        BigDecimal latitudInicio = latitud.subtract(DISTANCIA_RUTA_CERCANA);
        BigDecimal latitudFinal = latitud.add(DISTANCIA_RUTA_CERCANA);
        BigDecimal longitudInicio = longitud.subtract(DISTANCIA_RUTA_CERCANA);
        BigDecimal longitudFinal = longitud.add(DISTANCIA_RUTA_CERCANA);
        TypedQuery<SitioAlquiler> q = em.createNamedQuery(SitioAlquiler.SQ_findByPunto, SitioAlquiler.class);
        q.setParameter(SitioAlquiler.SQ_PARAM_LATITUD_INICIO, latitudInicio);
        q.setParameter(SitioAlquiler.SQ_PARAM_LATITUD_FINAL, latitudFinal);
        q.setParameter(SitioAlquiler.SQ_PARAM_LONGITUD_INICIO, longitudInicio);
        q.setParameter(SitioAlquiler.SQ_PARAM_LONGITUD_FINAL, longitudFinal);
        return q.getResultList();
    }


}
