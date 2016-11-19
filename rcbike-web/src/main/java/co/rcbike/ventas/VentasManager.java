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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.ws.rs.client.Entity;

import org.primefaces.event.FileUploadEvent;

import com.google.common.io.Files;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.ventas.model.OperacionesVentas;
import co.rcbike.ventas.model.VentaWeb;
import co.rcbike.web.util.UtilRest;
import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @PostConstruct
    public void init() {
        findVentasByEmail();
        findComprarByEmail();
    }

    public void cargarImagen(FileUploadEvent event) throws IOException {
        String fileExtension = Files.getFileExtension(event.getFile().getFileName());

        BufferedImage read = ImageIO.read(event.getFile().getInputstream());
        Image scaled = read.getScaledInstance(-1, 150, Image.SCALE_SMOOTH);

        BufferedImage buffered = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), read.getType());
        buffered.getGraphics().drawImage(scaled, 0, 0, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(buffered, fileExtension, baos);

        foto = Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    public void insertVenta() {
        venta.setValor(valor);
        venta.setIdConfiguracion(idConfiguracion);
        venta.setFoto(foto);
        venta.setAccesorios(accesorio);
        venta.setCiudadVenta(ciudad);
        venta.setEstado(estado);
        venta.setMarca(marca);
        venta.setObservaciones(observaciones);
        venta.setEmailCreador(AutenticacionManager.emailAutenticado());
        idVenta = modulosManager.root(Modulo.venta).path(OperacionesVentas.EP_VENTA).path(OperacionesVentas.EP_VENTA)
                .request().post(Entity.json(venta), Long.class);

        findComprarByEmail();
        findVentasByEmail();

    }

    public void findVentasByEmail() {
        ventaWebList = modulosManager.root(Modulo.venta).path(OperacionesVentas.EP_VENTA).path(OperacionesVentas.VENTAS)
                .path(OperacionesVentas.POR_EMAIL).queryParam("emailCreador", AutenticacionManager.emailAutenticado())
                .request().get(UtilRest.TYPE_LIST_VENTAS);

    }

    public void findComprarByEmail() {
        compraVentaWebList = modulosManager.root(Modulo.venta).path(OperacionesVentas.EP_VENTA)
                .path(OperacionesVentas.VENTAS).request().get(UtilRest.TYPE_LIST_VENTAS);

    }

    public void actualizaIdConfiguracion(Long idConfig) {
        idConfiguracion = idConfig;
    }

    public void actualizaCompraSelected(String emailCreador) {
        compraSelected = emailCreador;
    }
}