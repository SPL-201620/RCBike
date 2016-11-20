package co.rcbike.reportes;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@SuppressWarnings("serial")
@SessionScoped
public class ReportesGateway implements Serializable {
    @Inject
    @Snoop(serviceName = "reportes")
    private SnoopServiceClient reportesService;
}
