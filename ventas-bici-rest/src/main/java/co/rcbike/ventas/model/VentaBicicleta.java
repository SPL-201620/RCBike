package co.rcbike.ventas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	@NamedQuery(name = "findAllVentas", query = "SELECT v FROM VentaBicicleta v"),
	@NamedQuery(name = "findById", query = "SELECT v FROM VentaBicicleta v WHERE v.id = :idVenta"),
		@NamedQuery(name = "findByCreador", query = "SELECT v FROM VentaBicicleta v WHERE v.emailCreador = :emailCreador") })
public class VentaBicicleta implements Serializable {

	public static final String SQ_findAll = "findAllVentas";
	public static final String SQ_findById = "findById";
	public static final String SQ_findByCreador = "findByCreador";
	public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
	public static final String SQ_PARAM_ID_VENTA = "idVenta";

	/* No es necesario llenar */
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@NotEmpty
	@Email
	private String emailCreador;

	@NotNull
	private String valor;

	@Convert(converter = BooleanSNConverter.class)
	private boolean vendida;

	@NotNull
	private String marca;

	@NotNull
	private String ciudadVenta;

	@NotNull
	private String estado;

	@NotNull
	private String accesorios;

	@NotNull
	private String observaciones;

	@Column(columnDefinition = "CLOB")
	@Lob
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isVendida() {
		return vendida;
	}

	public void setVendida(boolean vendida) {
		this.vendida = vendida;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCiudadVenta() {
		return ciudadVenta;
	}

	public void setCiudadVenta(String ciudadVenta) {
		this.ciudadVenta = ciudadVenta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getAccesorios() {
		return accesorios;
	}

	public void setAccesorios(String accesorios) {
		this.accesorios = accesorios;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getEmailCreador() {
		return emailCreador;
	}

	public void setEmailCreador(String emailCreador) {
		this.emailCreador = emailCreador;
	}

}
