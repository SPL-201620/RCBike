package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
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
    @NamedQuery(name = "findByCreadorAndFecha", query = "SELECT r FROM Ruta r WHERE r.emailCreador = :emailCreador and r.fecha >= :fechaInicio and r.fecha <= :fechaFinal"),
        @NamedQuery(name = "findByTipoAndCreador", query = "SELECT r FROM Ruta r WHERE r.emailCreador = :emailCreador AND r.tipo = :tipo"),
        @NamedQuery(name = "findByIdRuta", query = "SELECT r FROM Ruta r WHERE r.id = :id"),
        @NamedQuery(name = "findByTipo", query = "SELECT r FROM Ruta r WHERE r.tipo = :tipo"),
        @NamedQuery(name = "findByTipoAndFrecuente", query = "SELECT r FROM Ruta r WHERE r.tipo = :tipo and r.frecuente = :frecuente"),
        @NamedQuery(name = "findByTipoAndFechaAndFrecuente", query = "SELECT r FROM Ruta r WHERE r.tipo = :tipo and r.fecha >= :fecha and r.frecuente = :frecuente"),
        @NamedQuery(name = "findByTipoAndFrecuenteAndPunto", query = "SELECT r FROM Ruta r WHERE r.tipo = :tipo and r.frecuente = :frecuente "
                + "and r.latitudInicio >= :latitudInicio and r.latitudInicio <= :latitudFinal "
                + "and r.longitudInicio >= :longitudInicio and r.longitudInicio <= :longitudFinal"),
        @NamedQuery(name = "findByTipoAndFechaAndFrecuenteAndPunto", query = "SELECT r FROM Ruta r WHERE r.tipo = :tipo and r.fecha >= :fecha and r.frecuente = :frecuente "
                + "and r.latitudInicio >= :latitudInicio and r.latitudInicio <= :latitudFinal "
                + "and r.longitudInicio >= :longitudInicio and r.longitudInicio <= :longitudFinal")})

public class RutaJpa extends Ruta implements Serializable {

	private static final long serialVersionUID = 6380919063743408740L;
	public static final String SQ_findByTipoAndCreador = "findByTipoAndCreador";
    public static final String SQ_findByTipo = "findByTipo";
    public static final String SQ_findByTipoAndFrecuente = "findByTipoAndFrecuente";
    public static final String SQ_findByCreadorAndFecha = "findByCreadorAndFecha";
    public static final String SQ_findByTipoAndFechaAndFrecuente = "findByTipoAndFechaAndFrecuente";
    public static final String SQ_findByTipoAndFrecuenteAndPunto = "findByTipoAndFrecuenteAndPunto";
    public static final String SQ_findByTipoAndFechaAndFrecuenteAndPunto = "findByTipoAndFechaAndFrecuenteAndPunto";
	public static final String SQ_findByIdRuta = "findByIdRuta";
    public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
    public static final String SQ_PARAM_TIPO = "tipo";
    public static final String SQ_PARAM_FECHA = "fecha";
    public static final String SQ_PARAM_FECHA_INICIO = "fechaInicio";
    public static final String SQ_PARAM_FECHA_FINAL = "fechaFinal";
    public static final String SQ_PARAM_FRECUENTE = "frecuente";
    public static final String SQ_PARAM_LATITUD_INICIO = "latitudInicio";
    public static final String SQ_PARAM_LATITUD_FINAL = "latitudFinal";
    public static final String SQ_PARAM_LONGITUD_INICIO = "longitudInicio";
    public static final String SQ_PARAM_LONGITUD_FINAL = "longitudFinal";
    public static final String SQ_PARAM_ID = "id";
    
    @OneToMany(mappedBy = "ruta", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<WaypointJpa> waypoints;
    
    @OneToMany(mappedBy = "ruta", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
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
