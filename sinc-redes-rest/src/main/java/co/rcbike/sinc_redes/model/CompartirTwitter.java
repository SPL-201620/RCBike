package co.rcbike.sinc_redes.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CompartirTwitter implements Serializable {

    private String consumerKey;

    private String consumerSecret;

    private String token;

    private String tokenSecret;

    private String mensaje;

    public CompartirTwitter() {
    }

    public CompartirTwitter(String consumerKey, String consumerSecret, String token, String tokenSecret,
            String mensaje) {
        super();
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.token = token;
        this.tokenSecret = tokenSecret;
        this.mensaje = mensaje;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
