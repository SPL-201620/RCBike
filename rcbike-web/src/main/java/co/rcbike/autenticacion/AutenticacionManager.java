package co.rcbike.autenticacion;

import static co.rcbike.web.util.Navegacion.redirectView;
import static co.rcbike.web.util.Navegacion.Views.dashboard;
import static co.rcbike.web.util.Navegacion.Views.error;
import static co.rcbike.web.util.Navegacion.Views.registro;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.rcbike.gui.ModulosManager;
import co.rcbike.gui.ModulosManager.ModAutenticacion;
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.web.util.Navegacion;
//import co.rcbike.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@SessionScoped
@JBossLog
public class AutenticacionManager implements Serializable {

    public static final String AUTENTICADO_ATTR = "sat_autenticado";
    public static final String EMAIL_ATTR = "sat_email";

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String clave;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulos;

    public static final String emailAutenticado() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get(AutenticacionManager.EMAIL_ATTR);
    }

    @PostConstruct
    public void init() {
        log.debug("Inicializado " + this.getClass().getName());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTENTICADO_ATTR, false);
    }

    public String autenticar() throws IOException {
        WebTarget serviceRoot = modulos.clienteSnoop(Modulo.autenticacion).getServiceRoot();
        Response response = serviceRoot.path(ModAutenticacion.ENDPNT_AUTENTICACION).queryParam("email", email)
                .queryParam("clave", clave).request().get();
        Status status = Response.Status.fromStatusCode(response.getStatus());

        cambiarEstadoAutenticacion(false);
        switch (status) {
            case NOT_FOUND :
                return redirectView(registro);
            case UNAUTHORIZED :
                clave = null;
                return null;
            case OK :
                clave = null;
                cambiarEstadoAutenticacion(true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(EMAIL_ATTR, email);
                Navegacion.sendRedirect("/site/usuarios/dashboard.xhtml");
                return null;
            default :
                return redirectView(error);
        }
    }

    public boolean autenticado() {
        return (boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .getOrDefault(AUTENTICADO_ATTR, false);
    }

    private void cambiarEstadoAutenticacion(boolean autenticado) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTENTICADO_ATTR, autenticado);
    }

    public void cerrarSesion() {
        cambiarEstadoAutenticacion(false);
    }

}
