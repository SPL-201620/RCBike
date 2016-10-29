package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PiezaConfiguracionWeb extends PiezaConfiguracion implements Serializable {
	private static final long serialVersionUID = 5448424178070964583L;
	private Long idConfiguracion;
	private Long idPieza;
	private TipoPiezaBicicleta tipo;
	private String descripcion;

	public Long getIdConfiguracion() {
		return idConfiguracion;
	}

	public void setIdConfiguracion(Long idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}

	public Long getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(Long idPieza) {
		this.idPieza = idPieza;
	}

	public TipoPiezaBicicleta getTipo() {
		return tipo;
	}

	public void setTipo(TipoPiezaBicicleta tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
