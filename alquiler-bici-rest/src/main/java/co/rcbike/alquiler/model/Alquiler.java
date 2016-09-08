package co.rcbike.alquiler.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@NamedQueries({@NamedQuery(name = "listByNombre", query = "SELECT e FROM Alquiler e ORDER BY e.nombre ASC")})
public class Alquiler implements Serializable {

    public static final String SQ_listByNombre = "listByNombre";
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String nombre;

}
