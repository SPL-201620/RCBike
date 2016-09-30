package co.rcbike.autenticacion;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class AutenticacionManager {

	private boolean autenticado;

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}
	
}
