package co.rcbike.configurador_bici.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ConfiguradorBiciRepository {

    @Inject
    private EntityManager em;

}
