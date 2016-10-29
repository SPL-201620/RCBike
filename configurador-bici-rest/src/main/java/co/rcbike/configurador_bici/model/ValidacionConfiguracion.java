package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValidacionConfiguracion implements Serializable {
	private static final long serialVersionUID = -4235744723173212992L;
	private int contadorMarco = 0;
	private int contadorLlantaDelantera = 0;
	private int contadorLlantaTrasera = 0;
	boolean valida;

	public void incrementarContadorMarco() {
		++this.contadorMarco;
	}

	public void incrementarContadorLlantaDelantera() {
		++this.contadorLlantaDelantera;
	}

	public void incrementarContadorLlantaTrasera() {
		++this.contadorLlantaTrasera;
	}

	public int getContadorMarco() {
		return contadorMarco;
	}

	public void setContadorMarco(int contadorMarco) {
		this.contadorMarco = contadorMarco;
	}

	public int getContadorLlantaDelantera() {
		return contadorLlantaDelantera;
	}

	public void setContadorLlantaDelantera(int contadorLlantaDelantera) {
		this.contadorLlantaDelantera = contadorLlantaDelantera;
	}
	
	public int getContadorLlantaTrasera() {
		return contadorLlantaTrasera;
	}

	public void setContadorLlantaTrasera(int contadorLlantaTrasera) {
		this.contadorLlantaTrasera = contadorLlantaTrasera;
	}

	public boolean isValida() {
		return valida;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}

}
