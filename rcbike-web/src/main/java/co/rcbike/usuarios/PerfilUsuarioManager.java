package co.rcbike.usuarios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.usuarios.model.Usuario;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class PerfilUsuarioManager implements Serializable {

    @Setter
    private Usuario usuario;

    @Inject
    private UsuariosGateway gateway;

    @PostConstruct
    public void init() {

    }

    public String nombreUsuario() {
        Usuario usuario = getUsuario();
        return usuario != null ? usuario.nombreCompleto() : "";
    }

    public Usuario getUsuario() {
        if (usuario == null && AutenticacionManager.autenticado()) {

            usuario = gateway.findUsuario(AutenticacionManager.emailAutenticado());
        }
        return usuario;
    }

}
