package co.rcbike.usuarios.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class UsuariosRepository {

    @Inject
    private EntityManager em;

}
