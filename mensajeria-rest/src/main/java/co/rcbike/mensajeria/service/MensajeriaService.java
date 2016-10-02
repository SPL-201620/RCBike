package co.rcbike.mensajeria.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import co.rcbike.mensajeria.model.Conversacion;
import co.rcbike.mensajeria.model.Mensaje;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;

@Stateless
public class MensajeriaService {

    @Inject
    private Logger LOG;

    @Inject
    private EntityManager em;

    public List<Conversacion> findConvesacionByEmRes() {
        TypedQuery<Conversacion> list;
        {
            list = em.createNamedQuery(Conversacion.SQ_LISTBYEMAILS, Conversacion.class);
            return list.getResultList();
        }
    }

    public Conversacion create(Conversacion record) {
            em.persist(record);
            em.flush();
            return record;
        } 
    
    public List<Mensaje> findMensajeByConversacion(Conversacion conver) {
        List<Mensaje> list;
        {
           list = em.createNamedQuery(Mensaje.SQ_LISTBYCONVERSACION, conver);            
            return list;
        }
    }

    public Mensaje create(Mensaje record) {
            em.persist(record);
            em.flush();
            return record;
        } 
    
    }