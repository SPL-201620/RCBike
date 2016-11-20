package co.rcbike.web.util;
import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import eu.agilejava.snoop.client.SnoopServiceUnavailableException;

@Aspect
public class RestNotFoundAspect {

    @Pointcut("within(co.rcbike.web.util.RcbikeRestGateway+)")
    public void gatewayExecution() {
    }

    @Pointcut("execution(* *(..))")
    public void anyMethod() {
    }

    @AfterThrowing(value = "gatewayExecution() && anyMethod()", throwing = "ex")
    public void handleRestNotFound(JoinPoint joinPoint, SnoopServiceUnavailableException ex) throws IOException {
        Navegacion.sendRedirect("/site/pb/error-modulo.xhtml");
    }

}