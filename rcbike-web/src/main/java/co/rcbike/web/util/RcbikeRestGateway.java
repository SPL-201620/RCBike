package co.rcbike.web.util;

import javax.ws.rs.client.WebTarget;

import eu.agilejava.snoop.client.SnoopServiceClient;

public abstract class RcbikeRestGateway {

    protected abstract SnoopServiceClient client();

    protected WebTarget webTarget() {
        return client().getServiceRoot();
    }
}
