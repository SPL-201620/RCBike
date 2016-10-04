package co.rcbike.autenticacion;

import static co.rcbike.web.util.Navegacion.redirectView;
import static co.rcbike.web.util.Navegacion.Views.dashboard;
import static co.rcbike.web.util.Navegacion.Views.error;
import static co.rcbike.web.util.Navegacion.Views.registro;

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
import co.rcbike.gui.ModulosManager.Modulo;
import co.rcbike.usuarios.model.Usuario;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@SessionScoped
@JBossLog
public class AutenticacionManager implements Serializable {

    public static final String AUTENTICADO_ATTR = "autenticado";

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String clave;

    @Getter
    @Setter
    private Usuario usuario;

    @Getter
    @Setter
    @ManagedProperty(value = "#{modulosManager}")
    private ModulosManager modulos;

    @PostConstruct
    public void init() {
        log.debug("Inicializado " + this.getClass().getName());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTENTICADO_ATTR, false);
    }

    public String autenticar() {
        WebTarget serviceRoot = modulos.clienteSnoop(Modulo.autenticacion).getServiceRoot();
        Response response = serviceRoot.path("autenticar").queryParam("email", email).queryParam("clave", clave)
                .request().get();
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
                return redirectView(dashboard);
            default :
                return redirectView(error);
        }
    }

    public boolean autenticado() {
        return (boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTENTICADO_ATTR);
    }

    private void cambiarEstadoAutenticacion(boolean autenticado) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTENTICADO_ATTR, autenticado);
    }

    public void cerrarSesion() {
        cambiarEstadoAutenticacion(false);
    }

}
