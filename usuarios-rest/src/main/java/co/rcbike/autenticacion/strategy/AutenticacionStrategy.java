package co.rcbike.autenticacion.strategy;

import java.util.Map;

import co.rcbike.autenticacion.model.ResultadoAutenticacion;

public abstract class AutenticacionStrategy {

    public static final String ATTR_AUTH_STRATEGY = "authStrategy";
    public static final String ATTR_PAYLOAD = "payload";

    public abstract ResultadoAutenticacion autenticar(Map<String, Object> valoresAutenticacion);
}
