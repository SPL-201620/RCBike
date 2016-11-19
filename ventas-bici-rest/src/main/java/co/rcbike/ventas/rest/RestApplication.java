package co.rcbike.ventas.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import co.rcbike.ventas.model.OperacionesVentas;
import eu.agilejava.snoop.annotation.EnableSnoopClient;

@EnableSnoopClient(serviceName = OperacionesVentas.EP_VENTA)
@ApplicationPath("rest")
public class RestApplication extends Application {
    /* class body intentionally left blank */
}
