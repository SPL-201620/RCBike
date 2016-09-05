package co.rcbike.desplazamientos.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import eu.agilejava.snoop.annotation.EnableSnoopClient;

@EnableSnoopClient(serviceName = "desplazamientos")
@ApplicationPath("rest")
public class RestApplication extends Application {
    /* class body intentionally left blank */
}
