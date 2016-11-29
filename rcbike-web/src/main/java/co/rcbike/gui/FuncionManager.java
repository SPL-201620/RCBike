package co.rcbike.gui;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;
import org.omnifaces.util.Faces;

import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@Named
@Eager
@ApplicationScoped
@JBossLog
public class FuncionManager implements Serializable {

    private Properties funcionEmpaquetados;

    @PostConstruct
    public void init() {
        funcionEmpaquetados = new Properties();
        try {
            funcionEmpaquetados.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("rcbike-funcion.properties"));
        } catch (IOException e) {
            log.error("Error cargando propiedades de funciones rcbike-funcion.properties", e);
            throw new RuntimeException(e);
        }
    }

    public boolean autTwitter() {
        return new Boolean((String) funcionEmpaquetados.getOrDefault("autenticacion_twitter", "false"));
    }

    public boolean autFacebook() {
        return new Boolean((String) funcionEmpaquetados.getOrDefault("autenticacion_facebook", "false"));
    }

    public boolean twitterWeb() {
        return new Boolean((String) funcionEmpaquetados.getOrDefault("twitter_web", "false"));
    }

    public boolean twitterRest() {
        return sinc_redes() && new Boolean((String) funcionEmpaquetados.getOrDefault("twitter_rest", "false"));
    }

    public boolean facebookWeb() {
        return new Boolean((String) funcionEmpaquetados.getOrDefault("facebook_web", "false"));
    }

    public boolean facebookRest() {
        return sinc_redes() && new Boolean((String) funcionEmpaquetados.getOrDefault("facebook_rest", "false"));
    }

    private boolean sinc_redes() {
        return Faces.evaluateExpressionGet("#{modulosManager.sinc_redes()}");
    }

    public boolean twitterSection() {
        return twitterWeb() || twitterRest();
    }

    public boolean facebookSection() {
        return facebookWeb() || facebookRest();
    }
}
