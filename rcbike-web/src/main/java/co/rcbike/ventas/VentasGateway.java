package co.rcbike.ventas;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import co.rcbike.ventas.model.OperacionesVentas;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@SuppressWarnings("serial")
@SessionScoped
public class VentasGateway implements Serializable {
    @Inject
    @Snoop(serviceName = OperacionesVentas.EP_VENTA)
    private SnoopServiceClient ventaService;
}
