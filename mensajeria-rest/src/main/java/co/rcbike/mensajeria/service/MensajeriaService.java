package co.rcbike.mensajeria.service;

import co.rcbike.mensajeria.exception.ServiceException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import co.rcbike.mensajeria.model.Conversacion;
import co.rcbike.mensajeria.sdo.GenericSDOQualifier;
import co.rcbike.mensajeria.sdo.SDO;
import org.slf4j.Logger;

@Stateless
public class MensajeriaService {

  @Inject
  private Logger LOG;

  @Inject
  private EntityManager em;

  @Inject
  @GenericSDOQualifier
  private SDO sdo;

  public List<Conversacion> findAll() throws ServiceException {
    LOG.trace("method: findAll()");
    List<Conversacion> list;
    try {
      list = sdo.getResultList(em, Conversacion.class);
      em.clear();
    } catch (Exception ex) {
      LOG.error("Error en <<findAll>> ->> mensaje ->> {}", ex.getMessage());
      throw new ServiceException("No es posible consultar la información.");
    }
    return list;
  }

  public Conversacion findById(Long id) throws ServiceException {
    LOG.debug("method: findById(id)");
    try {
      Conversacion i = (Conversacion) sdo.find(em, id, Conversacion.class);
      em.clear();
      return i;
    } catch (Exception ex) {
      LOG.error("Error en <<findById>> ->> mensaje ->> {}", ex.getMessage());
      throw new ServiceException("No es posible consultar la información.");
    }
  }

  public Conversacion create(Conversacion record) throws ServiceException {
    LOG.debug("method: create(): " + record.toString());
    try {
      sdo.persist(em, record);
      em.flush();
      return record;
    } catch (Exception ex) {
      LOG.error("Error en <<create>> ->> mensaje ->> {}", ex.getMessage());
      throw new ServiceException("No es posible guardar la información.");
    }
  }

  public void update(Conversacion record) throws ServiceException {
    LOG.debug("method: edit(record)");
    try {
      sdo.merge(em, record);
      em.flush();
    } catch (Exception ex) {
      LOG.error("Error en <<record>> ->> mensaje ->> {}", ex.getMessage());
      throw new ServiceException("No es posible actualizar la información.");
    }
  }

  public void delete(Long id) throws ServiceException {
    LOG.debug("method: delete(id)");
    try {
      sdo.remove(em, id, Conversacion.class);
      em.flush();
    } catch (Exception ex) {
      LOG.error("Error en <<delete>> ->> mensaje ->> {}", ex.getMessage());
      throw new ServiceException("No es posible eliminar la información.");
    }
  }
}
