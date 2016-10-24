package co.rcbike.mensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = Mensaje.SQ_LISTBYPARTICIPANTES, query = "SELECT e FROM Mensaje e WHERE (e.emailEmisor = :emailEmisor and e.emailReceptor = :emailReceptor) or (e.emailEmisor =  :emailReceptor and e.emailReceptor = :emailEmisor)"),
        @NamedQuery(name = Mensaje.SQ_LIST_CONVERSACIONES, query = "SELECT DISTINCT(e.participantes) FROM Mensaje e WHERE e.participantes LIKE :participante")})

public class Mensaje implements Serializable {

    public static final String SQ_LISTBYPARTICIPANTES = "listByParticipantes";

    public static final String SQ_LIST_CONVERSACIONES = "listConversaciones";
    public static final String SQ_PARAM_PARTICIPANTE_LIKE = "participante";

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @NotNull
    @NotEmpty
    @Email
    private String emailEmisor;

    @NotNull
    @NotEmpty
    @Email
    private String emailReceptor;

    @NotNull
    @NotEmpty
    private String participantes;

    private String contenido;

    public Mensaje() {
        super();
    }

    public Mensaje(String emailEmisor, String emailReceptor, String contenido, Date fechaHora) {
        super();
        this.emailEmisor = emailEmisor;
        this.emailReceptor = emailReceptor;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEmailEmisor() {
        return emailEmisor;
    }

    public void setEmailEmisor(String emailEmisor) {
        this.emailEmisor = emailEmisor;
    }

    public String getEmailReceptor() {
        return emailReceptor;
    }

    public void setEmailReceptor(String emailReceptor) {
        this.emailReceptor = emailReceptor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }

}
