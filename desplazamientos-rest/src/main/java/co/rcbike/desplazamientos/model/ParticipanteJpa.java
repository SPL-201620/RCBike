package co.rcbike.desplazamientos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Participante")
@Table(name = "Participante")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findByIdParticipante", query = "SELECT p FROM Participante p WHERE p.id = :id"),
	@NamedQuery(name = "findAllParticipantes", query = "SELECT p FROM Participante p"),
	@NamedQuery(name = "findParticipantesByIdRuta", query = "SELECT p FROM Participante p WHERE p.ruta.id = :idRuta") })
public class ParticipanteJpa extends Participante implements Serializable {

	private static final long serialVersionUID = 3642158591907889302L;
	public static final String SQ_findByIdParticipante = "findByIdParticipante";
	public static final String SQ_findAllParticipantes = "findAllParticipantes";
	public static final String SQ_findParticipantesByIdRuta = "findParticipantesByIdRuta";
	public static final String SQ_PARAM_ID = "id";
	public static final String SQ_PARAM_ID_RUTA = "idRuta";

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRuta")
	private RutaJpa ruta;

	public RutaJpa getRuta() {
		return ruta;
	}

	public void setRuta(RutaJpa ruta) {
		this.ruta = ruta;
	}
}
