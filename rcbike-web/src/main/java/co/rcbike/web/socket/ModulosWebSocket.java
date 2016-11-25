package co.rcbike.web.socket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import co.rcbike.gui.ModulosManager;
import lombok.extern.jbosslog.JBossLog;

@ApplicationScoped
@ServerEndpoint("/websocket/modulos/{modulo}")
@JBossLog
public class ModulosWebSocket {

    @Inject
    private ModulosManager modulosManager;

    @OnOpen
    public void onOpen(Session session, @PathParam("modulo") String modulo) {
        modulosManager.subirModulo(modulo);
        log.info("Modulo registrado en capa web " + modulo);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason, @PathParam("modulo") String modulo) {
        modulosManager.bajarModulo(modulo);
        log.info("Modulo removido en capa web " + modulo);
    }

}
