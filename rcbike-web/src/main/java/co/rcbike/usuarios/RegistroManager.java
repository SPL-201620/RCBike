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
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.primefaces.event.FileUploadEvent;

import com.google.common.io.Files;

import co.rcbike.autenticacion.AutenticacionManager;
import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModUsuarios;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.RegistroUsuario;
import co.rcbike.web.util.Navegacion;
import co.rcbike.web.util.Navegacion.Views;
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
    @ManagedProperty(value = "#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulosManager;

    @PostConstruct
    public void init() {
        this.email = autenticacionManager.getEmail();
        this.clave = autenticacionManager.getClave();
    }

    public String registrar() throws IOException {
        RegistroUsuario regUsuario = new RegistroUsuario();
        regUsuario.setEmail(email);
        regUsuario.setNombres(nombres);
        regUsuario.setApellidos(apellidos);
        regUsuario.setClave(clave);
        regUsuario.setFoto(foto);

        Response response = modulosManager.root(Modulo.usuarios).path(ModUsuarios.ENDPNT_USUARIOS).request()
                .post(Entity.json(regUsuario));
        log.debug(response);

        autenticacionManager.setEmail(email);
        autenticacionManager.setClave(clave);
        autenticacionManager.autenticar();
        return Navegacion.redirectView(Views.dashboard);
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
