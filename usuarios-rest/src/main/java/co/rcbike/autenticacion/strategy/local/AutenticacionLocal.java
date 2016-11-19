package co.rcbike.autenticacion.strategy.local;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import co.rcbike.autenticacion.model.AutenticacionUsuario;
import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.autenticacion.model.ResultadoAutenticacion.EstadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;

@Stateless
public class AutenticacionLocal extends AutenticacionStrategy {

    @Inject
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public ResultadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion) {
        Map<String, String> payload = (Map<String, String>) valoresAutenticacion.get(ATTR_PAYLOAD);
        String email = payload.get("email");
        String clave = payload.get("clave");
        EstadoAutenticacion autenticar = autenticar(email, clave);

        ResultadoAutenticacion retorno = new ResultadoAutenticacion();
        retorno.setEstado(autenticar);
        retorno.setEmail(email);
        if (autenticar.equals(EstadoAutenticacion.NO_EXISTE_USUARIO)) {
            retorno.setClave(clave);
            retorno.setRequiereClave(true);
        }
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
