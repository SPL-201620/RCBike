
package co.rcbike.sinc_redes.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@Stateless
public class SincRedesService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

}
