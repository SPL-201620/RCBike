package co.rcbike.autenticacion;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import co.rcbike.autenticacion.model.OperacionesAutenticacion;
import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.usuarios.model.Usuario;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class AutenticacionGateway extends RcbikeRestGateway {

    public static final GenericType<List<Usuario>> TYPE_LIST_USUARIO = new GenericType<List<Usuario>>() {
    };

    @Inject
    @Snoop(serviceName = OperacionesUsuarios.EP_USUARIOS)
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }

    public Response autenticar(String authContent) {
        return webTarget().path(OperacionesAutenticacion.EP_AUTENTICACION).request().post(Entity.json(authContent));
    }

}
