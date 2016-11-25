package co.rcbike.alquiler.util;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@Startup
@Singleton
public class InicializadorModulo {

    public static final String MODULO = "alquiler";
    public static final String PROPERTY = "co.rcbike.modulo." + MODULO;
    public static int intentos = 6;

    @Inject
    private Logger logger;

    @Inject
    private MyClientEndpoint clientEnpoint;

    private ScheduledExecutorService schExServ = Executors.newScheduledThreadPool(1);

    private Session session = null;

    @PostConstruct
    public void init() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://localhost:8080/rcbike-web/websocket/modulos/" + MODULO + "/";
            logger.info("Connecting to " + uri);
            session = container.connectToServer(clientEnpoint, URI.create(uri));
        } catch (DeploymentException | IOException ex) {
            if (intentos == 0) {
                logger.info("Intentos completados no fue posible establecer comunicacion");
                return;
            }
            schExServ.schedule(new Callable<Void>() {
                @Override
                public Void call() {
                    intentos--;
                    init();
                    return null;
                }
            }, 10, TimeUnit.SECONDS);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            session.close();
        } catch (IOException e) {

        }
    }
}
