package co.rcbike.usuarios.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import co.rcbike.usuarios.model.OperacionesUsuarios;
import eu.agilejava.snoop.annotation.EnableSnoopClient;

@EnableSnoopClient(serviceName = OperacionesUsuarios.EP_USUARIOS)
@ApplicationPath("rest")
public class RestApplication extends Application {
    /* class body intentionally left blank */
}
