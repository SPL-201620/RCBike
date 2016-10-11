package co.rcbike.mensajeria.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.rcbike.mensajeria.model.Conversacion;
import co.rcbike.mensajeria.model.Mensaje;

@Stateless
public class MensajeriaService {

	@Inject
	private EntityManager em;
	private List<Mensaje> mensajes;
	private Mensaje nuevoMensaje;
	private String email;
	private Conversacion nuevaConversacion;

	public List<Mensaje> findConvesacionByEmRes(String emailEmisor,
			String emailReceptor) {
		TypedQuery<Conversacion> q = em
						.createNamedQuery(Conversacion.SQ_LISTBYEMAILS, Conversacion.class);		
		q.setParameter("emailEmisor", emailEmisor);
		q.setParameter("emailReceptor", emailReceptor);
		try {
			Conversacion c =q.getSingleResult();
			return mensajes;
		} catch (Exception e) {
			return Collections.EMPTY_LIST;
		}

	}

	public Conversacion createConversacion(Mensaje mensaje, String emailReceptor) {
		nuevaConversacion.setEmailReceptor(emailReceptor);
		nuevaConversacion.setEmailEmisor(mensaje.getEmailEmisorMensaje());
		em.persist(nuevaConversacion);
		em.flush();
		return nuevaConversacion;
	}

	public Mensaje createMensaje(Mensaje mensaje, String emailReceptor) {
		nuevoMensaje = mensaje;
		email = emailReceptor;
		if (mensaje.getConversacion() != null) {
			em.persist(mensaje);
			em.flush();
		} else {
			createConversacion(nuevoMensaje, email);
		}
		return mensaje;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje getNuevoMensaje() {
		return nuevoMensaje;
	}

	public void setNuevoMensaje(Mensaje nuevoMensaje) {
		this.nuevoMensaje = nuevoMensaje;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Conversacion getNuevaConversacion() {
		return nuevaConversacion;
	}

	public void setNuevaConversacion(Conversacion nuevaConversacion) {
		this.nuevaConversacion = nuevaConversacion;
	}
}
