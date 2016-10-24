package co.rcbike.web.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.CloseReason;
import javax.websocket.MessageHandler;
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
public class MensajeriaWebSocket {

    private Map<String, Session> sessionCliente = new HashMap<>();

    private Map<String, List<MessageHandler>> handlersCache = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("email-cliente") String emailCliente) {
        log.info("Open Session: " + emailCliente);
        sessionCliente.put(emailCliente, session);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason, @PathParam("email-cliente") String emailCliente) {
        log.info("Close Session: " + emailCliente);
        sessionCliente.remove(emailCliente);
    }

    @OnMessage
    public void processGreeting(Session session, String message, @PathParam("email-cliente") String emailCliente) {
        log.info("On Messsage: " + emailCliente);

    }

    public void addHandler(String email, MessageHandler handler) {
        List<MessageHandler> handlers = handlersCache.getOrDefault(email, new ArrayList<>());
        handlers.add(handler);
        handlersCache.put(email, handlers);
    }

    public void removeHandlers(String email) {
        handlersCache.remove(email);
    }
}
