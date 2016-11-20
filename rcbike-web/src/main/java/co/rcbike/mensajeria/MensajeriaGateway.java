package co.rcbike.mensajeria;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager.ModMensajeria;
import co.rcbike.mensajeria.model.Mensaje;
import co.rcbike.mensajeria.model.OperacionesMensajeria;
import co.rcbike.web.util.UtilRest;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@SuppressWarnings("serial")
@RequestScoped
public class MensajeriaGateway implements Serializable {

    @Inject
    @Snoop(serviceName = OperacionesMensajeria.EP_MENSAJERIA)
    private SnoopServiceClient service;

    private GenericType<List<Mensaje>> gTListMensaje = new GenericType<List<Mensaje>>() {
    };

    public List<String> listConversaciones() {
        return service.getServiceRoot().path(OperacionesMensajeria.EP_MENSAJERIA)
                .path(OperacionesMensajeria.OP_CONVERSACIONES).path(AutenticacionManager.emailAutenticado()).request()
                .get(UtilRest.TYPE_LIST_STRING);
    }

    public List<Mensaje> listMensajes(String participante1, String participante2) {
        return service.getServiceRoot().path(OperacionesMensajeria.EP_MENSAJERIA).path(OperacionesMensajeria.OP_MENSAJE)
                .path(participante1).path(participante2).request().get(gTListMensaje);
    }

    public void crearMensaje(Mensaje nuevoMensaje) {
        service.getServiceRoot().path(ModMensajeria.ENDPNT_MENSAJERIA).request().post(Entity.json(nuevoMensaje));
    }
}
