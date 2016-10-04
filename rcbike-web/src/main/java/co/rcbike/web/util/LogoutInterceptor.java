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

import lombok.extern.jbosslog.JBossLog;

@WebFilter("/logout")
@JBossLog
public class LogoutInterceptor implements Filter {

    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        log.info(request.toString());
        if (request instanceof HttpServletRequest) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpServletRequest httpRq = (HttpServletRequest) request;
            HttpSession session = httpRq.getSession(true);
            session.invalidate();
            httpResponse.sendRedirect(httpRq.getContextPath() + "/site/pb/dashboard.xhtml");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup global variables if necessary.
    }

}