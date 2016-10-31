package co.rcbike.autenticacion.strategy;

import java.util.Map;

import co.rcbike.autenticacion.service.AutenticacionService.EstadoAutenticacion;

public abstract class AutenticacionStrategy {

    public static final String ATTR_AUTH_STRATEGY = "authStrategy";
    public static final String ATTR_PAYLOAD = "payload";

    public abstract EstadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion);
}
