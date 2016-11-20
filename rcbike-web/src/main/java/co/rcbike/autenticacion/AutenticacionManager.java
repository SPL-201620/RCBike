package co.rcbike.autenticacion;

import static co.rcbike.web.util.Navegacion.redirectView;
import static co.rcbike.web.util.Navegacion.Views.error;
import static co.rcbike.web.util.Navegacion.Views.registro;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.rcbike.autenticacion.model.ResultadoAutenticacion;
import co.rcbike.web.util.Navegacion;
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
    private static final String PARAM_AUTH_SERVICE = "auth-service";

    @Getter
    private final String serverIp = "localhost:8080";

    @Getter
    @Setter
    private ResultadoAutenticacion resAutenticacion;

    @Getter
    @Setter
    private String authContent;

    @Inject
    private AutenticacionGateway gateway;

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

        Response response = gateway.autenticar(authContent);

        Status status = Response.Status.fromStatusCode(response.getStatus());

        cambiarEstadoAutenticacion(false);
        switch (status) {
            case NOT_FOUND :
                extractResultadoAutenticacion(response);
                return redirectView(registro);
            case UNAUTHORIZED :
                return null;
            case OK :
                extractResultadoAutenticacion(response);
                cambiarEstadoAutenticacion(true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(EMAIL_ATTR,
                        resAutenticacion.getEmail());
                Navegacion.sendRedirect("/site/usuarios/dashboard.xhtml");
                return null;
            default :
                return redirectView(error);
        }
    }

    private void extractResultadoAutenticacion(Response response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String writeValueAsString = objectMapper.writer().withDefaultPrettyPrinter()
                .writeValueAsString(response.readEntity(Map.class).get("entity"));
        resAutenticacion = objectMapper.readValue(writeValueAsString, ResultadoAutenticacion.class);
    }

    public static boolean autenticado() {
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
