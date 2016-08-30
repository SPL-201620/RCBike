package co.rcbike.mensajeria.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class MensajeriaRepository {

    @Inject
    private EntityManager em;

}
