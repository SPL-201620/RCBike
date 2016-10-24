package co.rcbike.mensajeria.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.mensajeria.model.Mensaje;

@Stateless
public class MensajeriaService {

    @Inject
    private EntityManager em;

    public List<Mensaje> findMensajes(String emailEmisor, String emailReceptor) {
        TypedQuery<Mensaje> q = em.createNamedQuery(Mensaje.SQ_LISTBYPARTICIPANTES, Mensaje.class);
        q.setParameter("emailEmisor", emailEmisor);
        q.setParameter("emailReceptor", emailReceptor);
        List<Mensaje> c = q.getResultList();
        return c;

    }

    public Mensaje createMensaje(Mensaje mensaje) {
        mensaje.setParticipantes("|" + mensaje.getEmailEmisor() + "|" + mensaje.getEmailReceptor() + "|");
        em.persist(mensaje);
        return mensaje;
    }

    public List<String> listConversaciones(String emailParticipante) {
        TypedQuery<String> q = em.createNamedQuery(Mensaje.SQ_LIST_CONVERSACIONES, String.class);
        q.setParameter(Mensaje.SQ_PARAM_PARTICIPANTE_LIKE, "%|" + emailParticipante + "|%");
        List<String> resultList = q.getResultList();
        Set<String> procesados = new HashSet<>(resultList.size());

        resultList.stream().forEach(participantes -> {
            procesados.add(participantes.replace(emailParticipante, "").replace("|", ""));
        });
        return new ArrayList<String>(procesados);
    }

}
