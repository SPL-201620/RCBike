package co.rcbike.mensajeria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({@NamedQuery(name = "listByEmail", query = "SELECT e FROM Conversacion e WHERE (e.emailEmisor = :emailEmisor and e.emailReceptor = :emailReceptor) or (e.emailEmisor =  :emailReceptor and e.emailReceptor = :emailEmisor)" )})
public class Conversacion implements Serializable {

    public static final String SQ_LISTBYEMAILS = "listByEmail";
       
  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @NotEmpty
  @Email
  private String emailEmisor;

  @NotNull
  @NotEmpty
  @Email
  private String emailReceptor;

  @OneToMany(mappedBy = "conversacion", fetch = FetchType.EAGER)
  private List<Mensaje> mensajes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public List<Mensaje> getMensajes() {
    return mensajes;
  }

  public void setMensajes(List<Mensaje> mensajes) {
    this.mensajes = mensajes;
  }

}
