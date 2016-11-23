
package co.rcbike.sinc_redes.service;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;
import twitter4j.auth.AccessToken;

@Stateless
public class SincRedesService {

	public String postOnFacebook(String userId, String accessToken, String message) {
		Client cl = ClientBuilder.newClient();
		WebTarget mainEndpoint = cl.target("https://graph.facebook.com/v2.8").path(userId).path("feed");
		WebTarget webTarget = mainEndpoint.queryParam("access_token", accessToken).queryParam("message", message);

		Response postResponse = webTarget.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity("A string entity to be POSTed", MediaType.TEXT_PLAIN));
		return postResponse.readEntity(String.class);
	}

	public TwitterResponse postOnTwitter(String consumerKey, String consumerSecret, String accessToken,
			String accessTokenSecret, String message) {
		Twitter twitter = new TwitterFactory().getInstance();

		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		AccessToken token = new AccessToken(accessToken, accessTokenSecret);

		twitter.setOAuthAccessToken(token);

		try {
			Status status = twitter.updateStatus(message);
			return status;
		} catch (TwitterException e) {
			return e;
		}
	}

}
