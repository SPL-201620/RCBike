package co.rcbike.mensajeria.rest;

import co.rcbike.mensajeria.model.Conversacion;
import co.rcbike.mensajeria.model.Mensaje;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.rcbike.mensajeria.service.MensajeriaService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import org.slf4j.Logger;

@Path("/mensajes")
@RequestScoped
public class MensajeriaEndpoint {

  @Inject
  private Logger LOG;

  @Inject
  private MensajeriaService service;

  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<Conversacion> findAll(){
    return service.findConvesacionByEmRes();
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Conversacion create(Conversacion record){
    return service.create(record);
  }
  
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  public List<Mensaje> findMensajeAll(){
      Conversacion conver = new Conversacion();
    return service.findMensajeByConversacion(conver);
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Mensaje create(Mensaje record){
    return service.create(record);
  }

}
