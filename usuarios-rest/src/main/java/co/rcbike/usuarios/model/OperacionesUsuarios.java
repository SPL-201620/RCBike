package co.rcbike.usuarios.model;

public final class OperacionesUsuarios {

    private OperacionesUsuarios() {

    }

    // Enpoints
    public static final String EP_USUARIOS = "usuarios";

    // Operaciones
    public static final String OP_AMIGOS = "amigos";
    public static final String OP_NOAMIGOS = "noamigos";
    public static final String OP_USUARIOS = "usuarios";
    public static final String OP_USUARIO = "usuario";
    public static final String OP_AGREGAR_AMIGO = "agregar-amigo";
    public static final String OP_REMOVER_AMIGO = "remover-amigo";

    // Parametros
    public static final String PATH_PRM_EMAIL = "{email}";

}
