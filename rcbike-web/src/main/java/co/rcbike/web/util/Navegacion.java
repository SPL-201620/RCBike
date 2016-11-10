package co.rcbike.web.util;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Navegacion {

    public enum Views {
        dashboard,
        error,
        login,
        registro;
    }

    public static final String JSF_REDIRECT = "?faces-redirect=true";

    public static final String redirectView(Views view) {
        return view.toString() + JSF_REDIRECT;
    }

    public static final void sendRedirect(String page) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + page);
    }
}
