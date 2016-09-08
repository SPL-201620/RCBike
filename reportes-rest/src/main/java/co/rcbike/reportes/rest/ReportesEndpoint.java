package co.rcbike.reportes.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.reportes.service.ReportesService;

@Path("/reportes")
@RequestScoped
public class ReportesEndpoint {

    @Inject
    private ReportesService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String alive() {
        return "endpoint alive";
    }

}
