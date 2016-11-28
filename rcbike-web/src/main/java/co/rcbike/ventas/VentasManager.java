package co.rcbike.ventas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.FileUploadEvent;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.ventas.model.VentaWeb;

import com.google.common.io.Files;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VentasManager implements Serializable {

	@Getter
	@Setter
	private Long idConfiguracion;

	@Getter
	@Setter
	private String valor;

	@Getter
	@Setter
	private String marca;

	@Getter
	@Setter
	private String ciudad;

	@Getter
	@Setter
	private String estado;

	@Getter
	@Setter
	private String accesorio;

	@Getter
	@Setter
	private Boolean vendida;

	@Getter
	@Setter
	private String observaciones;

	@Getter
	@Setter
	private Long idVenta;

	@Getter
	@Setter
	private VentaWeb venta = new VentaWeb();

	@Getter
	@Setter
	private String compraSelected = new String();

	@Getter
	@Setter
	private List<VentaWeb> ventaWebList = new ArrayList<VentaWeb>();

	@Getter
	@Setter
	private List<VentaWeb> compraVentaWebList = new ArrayList<VentaWeb>();

	@Getter
	@Setter
	private String foto;

	@Inject
	private VentasGateway gateway;

	@PostConstruct
	public void init() {
		findVentasByEmail();
		findComprarByEmail();
	}

	public void cargarImagen(FileUploadEvent event) throws IOException {
		String fileExtension = Files.getFileExtension(event.getFile()
				.getFileName());

		BufferedImage read = ImageIO.read(event.getFile().getInputstream());
		Image scaled = read.getScaledInstance(-1, 150, Image.SCALE_SMOOTH);

		BufferedImage buffered = new BufferedImage(scaled.getWidth(null),
				scaled.getHeight(null), read.getType());
		buffered.getGraphics().drawImage(scaled, 0, 0, null);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(buffered, fileExtension, baos);

		foto = Base64.getEncoder().encodeToString(baos.toByteArray());
	}

	public void insertVenta() {

		venta.setIdConfiguracion(idConfiguracion);
		venta.setFoto(foto);
		/*
		 * venta.setAccesorios(accesorio); venta.setCiudadVenta(ciudad);
		 * venta.setEstado(estado); venta.setMarca(marca);
		 * venta.setObservaciones(observaciones);
		 */
		venta.setEmailCreador(AutenticacionManager.emailAutenticado());
		idVenta = gateway.crearVenta(venta);

		findComprarByEmail();
		findVentasByEmail();
		venta = new VentaWeb();

	}

	public void findVentasByEmail() {
		ventaWebList = gateway.listVentasByEmail(AutenticacionManager
				.emailAutenticado());

	}

	public void findComprarByEmail() {
		compraVentaWebList = gateway.listPublicacionesByEmail();
	}

	public void actualizaIdConfiguracion(Long idConfig) {
		idConfiguracion = idConfig;
	}

	public void actualizaCompraSelected(String emailCreador) {
		compraSelected = emailCreador;
	}

	public void deleteVentas(Long id) {
		gateway.deleleVentas(id);
		findVentasByEmail();
	}

}