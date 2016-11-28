package co.rcbike.sinc_redes.model;

public class OperacionesSincronizacion {

    private OperacionesSincronizacion() {

    }

    // Enpoints
    public static final String EP_SINCRONIZACION = "sincronizacion";

    // Operaciones
    public static final String OP_PUBLICAR_EN_FACEBOOK = "publicarEnFacebook";
    public static final String OP_PUBLICAR_EN_TWITTER = "publicarEnTwitter";

    // Parametros
    public static final String PARAM_USER_ID = "userId";
    public static final String PARAM_ACCESS_TOKEN = "accessToken";
    public static final String PARAM_MESSAGE = "message";

    public static final String PARAM_CONSUMER_KEY_STR = "consumerKeyStr";
    public static final String PARAM_CONSUMER_SECRET_STR = "consumerSecretStr";
    public static final String PARAM_ACCESS_TOKEN_STR = "accessTokenStr";
    public static final String PARAM_ACCESS_TOKEN_SECRET_STR = "accessTokenSecretStr";
}
