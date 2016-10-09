package co.rcbike.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private List<Usuario> candidatos;

    @Getter
    @Setter
    private boolean mostrarCandidatos = true;

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
        candidatos = new ArrayList<>(amigos.size());
    }

    public void removerAmigo(String emailAmigo) {
        log.info("remover amigo " + emailAmigo);
        Optional<Usuario> remover = amigos.stream().filter(amigo -> amigo.getEmail().equals(emailAmigo)).findFirst();
        remover.ifPresent(amigo -> amigos.remove(amigo));
        remover.ifPresent(ex -> candidatos.add(ex));
        // TODO remover amigo de backend
    }

    public void agregarAmigo(Usuario nuevoAmigo) {
        amigos.add(nuevoAmigo);
        candidatos.remove(nuevoAmigo);
    }

    public void listCandidatosAmigos() {

    }

    public void cambiarEstadoCandidatos() {
        mostrarCandidatos = !mostrarCandidatos;
    }

}
