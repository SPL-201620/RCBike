
package co.rcbike.sinc_redes.service;

import java.util.Map;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Stateless
public class SincRedesService {

	public Map<String, Object> postOnFacebook(String userId, String accessToken, String message) {
		Client cl = ClientBuilder.newClient();
		WebTarget mainEndpoint = cl.target("https://graph.facebook.com/v2.8").path(userId).path("feed");
		WebTarget webTarget = mainEndpoint.queryParam("access_token", accessToken).queryParam("message", message);

		Response postResponse = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity("", MediaType.TEXT_PLAIN));
		return postResponse.readEntity(new GenericType<Map<String, Object>>() {});
	}

	public Status postOnTwitter(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret,
			String message) throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		AccessToken token = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthAccessToken(token);
		Status status = twitter.updateStatus(message);
		return status;
	}

}
