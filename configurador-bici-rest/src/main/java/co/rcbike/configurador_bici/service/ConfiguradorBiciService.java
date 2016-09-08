
package co.rcbike.configurador_bici.service;

import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import co.rcbike.configurador_bici.model.Configuracion;
import co.rcbike.configurador_bici.model.Parte;
import co.rcbike.configurador_bici.model.TipoParte;

@Stateless
public class ConfiguradorBiciService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    public boolean validarConfiguracion(Configuracion conf) {
        throw new IllegalStateException("Not Implemented");
    }

    public Map<TipoParte, Parte> listParte() {
        throw new IllegalStateException("Not Implemented");
    }
}
