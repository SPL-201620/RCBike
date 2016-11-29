package co.rcbike.reportes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.SelectEvent;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.reportes.model.ResumenWeb;
import co.rcbike.reportes.model.TipoReporte;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ReportesManager implements Serializable {

	@Getter
	@Setter
	private TipoReporte tipo;

	@Getter
	@Setter
	private List<TipoReporte> tiposReporte = new ArrayList<TipoReporte>();

	@Getter
	@Setter
	private List<ResumenWeb> recorridos = new ArrayList<ResumenWeb>();

	@Getter
	@Setter
	private Date fech;

	@Getter
	@Setter
	private String fechaInicio;

	@Getter
	@Setter
	private String fechaFin;

	@PostConstruct
	public void init() {
		tiposReporte.add(TipoReporte.MENSUAL);
		tiposReporte.add(TipoReporte.SEMANAL);
	}

	@Inject
	private ReportesGateway gateway;

	public void findRecorridos() {
		String emailCreador = AutenticacionManager.emailAutenticado();
		recorridos = gateway.findRecorridos(fechaInicio, fechaFin, tipo,
				emailCreador);
	}

	public void onDateSelectInicio(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		fechaInicio = format.format(event.getObject());
	}

	public void onDateSelectFin(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		fechaFin = format.format(event.getObject());
	}
}