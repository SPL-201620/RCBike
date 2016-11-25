package co.rcbike.desplazamientos.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class MyClientEndpoint {
    @Inject
    private Logger logger;

    @OnOpen
    public void onOpen(Session session) {
        logger.info("Conectando a: " + session.getBasicRemote() + " notificando Modulo " + InicializadorModulo.MODULO);
    }

    @OnMessage
    public void processMessage(String message) {
    }

    @OnError
    public void processError(Throwable t) {
        logger.log(Level.INFO, "", t);
    }
}