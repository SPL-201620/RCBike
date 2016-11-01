package co.rcbike.autenticacion.strategy.twitter;

import java.util.Map;

import javax.ejb.Stateless;

import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;

@Stateless
public class AutenticacionTwitter extends AutenticacionStrategy {

    @Override
    public ResultadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion) {
        // TODO Auto-generated method stub
        return null;
    }

}
