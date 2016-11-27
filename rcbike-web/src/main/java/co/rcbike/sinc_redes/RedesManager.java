package co.rcbike.sinc_redes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class RedesManager implements Serializable {

    @Inject
    private RedesGateway gateway;

    @Getter
    @Setter
    private String mensaje;

    public void facebook() {
        log.info("Compartir Facebook");
        gateway.compartirFacebook(mensaje);
    }

    public void twitter() {
        log.info("Compartir Twitter");
        gateway.compartirTwitter(mensaje);
    }
}
