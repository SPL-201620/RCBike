package co.rcbike.configurador_bici.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.configurador_bici.service.ConfiguradorBiciService;

@Path("/configurar")
@RequestScoped
public class ConfiguradorBiciEndpoint {

    @Inject
    private ConfiguradorBiciService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String alive() {
        return "endpoint alive";
    }

}
