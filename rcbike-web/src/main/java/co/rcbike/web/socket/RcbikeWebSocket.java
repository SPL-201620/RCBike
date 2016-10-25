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

import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@ServerEndpoint("/websocket/{email-cliente}")
@JBossLog
public class RcbikeWebSocket {

    @Inject
    private NotifyWebSocketClient webSocketNotifier;

    @OnOpen
    public void onOpen(Session session, @PathParam("email-cliente") String emailCliente) {
        log.info("Open Session: " + emailCliente);
        webSocketNotifier.addSession(emailCliente, session);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason, @PathParam("email-cliente") String emailCliente) {
        log.info("Close Session: " + emailCliente);
        webSocketNotifier.removeSession(emailCliente);
    }

    @OnMessage
    public void processGreeting(Session session, String message, @PathParam("email-cliente") String emailCliente) {
        log.info("On Messsage: " + emailCliente);

    }

}
