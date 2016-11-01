package co.rcbike.autenticacion.rest;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import co.rcbike.autenticacion.model.OperacionesAutenticacion;
import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.autenticacion.service.AutenticacionService;

@Path(OperacionesAutenticacion.EP_AUTENTICACION)
@RequestScoped
public class AutenticacionEndpoint {

    @Inject
    private AutenticacionService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticacion(Map<String, Object> valoresAutenticacion) {
        ResponseBuilder resp;
        ResultadoAutenticacion autenticado = service.autenticar(valoresAutenticacion);

        switch (autenticado.getEstado()) {
            case CLAVE_ERRONEA :
                resp = Response.status(Response.Status.UNAUTHORIZED);
                break;
            case NO_EXISTE_USUARIO :
                resp = Response.status(Response.Status.NOT_FOUND);
                break;
            case OK :
                resp = Response.ok();
                break;
            default :
                resp = Response.serverError();
                break;
        }
        return resp.entity(Entity.json(autenticado)).build();
    }

}
