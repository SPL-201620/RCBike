package co.rcbike.autenticacion.strategy.twitter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Startup
@Singleton
public class TwitterAdapter {

    private Twitter twitter;

    @PostConstruct
    public void init() {
        twitter = TwitterFactory.getSingleton();
    }
}
