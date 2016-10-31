package co.rcbike.autenticacion.strategy.twitter;

import java.util.Map;

import javax.ejb.Stateless;

import co.rcbike.autenticacion.service.AutenticacionService.EstadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;

@Stateless
public class AutenticacionTwitter extends AutenticacionStrategy {

    @Override
    public EstadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion) {
        // TODO Auto-generated method stub
        return null;
    }

}
