package co.rcbike.usuarios;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.usuarios.model.RegistroUsuario;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class UsuariosGateway extends RcbikeRestGateway {

    public static final GenericType<List<Usuario>> TYPE_LIST_USUARIO = new GenericType<List<Usuario>>() {
    };

    @Inject
    @Snoop(serviceName = OperacionesUsuarios.EP_USUARIOS)
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }

    public void removerAmigo(List<String> list) {
        webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_REMOVER_AMIGO).request()
                .post(Entity.json(list));
    }

    public void agregarAmigo(List<String> list) {
        webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_AGREGAR_AMIGO).request()
                .post(Entity.json(list));
    }

    public List<Usuario> listAmigos(String email) {
        return webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_AMIGOS).path(email)
                .request().get(TYPE_LIST_USUARIO);
    }

    public List<Usuario> listNoAmigos(String email, String filtroNoAmigo) {
        return webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_NOAMIGOS).path(email)
                .queryParam("filtro", filtroNoAmigo).request().get(TYPE_LIST_USUARIO);
    }

    public List<Usuario> listUsuarioByEmail(List<String> emails) {
        return webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_USUARIOS)
                .path(String.join("|", emails)).request().get(TYPE_LIST_USUARIO);
    }

    public List<Usuario> filtrarUsuarios(String filtro) {
        return webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_USUARIOS)
                .queryParam("filtro", filtro).request().get(TYPE_LIST_USUARIO);
    }

    public Usuario findUsuario(String email) {
        return webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_USUARIO).path(email)
                .request().get(Usuario.class);
    }

    public Response requestUsuario(String email) {
        return webTarget().path(OperacionesUsuarios.EP_USUARIOS).path(OperacionesUsuarios.OP_USUARIO).path(email)
                .request().get();
    }

    public Response crearUsuario(RegistroUsuario regUsuario) {
        return webTarget().path(OperacionesUsuarios.EP_USUARIOS).request().post(Entity.json(regUsuario));
    }

}
