package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Color")
@Table(name = "Color")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findByIdColor", query = "SELECT w FROM Color w WHERE w.id = :id"),
	@NamedQuery(name = "findAllColores", query = "SELECT w FROM Color w") })
public class ColorJpa extends Color implements Serializable {

	private static final long serialVersionUID = 1267703991407172600L;
	public static final String SQ_findAllColores = "findAllColores";
	public static final String SQ_findByIdColor = "findByIdColor";
	public static final String SQ_PARAM_ID = "id";
	public static final String SQ_PARAM_ID_RUTA = "idConfiguracion";
	
}
