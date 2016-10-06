package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "findByTipoAndCreador", query = "SELECT e FROM Ruta e WHERE e.emailCreador = :emailCreador and e.tipo = :tipo"), 
	@NamedQuery(name = "findByTipo", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo"),
	@NamedQuery(name = "findByTipoAndFrecuente", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.frecuente = :frecuente"),
	@NamedQuery(name = "findByTipoAndFechaAndFrecuente", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.fecha >= :fecha and e.frecuente = :frecuente"),
	@NamedQuery(name = "findByTipoAndFrecuenteAndPunto", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.frecuente = :frecuente "
			+ "and e.latitudInicio >= :latitudInicio and e.latitudInicio <= :latitudFinal "
			+ "and e.longitudInicio >= :longitudInicio and e.longitudInicio <= :longitudFinal"),
	@NamedQuery(name = "findByTipoAndFechaAndFrecuenteAndPunto", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo and e.fecha >= :fecha and e.frecuente = :frecuente "
			+ "and e.latitudInicio >= :latitudInicio and e.latitudInicio <= :latitudFinal "
			+ "and e.longitudInicio >= :longitudInicio and e.longitudInicio <= :longitudFinal")})

public class Ruta implements Serializable {

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

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	@Email
	private String emailCreador;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	@NotNull
	@Column(precision = 20, scale = 15)
	private BigDecimal latitudInicio;

	@NotNull
	@Column(precision = 20, scale = 15)
	private BigDecimal longitudInicio;

	@NotNull
	@Column(precision = 20, scale = 15)
	private BigDecimal latitudFinal;

	@NotNull
	@Column(precision = 20, scale = 15)
	private BigDecimal longitudFinal;

	@NotNull
	private BigDecimal distancia;

	@NotNull
	private int tiempoEstimado;

	@NotNull
	private Integer calorias;

	@NotNull
	@NotEmpty
	private String clima;

	/**
	 * Grupal: si no es frecuente representa la fecha y hora del recorrido,
	 * si es frecuente representa la hora del dia del recorrido
	 * <br>
	 * Individial: Fecha en la que se hizo el recorrido
	 */
	/*Solo para individuales*/
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	/*Solo para grupales*/
	@Convert(converter=BooleanSNConverter.class)
	private boolean frecuente;

	@Convert(converter=BooleanSNConverter.class)
	private boolean lunes;

	@Convert(converter=BooleanSNConverter.class)
	private boolean martes;

	@Convert(converter=BooleanSNConverter.class)
	private boolean miercoles;

	@Convert(converter=BooleanSNConverter.class)
	private boolean jueves;

	@Convert(converter=BooleanSNConverter.class)
	private boolean viernes;

	@Convert(converter=BooleanSNConverter.class)
	private boolean sabado;

	@Convert(converter=BooleanSNConverter.class)
	private boolean domingo;

    @OneToMany(mappedBy="ruta",fetch=FetchType.EAGER)
	private Set<Waypoint> waypoints;

    @OneToMany(mappedBy="ruta",fetch=FetchType.EAGER)
	private Set<Participante> participantes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailCreador() {
		return emailCreador;
	}

	public void setEmailCreador(String emailCreador) {
		this.emailCreador = emailCreador;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getLatitudInicio() {
		return latitudInicio;
	}

	public void setLatitudInicio(BigDecimal latitudInicio) {
		this.latitudInicio = latitudInicio;
	}

	public BigDecimal getLongitudInicio() {
		return longitudInicio;
	}

	public void setLongitudInicio(BigDecimal longitudInicio) {
		this.longitudInicio = longitudInicio;
	}

	public BigDecimal getLatitudFinal() {
		return latitudFinal;
	}

	public void setLatitudFinal(BigDecimal latitudFinal) {
		this.latitudFinal = latitudFinal;
	}

	public BigDecimal getLongitudFinal() {
		return longitudFinal;
	}

	public void setLongitudFinal(BigDecimal longitudFinal) {
		this.longitudFinal = longitudFinal;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public int getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(int tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public Integer getCalorias() {
		return calorias;
	}

	public void setCalorias(Integer calorias) {
		this.calorias = calorias;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Set<Waypoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(Set<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}

	public Set<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Set<Participante> participantes) {
		this.participantes = participantes;
	}

    public boolean isFrecuente() {
        return frecuente;
    }

    public void setFrecuente(boolean frecuente) {
        this.frecuente = frecuente;
    }

    public boolean isLunes() {
        return lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    public boolean isMartes() {
        return martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    public boolean isMiercoles() {
        return miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    public boolean isJueves() {
        return jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    public boolean isViernes() {
        return viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }
	
	
}
