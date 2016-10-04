package co.rcbike.web.util;

public class Navegacion {
    
    public enum Views{
        dashboard,
        error,
        login,
        registro;
    }
    
    public static final String JSF_REDIRECT = "?faces-redirect=true";
    
    public static final String redirectView(Views view){
        return view.toString()+JSF_REDIRECT;
    }
    
}
