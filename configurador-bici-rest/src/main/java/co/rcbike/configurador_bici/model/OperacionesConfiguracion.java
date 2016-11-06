package co.rcbike.configurador_bici.model;

public class OperacionesConfiguracion {

	private OperacionesConfiguracion() {

	}

	// Enpoints
	public static final String EP_CONFIGURACION = "configuracion";

	// Operaciones
	public static final String OP_PIEZAS = "piezas";
	public static final String OP_PIEZAS_BY_TIPO = "porTipo";
	public static final String OP_PIEZA_BY_ID = "piezasById";
	public static final String OP_PIEZA_CONFIGURACION = "piezaConfiguracion";
	// Parametros
	public static final String PATH_PRM_EMAIL = "{email}";
	public static final String PATH_PRM_TIPO = "tipo";

}
