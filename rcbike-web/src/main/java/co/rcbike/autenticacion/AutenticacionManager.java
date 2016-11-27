package co.rcbike.autenticacion;

import static co.rcbike.web.util.Navegacion.redirectView;
import static co.rcbike.web.util.Navegacion.Views.registro;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.rcbike.autenticacion.strategy.AutenticacionStrategy;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@SessionScoped
public class AutenticacionManager implements Serializable {

    public static final String AUTENTICADO_ATTR = "sat_autenticado";
    public static final String EMAIL_ATTR = "sat_email";

    @Getter
    private static final String PARAM_AUTH_SERVICE = "auth-service";

    @Getter
    private static final String PARAM_AUTH_CONTENT = "auth-content";

    @Getter
    private final String serverIp = "localhost:8080";

    @Getter
    @Setter
    private String servicioAutenticacion;

    @Getter
    @Setter
    private String contenidoAutenticacion;

    @Getter
    @Setter
    private DatosAutenticacion datosAut;

    public static boolean autenticado() {
        return (boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .getOrDefault(AUTENTICADO_ATTR, false);
    }

    public static final String emailAutenticado() {
        return (String) Faces.getSessionAttribute(AutenticacionManager.EMAIL_ATTR);
    }

    @PostConstruct
    public void init() {
        cambiarEstadoAutenticacion(false);
    }

    @SuppressWarnings("el-syntax")
    public String login() throws IOException {
        AutenticacionStrategy estrategia = Faces.evaluateExpressionGet("#{autenticacion" + servicioAutenticacion + "}");
        DatosAutenticacion respAutenticacion = estrategia.autenticar(contenidoAutenticacion);
        this.datosAut = respAutenticacion;
        switch (respAutenticacion.getEstado()) {
            case OK :
                cambiarEstadoAutenticacion(true);
                initEmailAutenticacion(respAutenticacion.getEmail());
                Faces.redirect("site/usuarios/dashboard.xhtml");
                break;
            case NO_EXISTE_USUARIO :
                return redirectView(registro);
            case CLAVE_ERRONEA :
                Messages.create("Usuario o Clave no corresponde, intente de nuevo").error().add();
                break;
            case ERROR :
                return null;

        }
        return null;
    }

    private void cambiarEstadoAutenticacion(boolean autenticado) {
        Faces.getSession().setAttribute(AUTENTICADO_ATTR, autenticado);
    }

    private void initEmailAutenticacion(String email) {
        Faces.getSession().setAttribute(EMAIL_ATTR, email);
        datosAut = null;
    }

    public void cerrarSesion() {
        cambiarEstadoAutenticacion(false);
    }

    public void darIngreso(String email) {
        cambiarEstadoAutenticacion(true);
        initEmailAutenticacion(email);

    }

}
