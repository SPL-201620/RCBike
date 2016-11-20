package co.rcbike.desplazamientos;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import co.rcbike.desplazamientos.model.RutaWeb;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class DesplazamientosGateway extends RcbikeRestGateway {

    public static final GenericType<List<RutaWeb>> TYPE_LIST_RUTA = new GenericType<List<RutaWeb>>() {
    };

    @Inject
    @Snoop(serviceName = "desplazamientos")
    private SnoopServiceClient service;

    @Override
    protected SnoopServiceClient client() {
        return service;
    }

    public void guardarParticipante(long idRuta, String email) {
        webTarget().path("grupal").path("guardarParticipante").queryParam("idRuta", idRuta).queryParam("email", email)
                .request().get();
    }

    public String clima(double latitud, double longitud) {
        return webTarget().path("clima").queryParam("latitud", latitud).queryParam("longitud", longitud).request()
                .get(String.class);
    }

    public void crearRutaGrupal(RutaWeb ruta) {
        webTarget().path("grupal").path("rutaGrupal").request().post(Entity.json(ruta));
    }

    public void crearRutaIndividual(RutaWeb ruta) {
        webTarget().path("individual").path("rutaIndividual").request().post(Entity.json(ruta));
    }

    public List<RutaWeb> listGruposCercanos(String latitud, String longitud) {
        return webTarget().path("grupal").path("rutasGrupales").path("cercanos").queryParam("latitud", latitud)
                .queryParam("longitud", longitud).request().get(TYPE_LIST_RUTA);
    }

    public List<RutaWeb> listGrupales() {
        return webTarget().path("grupal").path("listViajesGrupales").request().get(TYPE_LIST_RUTA);
    }

    public List<RutaWeb> listIndividualesByEmail(String email) {
        return webTarget().path("individual").path("rutasIndividuales").path("porEmail")
                .queryParam("emailCreador", email).request().get(TYPE_LIST_RUTA);
    }
}
