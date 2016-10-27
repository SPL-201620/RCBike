package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Ruta")
@Table(name = "Ruta")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "findByTipoAndCreador", query = "SELECT e FROM Ruta e WHERE e.emailCreador = :emailCreador AND e.tipo = :tipo"),
        @NamedQuery(name = "findByTipo", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo"),
        @NamedQuery(name = "findByTipoAndFrecuente", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.frecuente = :frecuente"),
        @NamedQuery(name = "findByTipoAndFechaAndFrecuente", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.fecha >= :fecha and e.frecuente = :frecuente"),
        @NamedQuery(name = "findByTipoAndFrecuenteAndPunto", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.frecuente = :frecuente "
                + "and e.latitudInicio >= :latitudInicio and e.latitudInicio <= :latitudFinal "
                + "and e.longitudInicio >= :longitudInicio and e.longitudInicio <= :longitudFinal"),
        @NamedQuery(name = "findByTipoAndFechaAndFrecuenteAndPunto", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.fecha >= :fecha and e.frecuente = :frecuente "
                + "and e.latitudInicio >= :latitudInicio and e.latitudInicio <= :latitudFinal "
                + "and e.longitudInicio >= :longitudInicio and e.longitudInicio <= :longitudFinal")})

public class RutaJpa extends Ruta implements Serializable {

	private static final long serialVersionUID = 6380919063743408740L;
	public static final String SQ_findByTipoAndCreador = "findByTipoAndCreador";
    public static final String SQ_findByTipo = "findByTipo";
    public static final String SQ_findByTipoAndFrecuente = "findByTipoAndFrecuente";
    public static final String SQ_findByTipoAndFechaAndFrecuente = "findByTipoAndFechaAndFrecuente";
    public static final String SQ_findByTipoAndFrecuenteAndPunto = "findByTipoAndFrecuenteAndPunto";
    public static final String SQ_findByTipoAndFechaAndFrecuenteAndPunto = "findByTipoAndFechaAndFrecuenteAndPunto";
    public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
    public static final String SQ_PARAM_TIPO = "tipo";
    public static final String SQ_PARAM_FECHA = "fecha";
    public static final String SQ_PARAM_FRECUENTE = "frecuente";
    public static final String SQ_PARAM_LATITUD_INICIO = "latitudInicio";
    public static final String SQ_PARAM_LATITUD_FINAL = "latitudFinal";
    public static final String SQ_PARAM_LONGITUD_INICIO = "longitudInicio";
    public static final String SQ_PARAM_LONGITUD_FINAL = "longitudFinal";
    
    @OneToMany(mappedBy = "ruta", fetch = FetchType.EAGER)
    private Set<WaypointJpa> waypoints;
    
    @OneToMany(mappedBy = "ruta", fetch = FetchType.EAGER)
    private Set<ParticipanteJpa> participantes;

	public Set<WaypointJpa> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Set<WaypointJpa> waypoints) {
		this.waypoints = waypoints;
	}

	public Set<ParticipanteJpa> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<ParticipanteJpa> participantes) {
		this.participantes = participantes;
	}

}
