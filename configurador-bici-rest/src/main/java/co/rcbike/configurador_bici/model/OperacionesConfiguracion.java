package co.rcbike.configurador_bici.model;

public class OperacionesConfiguracion {

    private OperacionesConfiguracion() {

    }

    // Enpoints
    public static final String EP_CONFIGURACION = "configuracion";

    // Operaciones
    public static final String OP_CONFIGURACIONES = "configuraciones";
    public static final String OP_PIEZAS = "piezas";
    public static final String OP_PIEZAS_BY_TIPO = "porTipo";
    public static final String OP_BY_EMAIL = "porEmail";
    public static final String OP_PIEZA_BY_ID = "piezasById";
    public static final String OP_PIEZAS_CONFIGURACION = "piezasConfiguracion";
    public static final String OP_PIEZA_CONFIGURACION = "piezaConfiguracion";
    public static final String OP_COLORES = "colores";
    public static final String OP_COLOR = "color";
    public static final String OP_VALIDAR = "validar";
    public static final String OP_PIEZA = "pieza";

    // Parametros
    public static final String PATH_PRM_EMAIL = "{email}";
    public static final String PATH_PRM_ID = "{id}";
    public static final String PRM_ID = "id";
    public static final String PATH_PRM_TIPO = "tipo";
    public static final String PARAM_EMAIL_CREADOR = "emailCreador";
    public static final String PARAM_CONFIGURACION = "configuracion";

}
