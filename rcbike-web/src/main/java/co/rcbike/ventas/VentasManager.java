package co.rcbike.ventas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.ws.rs.client.Entity;

import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.FileUploadEvent;

import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.ventas.model.OperacionesVentas;
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
	private String valor = new String();

	@Getter
	@Setter
	private Long idVenta = (-1L);

	@Getter
	@Setter
	private VentaWeb venta = new VentaWeb();

	@Getter
	@Setter
	private String foto;

	@Getter
	@Setter
	@ManagedProperty(value = "#{modulosManager}")
	private ModulosManager modulosManager;

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
		venta.setValor(valor);
		venta.setIdConfiguracion(idConfiguracion);
		venta.setFoto(foto);
		idVenta = modulosManager.root(Modulo.venta)
				.path(OperacionesVentas.VENTA).path(OperacionesVentas.VENTA)
				.request().post(Entity.json(venta), Long.class);

	}

}