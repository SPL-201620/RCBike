package co.rcbike.desplazamientos.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ParticipanteWeb extends Participante implements Serializable {

	private static final long serialVersionUID = 8472434553834980873L;
	
	private Long idRuta;

	public Long getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(Long idRuta) {
		this.idRuta = idRuta;
	}
}
