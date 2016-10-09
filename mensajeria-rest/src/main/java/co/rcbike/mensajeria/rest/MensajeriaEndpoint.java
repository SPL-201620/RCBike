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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
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
  public Conversacion findConvesacionByEmRes(@PathParam("emisor") String emisor, @PathParam("receptor") String receptor){
  return service.findConvesacionByEmRes(emisor, receptor);
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Conversacion create(Conversacion record){
    return service.create(record);
  }
  
  
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Mensaje create(Mensaje record){
    return service.create(record);
  }

}
