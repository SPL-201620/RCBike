package co.rcbike.mensajeria.rest;

import co.rcbike.mensajeria.exception.ServiceException;
import co.rcbike.mensajeria.model.Conversacion;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.mensajeria.service.MensajeriaService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
  public List<Conversacion> findAll() throws ServiceException {
    LOG.trace("method: findAll()");
    return service.findAll();
  }

  @GET
  @Path("{id}")
  @Produces({MediaType.APPLICATION_JSON})
  public Conversacion findById(@PathParam("id") Long id) throws ServiceException {
    LOG.debug("method: findById(id)");
    return service.findById(id);
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  public Conversacion create(Conversacion record) throws ServiceException {
    LOG.debug("method: create(): " + record.toString());
    return service.create(record);
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public void update(Conversacion record) throws ServiceException {
    LOG.debug("method: update(record)");
    service.update(record);
  }

  @DELETE
  @Path("{id}")
  public void delete(@PathParam("id") Long id) throws ServiceException {
    LOG.debug("method: delete(id)");
    service.delete(id);
  }
}
