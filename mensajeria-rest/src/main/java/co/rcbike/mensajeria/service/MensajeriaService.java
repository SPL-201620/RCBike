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
    private List<Mensaje> mensajes;

    public List<Mensaje> findConvesacionByEmRes(String emailEmisor, String emailReceptor) {
        javax.persistence.Query q = em.createNamedQuery(Conversacion.SQ_LISTBYEMAILS);
        q.setParameter("emailEmisor", emailEmisor);
        q.setParameter("emailReceptor", emailReceptor);
        Conversacion c = (Conversacion) q.getSingleResult();
        if (c != null){
           mensajes=c.getMensajes();
        }
        return mensajes;
    }

    public Conversacion create(Conversacion record) {
        em.persist(record);
        em.flush();
        return record;
    }
    
    public Mensaje create(Mensaje record) {
        em.persist(record);
        em.flush();
        return record;
    }
}
