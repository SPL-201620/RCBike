package co.rcbike.sinc_redes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.rcbike.sinc_redes.model.CompartirTwitter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class RedesManager implements Serializable {

    @Inject
    private RedesGateway gateway;

    @Getter
    @Setter
    private String mensaje;

    @Getter
    @Setter
    private String url;

    public void facebook() {
        String userId = Faces.evaluateExpressionGet("#{autenticacionFacebook.userID}");
        String accessToken = Faces.evaluateExpressionGet("#{autenticacionFacebook.accessToken}");
        Response respCompartir = gateway.compartirFacebook(userId, accessToken, mensaje + " " + url);
        if (Status.fromStatusCode(respCompartir.getStatus()).equals(Status.OK)) {
            Messages.create("Tu recorrido fue publicado exitosamente en Facebook").add();
        } else
            Messages.create("No fue posible entregar el mensaje solicitado a Facebook\n"
                    + respCompartir.readEntity(String.class)).error().add();
    }

    public void twitter() {
        String consumerKey = Faces.evaluateExpressionGet("#{autenticacionTwitter.consumerKey}");
        String consumerSecret = Faces.evaluateExpressionGet("#{autenticacionTwitter.consumerSecret}");
        String token = Faces.evaluateExpressionGet("#{autenticacionTwitter.token}");
        String tokenSecret = Faces.evaluateExpressionGet("#{autenticacionTwitter.tokenSecret}");
        CompartirTwitter compartirTwitter = new CompartirTwitter(consumerKey, consumerSecret, token, tokenSecret,
                mensaje + " " + url);
        Response respCompartir = gateway.compartirTwitter(compartirTwitter);
        if (Status.fromStatusCode(respCompartir.getStatus()).equals(Status.OK)) {
            Messages.create("Tu recorrido fue publicado exitosamente en Twitter").add();
        } else
            Messages.create("No fue posible entregar el mensaje solicitado a Twitter\n"
                    + respCompartir.readEntity(String.class)).error().add();
    }
}
