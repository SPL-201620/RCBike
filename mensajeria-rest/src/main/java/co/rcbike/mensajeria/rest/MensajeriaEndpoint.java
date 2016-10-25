package co.rcbike.mensajeria.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.mensajeria.model.Mensaje;
import co.rcbike.mensajeria.model.OperacionesMensajeria;
import co.rcbike.mensajeria.service.MensajeriaService;

@Path(OperacionesMensajeria.EP_MENSAJERIA)
@RequestScoped
public class MensajeriaEndpoint {

    @Inject
    private MensajeriaService service;

    @GET
    @Path(OperacionesMensajeria.OP_CONVERSACIONES + "/" + OperacionesMensajeria.PATH_PRM_EMAIL)
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> listConversaciones(@PathParam("email") String emailParticipante) {
        return service.listConversaciones(emailParticipante);
    }

    @GET
    @Path(OperacionesMensajeria.OP_MENSAJE + "/{emailEmisor: .+@.+}/{emailReceptor: .+@.+}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Mensaje> findConvesacionByEmRes(@PathParam("emailEmisor") String emailEmisor,
            @PathParam("emailReceptor") String emailReceptor) {
        return service.findMensajes(emailEmisor, emailReceptor);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void crearMensaje(Mensaje mensaje) {
        service.createMensaje(mensaje);
    }

}
