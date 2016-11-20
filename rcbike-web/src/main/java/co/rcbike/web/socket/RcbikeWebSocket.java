package co.rcbike.web.socket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/websocket/{email-cliente}")
public class RcbikeWebSocket {

    @Inject
    private NotifyWebSocketClient webSocketNotifier;

    @OnOpen
    public void onOpen(Session session, @PathParam("email-cliente") String emailCliente) {
        webSocketNotifier.addSession(emailCliente, session);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason, @PathParam("email-cliente") String emailCliente) {
        webSocketNotifier.removeSession(emailCliente);
    }

    @OnMessage
    public void processGreeting(Session session, String message, @PathParam("email-cliente") String emailCliente) {

    }

}
