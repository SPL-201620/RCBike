package co.rcbike.alquiler.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import co.rcbike.alquiler.model.OperacionesAlquiler;
import eu.agilejava.snoop.annotation.EnableSnoopClient;

@EnableSnoopClient(serviceName = OperacionesAlquiler.EP_ALQUILER)
@ApplicationPath("rest")
public class RestApplication extends Application {
    /* class body intentionally left blank */
}
