package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Pieza")
@Table(name = "Pieza")
@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "findAllPiezas", query = "SELECT w FROM Pieza w"),
	@NamedQuery(name = "findByIdPieza", query = "SELECT w FROM Pieza w WHERE w.id = :id"),
	@NamedQuery(name = "findByTipo", query = "SELECT w FROM Pieza w WHERE w.tipo = :tipo")})
public class PiezaJpa extends Pieza implements Serializable {

	private static final long serialVersionUID = -1043399871522964339L;
	public static final String SQ_findAllPiezas = "findAllPiezas";
	public static final String SQ_findByTipo = "findByTipo";
	public static final String SQ_findByIdPieza = "findByIdPieza";
	public static final String SQ_PARAM_ID = "id";
	public static final String SQ_PARAM_TIPO = "tipo";
	
}
