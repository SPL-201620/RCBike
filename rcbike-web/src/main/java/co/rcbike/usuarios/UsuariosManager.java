package co.rcbike.usuarios;

import static co.rcbike.web.util.UtilRest.TYPE_LIST_USUARIO;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.google.common.base.Strings;

import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuariosManager implements Serializable {

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{amigosManager}")
    private AmigosManager amigosManager;

    @Getter
    @Setter
    private List<Usuario> busquedaUsuarios;

    public List<Usuario> buscarUsuariosByEmail(List<String> emails) {
        List<Usuario> list = modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                .path(OperacionesUsuarios.OP_USUARIOS).path(String.join("|", emails)).request().get(TYPE_LIST_USUARIO);
        return list;
    }

    public List<Usuario> filtrarUsuarios(String filtro) {
        if (Strings.isNullOrEmpty(filtro)) {
            amigosManager.listAmigos();
            return amigosManager.getAmigos();
        } else {
            return modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                    .path(OperacionesUsuarios.OP_USUARIOS).queryParam("filtro", filtro).request()
                    .get(TYPE_LIST_USUARIO);
        }
    }

}
