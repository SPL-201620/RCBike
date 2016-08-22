package co.rcbike.desplazamientos.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class DesplazamientosService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

}
