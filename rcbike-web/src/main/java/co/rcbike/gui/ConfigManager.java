package co.rcbike.gui;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.omnifaces.cdi.Eager;

import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@Named
@Eager
@ApplicationScoped
@JBossLog
public class ConfigManager implements Serializable {

    private Properties config;

    @PostConstruct
    public void init() {
        config = new Properties();
        try {
            config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("rcbike-config.properties"));
        } catch (IOException e) {
            log.error("Error cargando propiedades de funciones rcbike-config.properties", e);
            throw new RuntimeException(e);
        }
    }

    public String webServerUrl() {
        return config.getProperty("webserver");
    }

}
