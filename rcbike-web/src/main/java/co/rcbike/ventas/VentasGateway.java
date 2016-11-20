package co.rcbike.ventas;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import co.rcbike.ventas.model.OperacionesVentas;
import co.rcbike.ventas.model.VentaWeb;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class VentasGateway extends RcbikeRestGateway {

    public static final GenericType<List<VentaWeb>> TYPE_LIST_VENTAS = new GenericType<List<VentaWeb>>() {
    };

    @Inject
    @Snoop(serviceName = OperacionesVentas.EP_VENTA)
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }

    public Long crearVenta(VentaWeb venta) {
        return webTarget().path(OperacionesVentas.EP_VENTA).path(OperacionesVentas.EP_VENTA).request()
                .post(Entity.json(venta), Long.class);
    }

    public List<VentaWeb> listVentasByEmail(String email) {
        return webTarget().path(OperacionesVentas.EP_VENTA).path(OperacionesVentas.VENTAS)
                .path(OperacionesVentas.POR_EMAIL).queryParam("emailCreador", email).request().get(TYPE_LIST_VENTAS);

    }

    public List<VentaWeb> listPublicacionesByEmail() {
        return webTarget().path(OperacionesVentas.EP_VENTA).path(OperacionesVentas.VENTAS).request()
                .get(TYPE_LIST_VENTAS);
    }
}
