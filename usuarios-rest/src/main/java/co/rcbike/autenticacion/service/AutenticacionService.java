package co.rcbike.autenticacion.service;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

import co.rcbike.autenticacion.model.AutenticacionUsuario;
import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;

@Stateless
public class AutenticacionService {

    @Inject
    private EntityManager em;

    public void registrar(String email, String clave) {
        AutenticacionUsuario entity = new AutenticacionUsuario();
        entity.setEmail(email);
        entity.setClave(clave);
        em.persist(entity);
    }

    public ResultadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion) {

        String authStrategy = (String) valoresAutenticacion.get("authStrategy");
        try {
            AutenticacionStrategy strategy = (AutenticacionStrategy) new InitialContext()
                    .lookup("java:module/Autenticacion" + authStrategy);
            return strategy.autenticar(valoresAutenticacion);
        } catch (NamingException e) {
            throw new UnknownError(e.getMessage());
        }
    }
}
