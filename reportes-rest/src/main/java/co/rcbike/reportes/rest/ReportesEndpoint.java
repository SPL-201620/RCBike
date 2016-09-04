package co.rcbike.reportes.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.rcbike.reportes.data.ReportesRepository;

@Path("/reportes")
@RequestScoped
public class ReportesEndpoint {

    @Inject
    private ReportesRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public String alive() {
		return "endpoint alive";
    }

}
