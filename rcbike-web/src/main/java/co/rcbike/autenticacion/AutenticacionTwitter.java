package co.rcbike.autenticacion;

import static co.rcbike.web.util.Navegacion.redirectView;
import static co.rcbike.web.util.Navegacion.Views.registro;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.OperacionesUsuarios;
import co.rcbike.web.util.Navegacion;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@SessionScoped
@JBossLog
public class AutenticacionTwitter implements Serializable {

    private Twitter twitter;
    private RequestToken requestToken;

    @Getter
    @Setter
    private String authContent;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @PostConstruct
    public void init() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("pzJH9IW2nAcFLWFB6iw9mtxI2", "zFN0CMS8gPerfifG2VxwEV168LshKv4yzFZgLPEjn5O7Dj7fMy");
        log.debug("Inicializado " + this.getClass().getName());
    }

    public void requestToken() throws TwitterException, IOException {
        requestToken = twitter
                .getOAuthRequestToken("http://localhost:8080/rcbike-web/site/pb/login.xhtml?serv=twitter");
        FacesContext.getCurrentInstance().getExternalContext().redirect(requestToken.getAuthenticationURL());
    }

    @SuppressWarnings("unchecked")
    public String accessToken() throws Exception {
        // {oauth_token=uIFtzwAAAAAAxxEAAAABWChRegU,
        // =615d4GFhosJkuwCzqmwAFN8nvcdy1ngR}
        Map<String, String> twitterRedirect = (Map<String, String>) new ObjectMapper().readValue(authContent, Map.class)
                .get("payload");
        String verifier = twitterRedirect.get("oauth_verifier");
        AccessToken oAuthAccessToken = twitter.getOAuthAccessToken(requestToken, verifier);
        User autenticado = twitter.lookupUsers(oAuthAccessToken.getUserId()).get(0);
        String authEmail = autenticado.getId() + "@twitter.com";
        Response usuarioResp = modulosManager.root(Modulo.usuarios).path(OperacionesUsuarios.EP_USUARIOS)
                .path(OperacionesUsuarios.OP_USUARIO).path(authEmail).request().get();

        ResultadoAutenticacion resultadoAutenticacion = new ResultadoAutenticacion();
        resultadoAutenticacion.setEmail(authEmail);
        resultadoAutenticacion.setRequiereClave(false);
        resultadoAutenticacion.setNombresExternos(autenticado.getName());
        autenticacionManager.setResAutenticacion(resultadoAutenticacion);

        if (usuarioResp.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return redirectView(registro);
        } else {
            cambiarEstadoAutenticacion(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AutenticacionManager.EMAIL_ATTR,
                    authEmail);
            Navegacion.sendRedirect("/site/usuarios/dashboard.xhtml");
            return null;
        }
    }
    private void cambiarEstadoAutenticacion(boolean autenticado) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put(AutenticacionManager.AUTENTICADO_ATTR, autenticado);
    }
    /*
     * public void requestToken() throws Exception { Client cl =
     * ClientBuilder.newClient();
     * 
     * WebTarget mainEndpoint =
     * cl.target("https://api.twitter.com/oauth/request_token");
     * mainEndpoint.queryParam("oauth_callback",
     * "http://localhost:8080/rcbike-web/twitter");
     * mainEndpoint.queryParam("oauth_consumer_key",
     * "pzJH9IW2nAcFLWFB6iw9mtxI2");
     * mainEndpoint.queryParam("oauth_signature_method", "HMAC-SHA1");
     * mainEndpoint.queryParam("oauth_signature", "" +
     * calculateRFC2104HMAC("pzJH9IW2nAcFLWFB6iw9mtxI2",
     * "zFN0CMS8gPerfifG2VxwEV168LshKv4yzFZgLPEjn5O7Dj7fMy"));
     * mainEndpoint.queryParam("oauth_nonce", System.currentTimeMillis() +
     * 8795); mainEndpoint.queryParam("oauth_timestamp", "" +
     * System.currentTimeMillis() / 1000);
     * mainEndpoint.queryParam("oauth_version", "1.0");
     * 
     * Map post2 = mainEndpoint.request().post(null, Map.class);
     * 
     * log.info(post2); // log.info(Status.fromStatusCode(post.getStatus())); }
     * 
     * private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
     * 
     * private static String toHexString(byte[] bytes) { Formatter formatter =
     * new Formatter();
     * 
     * for (byte b : bytes) { formatter.format("%02x", b); }
     * 
     * return formatter.toString(); }
     * 
     * public static String calculateRFC2104HMAC(String data, String key) throws
     * SignatureException, NoSuchAlgorithmException, InvalidKeyException {
     * SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
     * HMAC_SHA1_ALGORITHM); Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
     * mac.init(signingKey); return toHexString(mac.doFinal(data.getBytes())); }
     */
}
