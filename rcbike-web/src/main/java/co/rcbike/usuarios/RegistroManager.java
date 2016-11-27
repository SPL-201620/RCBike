package co.rcbike.usuarios;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;

import com.google.common.io.Files;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.usuarios.model.RegistroUsuario;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
@JBossLog
public class RegistroManager implements Serializable {

    @Getter
    @Setter
    @NotNull
    private String email;

    @Getter
    @Setter
    @NotNull
    private String clave;

    @Getter
    @Setter
    @NotNull
    private String nombres;

    @Getter
    @Setter
    @NotNull
    private String apellidos;

    @Getter
    @Setter
    private String foto;

    @Getter
    @Setter
    private String servicio;

    @Getter
    @Setter
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @Inject
    private UsuariosGateway gateway;

    @PostConstruct
    public void init() {
        this.servicio = autenticacionManager.getServicioAutenticacion().toLowerCase();
        this.email = autenticacionManager.getDatosAut().getEmail();
        this.nombres = autenticacionManager.getDatosAut().getNombres();
        this.apellidos = autenticacionManager.getDatosAut().getApellidos();
    }

    public void registrar() throws IOException {
        RegistroUsuario regUsuario = new RegistroUsuario();
        regUsuario.setEmail(email);
        regUsuario.setNombres(nombres);
        regUsuario.setApellidos(apellidos);
        regUsuario.setClave(clave);
        regUsuario.setFoto(foto);

        Response response = gateway.crearUsuario(regUsuario);
        log.debug(response);
        if (response.getStatus() == Status.OK.getStatusCode()) {
            autenticacionManager.darIngreso(email);
            Faces.redirect("site/usuarios/dashboard.xhtml");
        } else if (response.getStatus() == Status.BAD_REQUEST.getStatusCode()) {
            Messages.create(response.getEntity().toString()).error().add();
        }
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
}
