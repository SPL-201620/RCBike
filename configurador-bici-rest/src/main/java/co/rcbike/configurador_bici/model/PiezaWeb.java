package co.rcbike.configurador_bici.model;

import java.io.Serializable;

public class PiezaWeb extends Pieza implements Serializable {
	
	private static final long serialVersionUID = 9008988343979682086L;

	private Long idPiezaConfiguracion;

	private Long idConfiguracion;

	private String color;

	public Long getIdConfiguracion() {
		return idConfiguracion;
	}

	public void setIdConfiguracion(Long idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}

	public Long getIdPiezaConfiguracion() {
		return idPiezaConfiguracion;
	}

	public void setIdPiezaConfiguracion(Long idPiezaConfiguracion) {
		this.idPiezaConfiguracion = idPiezaConfiguracion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
