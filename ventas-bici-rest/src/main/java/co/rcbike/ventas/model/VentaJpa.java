package co.rcbike.ventas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Venta")
@Table(name = "Venta")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "findAllVentas", query = "SELECT v FROM Venta v"),
        @NamedQuery(name = "findByCreador", query = "SELECT v FROM Venta v WHERE v.emailCreador = :emailCreador"),
        @NamedQuery(name = "findByIdVenta", query = "SELECT v FROM Venta v WHERE v.id = :id")})
public class VentaJpa extends Venta implements Serializable {

	private static final long serialVersionUID = -4346444215603582675L;
	public static final String SQ_findAllVentas = "findAllVentas";
	public static final String SQ_findByCreador = "findByCreador";
	public static final String SQ_findByIdVenta = "findByIdVenta";
    public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
    public static final String SQ_PARAM_ID = "id";
    
}
