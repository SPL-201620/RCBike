package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity(name = "PiezaConfiguracion")
@Table(name = "PiezaConfiguracion")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findByIdPiezaConfiguracion", query = "SELECT w FROM PiezaConfiguracion w WHERE w.id = :id"),
	@NamedQuery(name = "findAllPiezasConfiguracion", query = "SELECT w FROM PiezaConfiguracion w"),
	@NamedQuery(name = "findPiezasConfiguracionByIdConfiguracion", query = "SELECT w FROM PiezaConfiguracion w WHERE w.configuracion.id = :idConfiguracion") })
public class PiezaConfiguracionJpa extends Pieza implements Serializable {

	public static final String SQ_findAllPiezasConfiguracion = "findAllPiezasConfiguracion";
	public static final String SQ_findPiezasConfiguracionByIdConfiguracion = "findPiezasConfiguracionByIdConfiguracion";
	public static final String SQ_findByIdPiezaConfiguracion = "findByIdPiezaConfiguracion";
	public static final String SQ_PARAM_ID = "id";
	public static final String SQ_PARAM_ID_RUTA = "idConfiguracion";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idConfiguracion")
	private ConfiguracionJpa configuracion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPieza")
	private PiezaJpa pieza;

	public ConfiguracionJpa getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(ConfiguracionJpa configuracion) {
		this.configuracion = configuracion;
	}

	public PiezaJpa getPieza() {
		return pieza;
	}

	public void setPieza(PiezaJpa pieza) {
		this.pieza = pieza;
	}
	
}
