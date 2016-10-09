package co.rcbike.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.core.GenericType;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.Usuario;
import eu.agilejava.snoop.client.SnoopServiceClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class AmigosManager implements Serializable {

    @Getter
    @Setter
    private List<Usuario> amigos;

    @Getter
    @Setter
    private String filtroCandidato;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @PostConstruct
    public void init() {
        String email = AutenticacionManager.emailAutenticado();
        SnoopServiceClient usuariosRest = modulosManager.clienteSnoop(Modulo.usuarios);
        amigos = usuariosRest.getServiceRoot().path("usuarios").path("amigos").path(email).request()
                .get(new GenericType<List<Usuario>>() {
                });
    }

    public void removerAmigo(String emailAmigo) {
        log.info("remover amigo " + emailAmigo);
        amigos.stream().filter(amigo -> amigo.getEmail().equals(emailAmigo)).findAny()
                .ifPresent(amigo -> amigos.remove(amigo));
        // TODO remover amigo de backend
    }

    public void agregarAmigo() {

    }

    public void listCandidatosAmigos() {

    }
}
