package co.rcbike.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.rcbike.autenticacion.AutenticacionManager;
import lombok.extern.jbosslog.JBossLog;

@WebFilter("/site/*")
@JBossLog
public class AutorizacionInterceptor implements Filter {

    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        log.info(request.toString());
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRq = (HttpServletRequest) request;
            HttpSession session = httpRq.getSession(true);
            if (!isAutenticado(session) && !isAccesoPublico(httpRq)) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect(httpRq.getContextPath() +"/site/pb/login.xhtml");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isAccesoPublico(HttpServletRequest httpRq) {
        String servletPath = httpRq.getServletPath();
        return servletPath.startsWith("/site/pb/");
    }

    private boolean isAutenticado(HttpSession session) {
        Object autenticado = session.getAttribute(AutenticacionManager.AUTENTICADO_ATTR);
        return autenticado == null ? false : (boolean) autenticado;
    }
    @Override
    public void destroy() {
        // Cleanup global variables if necessary.
    }

}