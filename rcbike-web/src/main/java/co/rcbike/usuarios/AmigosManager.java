package co.rcbike.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModUsuarios;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.Usuario;
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
    private String filtroNoAmigo;

    @Getter
    @Setter
    private List<Usuario> noAmigos = new ArrayList<Usuario>();

    @Getter
    @Setter
    private boolean mostrarNoAmigos;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    private GenericType<List<Usuario>> gTListUsuario = new GenericType<List<Usuario>>() {
    };

    @PostConstruct
    public void init() {
        listAmigos();
    }

    public void removerAmigo(String emailAmigo) {
        modulosManager.root(Modulo.usuarios).path(ModUsuarios.ENDPNT_USUARIOS).path("remover-amigo").request()
                .post(Entity.json(Arrays.asList(AutenticacionManager.emailAutenticado(), emailAmigo)));

        amigos.stream().filter(amigo -> amigo.getEmail().equals(emailAmigo)).findFirst()
                .ifPresent(amigo -> amigos.remove(amigo));

        listNoAmigos();
    }

    public void agregarAmigo(Usuario nuevoAmigo) {
        modulosManager.root(Modulo.usuarios).path(ModUsuarios.ENDPNT_USUARIOS).path("agregar-amigo").request()
                .post(Entity.json(Arrays.asList(AutenticacionManager.emailAutenticado(), nuevoAmigo.getEmail())));
        noAmigos.remove(nuevoAmigo);
        amigos.add(nuevoAmigo);
    }

    public void listAmigos() {
        amigos = modulosManager.root(Modulo.usuarios).path(ModUsuarios.ENDPNT_USUARIOS).path("amigos")
                .path(AutenticacionManager.emailAutenticado()).request().get(gTListUsuario);
    }

    public void listNoAmigos() {
        noAmigos = modulosManager.root(Modulo.usuarios).path(ModUsuarios.ENDPNT_USUARIOS).path("noamigos")
                .path(AutenticacionManager.emailAutenticado()).queryParam("filtro", filtroNoAmigo).request()
                .get(gTListUsuario);
    }

    public void cambiarEstadoCandidatos() {
        mostrarNoAmigos = !mostrarNoAmigos;
        if (mostrarNoAmigos) {
            listNoAmigos();
        }
    }

}
