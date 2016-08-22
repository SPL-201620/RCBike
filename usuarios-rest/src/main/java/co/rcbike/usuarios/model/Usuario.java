package co.rcbike.usuarios.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Usuario implements Serializable {

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
	private String foto;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "AMIGOS",//
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

}
