package co.rcbike.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.google.common.base.Strings;

import co.rcbike.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuariosManager implements Serializable {

    @Inject
    UsuariosGateway gateway;

    @Getter
    @Setter
    @ManagedProperty(value = "#{amigosManager}")
    private AmigosManager amigosManager;

    @Getter
    @Setter
    private List<Usuario> busquedaUsuarios;

    public List<Usuario> buscarUsuariosByEmail(List<String> emails) {
        return gateway.listUsuarioByEmail(emails);

    }

    public List<Usuario> filtrarUsuarios(String filtro) {
        if (Strings.isNullOrEmpty(filtro)) {
            amigosManager.listAmigos();
            return amigosManager.getAmigos();
        } else {
            return gateway.filtrarUsuarios(filtro);
        }
    }

}
