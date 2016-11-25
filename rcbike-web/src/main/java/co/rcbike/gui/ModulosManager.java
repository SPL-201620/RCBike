package co.rcbike.gui;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.menu.MenuModel;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@ApplicationScoped
public class ModulosManager implements Serializable {

    public static final Boolean DEFAULT_MODULES = true;

    @Getter
    @Setter
    private MenuModel model;

    private Properties modulosEmpaquetados;

    private Map<String, Boolean> modulosDesplegados = new HashMap<>(8);

    @PostConstruct
    public void init() {
        modulosEmpaquetados = new Properties();
        try {
            modulosEmpaquetados.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("rcbike-modulos.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        modulosEmpaquetados.entrySet().stream().forEach(entry -> modulosDesplegados.put(entry.getKey().toString(),
                Boolean.valueOf(entry.getValue().toString())));
    }

    public boolean alquiler() {
        return estadoModulo("alquiler");
    }

    public boolean configurador() {
        return estadoModulo("configurador");
    }

    public boolean desplazamientos() {
        return estadoModulo("desplazamientos");
    }

    public boolean mensajeria() {
        return estadoModulo("mensajeria");
    }

    public boolean reportes() {
        return estadoModulo("reportes");
    }

    public boolean sinc_redes() {
        return estadoModulo("sinc_redes");
    }

    public boolean ventas() {
        return estadoModulo("ventas");
    }

    private boolean fueEmpaquetado(String modulo) {
        return Boolean.valueOf(modulosEmpaquetados.getOrDefault(modulo, "false").toString());
    }

    private boolean estadoModulo(String modulo) {
        return !fueEmpaquetado(modulo) ? false : modulosDesplegados.getOrDefault(modulo, false);
    }

    public void subirModulo(String modulo) {
        modulosDesplegados.put(modulo, fueEmpaquetado(modulo) ? Boolean.TRUE : Boolean.FALSE);
    }

    public void bajarModulo(String modulo) {
        modulosDesplegados.put(modulo, Boolean.FALSE);
    }
}
