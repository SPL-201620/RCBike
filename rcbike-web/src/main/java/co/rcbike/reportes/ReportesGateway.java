package co.rcbike.reportes;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;

import co.rcbike.reportes.model.OperacionesReportes;
import co.rcbike.reportes.model.ResumenWeb;
import co.rcbike.reportes.model.TipoReporte;
import co.rcbike.web.util.RcbikeRestGateway;
import eu.agilejava.snoop.annotation.Snoop;
import eu.agilejava.snoop.client.SnoopServiceClient;

@RequestScoped
public class ReportesGateway extends RcbikeRestGateway {

	public static final GenericType<List<ResumenWeb>> TYPE_LIST_RESUMEN_WEB = new GenericType<List<ResumenWeb>>() {
	};

	@Inject
	@Snoop(serviceName = "reportes")
	private SnoopServiceClient service;

	@Override
	protected SnoopServiceClient client() {
		return service;
	}

	public List<ResumenWeb> findRecorridos(String fechaInicio, String fechaFin,
			TipoReporte tipo, String emailCreador) {
		return webTarget().path(OperacionesReportes.REPORTES)
				.path(OperacionesReportes.REPORTE).queryParam("tipo", tipo)
				.queryParam("fechaInicio", fechaInicio)
				.queryParam("fechaFinal", fechaFin)
				.queryParam("emailCreador", emailCreador).request()
				.get(TYPE_LIST_RESUMEN_WEB);
	}
}
