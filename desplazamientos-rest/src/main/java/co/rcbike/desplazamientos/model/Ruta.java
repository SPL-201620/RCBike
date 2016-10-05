package co.rcbike.desplazamientos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
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
	@NamedQuery(name = "findByTipo", query = "SELECT e FROM Ruta e WHERE e.tipo = :tipo") })

public class Ruta implements Serializable {

	public static final String SQ_findByTipoAndCreador = "findByTipoAndCreador";
	public static final String SQ_findByTipo = "findByTipo";
	public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
	public static final String SQ_PARAM_TIPO = "tipo";

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
	private BigDecimal latitudInicio;

	@NotNull
	private BigDecimal longitudInicio;

	@NotNull
	private BigDecimal latitudFinal;

	@NotNull
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
	private boolean frecuente;

	private boolean lunes;

	private boolean martes;

	private boolean miercoles;

	private boolean jueves;

	private boolean viernes;

	private boolean sabado;

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
