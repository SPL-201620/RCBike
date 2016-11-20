package co.rcbike.reportes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class ReportesGateway extends RcbikeRestGateway {
    @Inject
    @Snoop(serviceName = "reportes")
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }
}
