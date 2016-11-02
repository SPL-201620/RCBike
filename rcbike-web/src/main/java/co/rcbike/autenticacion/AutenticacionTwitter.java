package co.rcbike.autenticacion;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import lombok.extern.jbosslog.JBossLog;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@ApplicationScoped
@JBossLog
public class AutenticacionTwitter implements Serializable {

    Twitter twitter;
    @PostConstruct
    public void init() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("pzJH9IW2nAcFLWFB6iw9mtxI2", "zFN0CMS8gPerfifG2VxwEV168LshKv4yzFZgLPEjn5O7Dj7fMy");
        log.debug("Inicializado " + this.getClass().getName());
    }

    public void requestToken() throws TwitterException {
        RequestToken requestToken = twitter.getOAuthRequestToken("http://localhost:8080/rcbike-web/twitter");
        log.info(requestToken);
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
