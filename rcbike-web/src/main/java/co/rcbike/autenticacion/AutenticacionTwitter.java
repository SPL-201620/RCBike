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
        twitter.setOAuthConsumer("pzJH9IW2nAcFLWFB6iw9mtxI2", " zFN0CMS8gPerfifG2VxwEV168LshKv4yzFZgLPEjn5O7Dj7fMy");
        log.debug("Inicializado " + this.getClass().getName());
    }

    public void requestToken() throws TwitterException {
        RequestToken requestToken = twitter.getOAuthRequestToken("http://localhost:8080/rcbike-web/twitter");
        log.info(requestToken);
    }
}
