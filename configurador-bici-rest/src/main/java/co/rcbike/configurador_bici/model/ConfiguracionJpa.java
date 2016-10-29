package co.rcbike.configurador_bici.model;

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

@Entity(name = "Configuracion")
@Table(name = "Configuracion")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "findByCreador", query = "SELECT e FROM Configuracion e WHERE e.emailCreador = :emailCreador"),
        @NamedQuery(name = "findByIdConfiguracion", query = "SELECT e FROM Configuracion e WHERE e.id = :id")})
public class ConfiguracionJpa extends Configuracion implements Serializable {

	private static final long serialVersionUID = 8994402921863756631L;
	public static final String SQ_findByCreador = "findByCreador";
	public static final String SQ_findByIdConfiguracion = "findByIdConfiguracion";
    public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
    public static final String SQ_PARAM_ID = "id";
    
    @OneToMany(mappedBy = "configuracion", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<PiezaConfiguracionJpa> piezasConfiguracion;

	public Set<PiezaConfiguracionJpa> getPiezasConfiguracion() {
		return piezasConfiguracion;
	}

	public void setPiezasConfiguracion(Set<PiezaConfiguracionJpa> piezasConfiguracion) {
		this.piezasConfiguracion = piezasConfiguracion;
	}
    
}
