package co.rcbike.web.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UtilEL implements Serializable {

    public String parseBoolean(boolean bool) {
        return bool ? "Si" : "No";
    }
}
