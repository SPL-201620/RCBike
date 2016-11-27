package co.rcbike.desplazamientos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import co.rcbike.desplazamientos.model.RutaWeb;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@ViewScoped
public class DesplazamientoPublicoManager implements Serializable {

    @Getter
    @Setter
    private RutaWeb ruta;

    @Inject
    private DesplazamientosGateway gateway;
    
    @PostConstruct
    public void init() {
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
    	        .getRequest();

    	String id = request.getParameter("id");
    	java.lang.System.out.print(".-----DesplazamientoPublicoManager\n" + "id : " + id);
    	
    	if(!id.isEmpty())
    	{
	    	try
	    	{
	    		Long idFinal = Long.valueOf(id).longValue();
	    		ruta = gateway.getIndividual(idFinal);
	    		java.lang.System.out.print("\n" + ruta.toString());
	    	}
	    	catch(NumberFormatException ex)
	    	{
	    		ruta = null;
	    		java.lang.System.out.print(ex.getMessage());
	    	}
    	}
    }
}