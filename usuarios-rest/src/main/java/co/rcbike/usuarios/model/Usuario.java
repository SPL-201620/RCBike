package co.rcbike.usuarios.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@JsonIgnoreProperties({"amigos"})
@NamedQueries({@NamedQuery(name = "findByEmail", query = "SELECT e FROM Usuario e WHERE e.email = :email"),
        @NamedQuery(name = "listByNombre", query = "SELECT e FROM Usuario e ORDER BY e.nombres ASC"),
        @NamedQuery(name = "listNoAmigos", query = "SELECT e FROM Usuario e WHERE e.email NOT IN :excluidos ORDER BY e.nombres ASC"),
        @NamedQuery(name = "listNoAmigosFiltro", query = "SELECT e FROM Usuario e WHERE "
                + " lower(e.email) LIKE :filtro OR lower(e.nombres) LIKE :filtro OR lower(e.apellidos) LIKE :filtro"
                + " AND e.email NOT IN :excluidos ORDER BY e.nombres ASC")})
public class Usuario implements Serializable {

    public static final String SQ_findByEmail = "findByEmail";
    public static final String SQ_listByNombre = "listByNombre";
    public static final String SQ_PARAM_EMAIL = "email";
    public static final String SQ_listNoAmigos = "listNoAmigos";
    public static final String SQ_PARAM_EXCLUIDOS = "excluidos";
    public static final String SQ_listNoAmigosFiltro = "listNoAmigosFiltro";
    public static final String SQ_PARAM_FILTRO = "filtro";

    @Id
    @GeneratedValue
    private Long id;
    /**
     * Identificador de negocio de un usuario
     */
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    private String nombres;
    @NotNull
    private String apellidos;
    /**
     * Representacion Base 64 de la imagen
     */
    @Column(columnDefinition = "CLOB")
    @Lob
    private String foto;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "AMIGOS", //
            joinColumns = @JoinColumn(name = "PRINCIPAL", referencedColumnName = "ID"), //
            inverseJoinColumns = @JoinColumn(name = "AMIGO", referencedColumnName = "ID"))
    private List<Usuario> amigos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String nombreCompleto() {
        return this.nombres + " " + this.apellidos;
    }

}
