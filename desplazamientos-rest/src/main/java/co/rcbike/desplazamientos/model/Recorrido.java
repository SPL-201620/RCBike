package co.rcbike.desplazamientos.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "findByCreador", query = "SELECT e FROM Recorrido e WHERE e.emailCreador = :emailCreador")})
public class Recorrido implements Serializable {

    public static final String SQ_findByCreador = "findByCreador";
    public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Email
    private String emailCreador;

    @Enumerated(EnumType.STRING)
    private TipoRecorrido tipo;

    @Enumerated(EnumType.STRING)
    private EstadoRecorrido estado;
}