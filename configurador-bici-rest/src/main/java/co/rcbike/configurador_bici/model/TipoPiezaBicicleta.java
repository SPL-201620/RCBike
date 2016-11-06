package co.rcbike.configurador_bici.model;

public enum TipoPiezaBicicleta {

	MARCO(true), LLANTA_DELANTERA(true), LLANTA_TRASERA(true), FRENOS(false), CAMBIOS(
			false), LUZ(false), PITO(false);

	private boolean req;

	TipoPiezaBicicleta(boolean requerido) {
		req = requerido;
	}

	public boolean isReq() {
		return req;
	}

}
