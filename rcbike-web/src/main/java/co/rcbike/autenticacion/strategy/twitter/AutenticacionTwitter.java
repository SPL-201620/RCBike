package co.rcbike.autenticacion.strategy.twitter;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.rcbike.autenticacion.DatosAutenticacion;
import co.rcbike.autenticacion.DatosAutenticacion.EstadoAutenticacion;
import co.rcbike.autenticacion.strategy.AutenticacionStrategy;
import lombok.Getter;
import lombok.extern.jbosslog.JBossLog;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
@JBossLog
public class AutenticacionTwitter extends AutenticacionStrategy implements Serializable {

    private static final String CONSUMER_KEY = "pzJH9IW2nAcFLWFB6iw9mtxI2";
    private static final String CONSUMER_SECRET = "zFN0CMS8gPerfifG2VxwEV168LshKv4yzFZgLPEjn5O7Dj7fMy";

    @Getter
    private String consumerKey;
    @Getter
    private String consumerSecret;
    @Getter
    private String token;
    @Getter
    private String tokenSecret;

    private Twitter twitter;
    private RequestToken requestToken;

    @PostConstruct
    public void init() {
        consumerKey = CONSUMER_KEY;
        consumerSecret = CONSUMER_SECRET;
    }

    public void sendToTwitter() throws TwitterException, IOException {
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        requestToken = twitter
                .getOAuthRequestToken("http://localhost:8080/rcbike-web/site/pb/login.xhtml?serv=twitter");
        log.info("Redireccionando a Twitter");
        Faces.redirect(requestToken.getAuthenticationURL());
    }

    @Override
    public DatosAutenticacion autenticar(String contenidoAutenticacion) {
        return login(contenidoAutenticacion);
    }
    /**
     * Procesa el contenido de autenticacion especifico de Twitter
     * 
     * @param contenidoAutenticacion
     *            {"payload" = { "oauth_token":"ttt", "oauth_verifier":"vvv" }}
     * @return resultado de la autenticacion
     */
    private DatosAutenticacion login(String contenidoAutenticacion) {
        DatosAutenticacion resp = new DatosAutenticacion();
        try {
            User twitterUser = procesarRespuestaTwitter(contenidoAutenticacion);
            String twitterEmail = twitterUser.getId() + "@twitter.com";
            resp.setEmail(twitterEmail);
            resp.setNombres(twitterUser.getName());
            resp.setRequiereClave(false);
            resp.setRequiereEmail(false);
            if (usuarioExiste(twitterEmail)) {
                resp.setEstado(EstadoAutenticacion.OK);
            } else {
                resp.setEstado(EstadoAutenticacion.NO_EXISTE_USUARIO);
            }
        } catch (TwitterException | IOException e) {
            log.error("Error procesando autenticacion con Twitter", e);
            resp = buildErrorRespuesta(resp);
        }
        return resp;
    }

    @SuppressWarnings("unchecked")
    private User procesarRespuestaTwitter(String contenidoAutenticacion) throws TwitterException, IOException {
        Map<String, String> twitterRedirect = (Map<String, String>) new ObjectMapper()
                .readValue(contenidoAutenticacion, Map.class).get(ATTR_PAYLOAD);
        String verifier = twitterRedirect.get("oauth_verifier");
        AccessToken oAuthAccessToken = twitter.getOAuthAccessToken(requestToken, verifier);
        token = oAuthAccessToken.getToken();
        tokenSecret = oAuthAccessToken.getTokenSecret();
        User autenticado = twitter.lookupUsers(oAuthAccessToken.getUserId()).get(0);
        return autenticado;
    }

    @Override
    public String nombreServicio() {
        return "Twitter";
    }

}
