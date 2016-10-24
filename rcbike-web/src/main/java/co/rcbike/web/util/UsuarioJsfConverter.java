package co.rcbike.web.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("usuarioConverter")
public class UsuarioJsfConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
        // TODO Auto-generated method stub
        return null;
    }

}
