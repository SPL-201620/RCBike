package co.rcbike.mensajeria.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import eu.agilejava.snoop.annotation.EnableSnoopClient;

@EnableSnoopClient(serviceName = "mensajeria")
@ApplicationPath("rest")
public class RestApplication extends Application {
  /* class body intentionally left blank */
}
