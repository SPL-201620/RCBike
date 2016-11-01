package co.rcbike.autenticacion.strategy;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import co.rcbike.autenticacion.model.AutenticacionUsuario;
import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.autenticacion.model.ResultadoAutenticacion.EstadoAutenticacion;

@Stateless
public class AutenticacionLocal extends AutenticacionStrategy {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public ResultadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion) {
        Map<String, String> payload = (Map<String, String>) valoresAutenticacion.get(ATTR_PAYLOAD);
        String email = payload.get("email");
        log.fine("Autenticando Localmente: " + email);
        EstadoAutenticacion autenticar = autenticar(email, payload.get("clave"));
        ResultadoAutenticacion retorno = new ResultadoAutenticacion();
        retorno.setEstado(autenticar);
        retorno.setEmail(email);
        return retorno;
    }

    private EstadoAutenticacion autenticar(String email, String clave) {
        AutenticacionUsuario aut = findByEmail(email);
        if (aut != null) {
            return aut.getClave().equals(clave) ? EstadoAutenticacion.OK : EstadoAutenticacion.CLAVE_ERRONEA;
        } else {
            return EstadoAutenticacion.NO_EXISTE_USUARIO;
        }
    }

    private AutenticacionUsuario findByEmail(String email) {
        TypedQuery<AutenticacionUsuario> q = em.createNamedQuery(AutenticacionUsuario.SQ_autByEmail,
                AutenticacionUsuario.class);
        q.setParameter(AutenticacionUsuario.SQ_PARAM_EMAIL, email);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
