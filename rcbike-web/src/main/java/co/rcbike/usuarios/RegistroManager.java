package co.rcbike.usuarios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.primefaces.event.FileUploadEvent;

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

    public String registrar() {
        RegistroUsuario regUsuario = new RegistroUsuario();
        regUsuario.setEmail(email);
        regUsuario.setNombres(nombres);
        regUsuario.setApellidos(apellidos);
        regUsuario.setClave(clave);

        Response response = modulosManager.root(Modulo.usuarios).path(ModUsuarios.ENDPNT_USUARIOS).request()
                .post(Entity.json(regUsuario));
        log.debug(response);

        autenticacionManager.setEmail(email);
        autenticacionManager.setClave(clave);
        autenticacionManager.autenticar();
        return Navegacion.redirectView(Views.dashboard);
    }

    public void cargarImagen(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
