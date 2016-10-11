package co.rcbike.mensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "listByConversacion", query = "SELECT e FROM Mensaje e WHERE e.conversacion = :conversacion")})
public class Mensaje implements Serializable {

    public static final String SQ_LISTBYCONVERSACION = "listByConversacion";

    @Id
    @GeneratedValue
    private Long id;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @NotNull
    @NotEmpty
    @Email
    private String emailEmisorMensaje;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_conversacion", referencedColumnName = "id")
    private Conversacion conversacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Conversacion getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversacion conversacion) {
        this.conversacion = conversacion;
    }

    public String getEmailEmisorMensaje() {
        return emailEmisorMensaje;
    }

    public void setEmailEmisorMensaje(String emailEmisorMensaje) {
        this.emailEmisorMensaje = emailEmisorMensaje;
    }

}
