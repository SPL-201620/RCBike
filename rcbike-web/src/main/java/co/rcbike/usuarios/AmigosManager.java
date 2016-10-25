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

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.web.util.UtilRest;
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

    @PostConstruct
    public void init() {
        listAmigos();
    }

    public void removerAmigo(String emailAmigo) {
        modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                .path(OperacionesUsuarios.OP_REMOVER_AMIGO).request()
                .post(Entity.json(Arrays.asList(AutenticacionManager.emailAutenticado(), emailAmigo)));

        amigos.stream().filter(amigo -> amigo.getEmail().equals(emailAmigo)).findFirst()
                .ifPresent(amigo -> amigos.remove(amigo));

        listNoAmigos();
    }

    public void agregarAmigo(Usuario nuevoAmigo) {
        modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                .path(OperacionesUsuarios.OP_AGREGAR_AMIGO).request()
                .post(Entity.json(Arrays.asList(AutenticacionManager.emailAutenticado(), nuevoAmigo.getEmail())));
        noAmigos.remove(nuevoAmigo);
        amigos.add(nuevoAmigo);
    }

    public void listAmigos() {
        amigos = modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                .path(OperacionesUsuarios.OP_AMIGOS).path(AutenticacionManager.emailAutenticado()).request()
                .get(UtilRest.TYPE_LIST_USUARIO);
    }

    public void listNoAmigos() {
        noAmigos = modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                .path(OperacionesUsuarios.OP_NOAMIGOS).path(AutenticacionManager.emailAutenticado())
                .queryParam("filtro", filtroNoAmigo).request().get(UtilRest.TYPE_LIST_USUARIO);
    }

    public void cambiarEstadoCandidatos() {
        mostrarNoAmigos = !mostrarNoAmigos;
        if (mostrarNoAmigos) {
            listNoAmigos();
        }
    }

}
