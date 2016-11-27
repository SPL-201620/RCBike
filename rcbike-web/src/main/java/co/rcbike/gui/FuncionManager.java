package co.rcbike.gui;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

@SuppressWarnings("serial")
@Named
@Eager
@ApplicationScoped
public class FuncionManager implements Serializable {

    private Properties funcionEmpaquetados;

    private Map<String, Boolean> modulosDesplegados = new HashMap<>(8);

    @PostConstruct
    public void init() {
        funcionEmpaquetados = new Properties();
        try {
            funcionEmpaquetados.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("rcbike-funcion.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        funcionEmpaquetados.entrySet().stream()
                .forEach(entry -> modulosDesplegados.put(entry.getKey().toString(), false));
    }

    public boolean compartirTwitter() {
        return true;
    }
    public boolean compartirFacebook() {
        return true;
    }

    public boolean autTwitter() {
        return true;
    }
    public boolean autFacebook() {
        return true;
    }
}
