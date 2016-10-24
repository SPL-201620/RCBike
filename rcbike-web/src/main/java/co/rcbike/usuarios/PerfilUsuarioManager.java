package co.rcbike.usuarios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class PerfilUsuarioManager implements Serializable {

    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @PostConstruct
    public void init() {

    }

    public String nombreUsuario() {
        Usuario usuario = getUsuario();
        return usuario != null ? usuario.nombreCompleto() : "";
    }

    public Usuario getUsuario() {
        if (usuario == null && autenticacionManager.autenticado()) {
            String email = AutenticacionManager.emailAutenticado();
            usuario = modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                    .path(OperacionesUsuarios.OP_USUARIO).path(email).request().get(Usuario.class);
        }
        return usuario;
    }
}
