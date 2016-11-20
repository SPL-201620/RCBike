package co.rcbike.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
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

    @Inject
    private UsuariosGateway gateway;

    @PostConstruct
    public void init() {
        listAmigos();
    }

    public void removerAmigo(String emailAmigo) {
        gateway.removerAmigo(Arrays.asList(AutenticacionManager.emailAutenticado(), emailAmigo));

        amigos.stream().filter(amigo -> amigo.getEmail().equals(emailAmigo)).findFirst()
                .ifPresent(amigo -> amigos.remove(amigo));

        listNoAmigos();
    }

    public void agregarAmigo(Usuario nuevoAmigo) {
        gateway.agregarAmigo(Arrays.asList(AutenticacionManager.emailAutenticado(), nuevoAmigo.getEmail()));

        noAmigos.remove(nuevoAmigo);
        amigos.add(nuevoAmigo);
    }

    public void listAmigos() {
        amigos = gateway.listAmigos(AutenticacionManager.emailAutenticado());
    }

    public void listNoAmigos() {
        noAmigos = gateway.listNoAmigos(AutenticacionManager.emailAutenticado(), filtroNoAmigo);
    }

    public void cambiarEstadoCandidatos() {
        mostrarNoAmigos = !mostrarNoAmigos;
        if (mostrarNoAmigos) {
            listNoAmigos();
        }
    }

}
