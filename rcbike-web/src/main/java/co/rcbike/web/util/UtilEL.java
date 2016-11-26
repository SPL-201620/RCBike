package co.rcbike.web.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UtilEL implements Serializable {

    public String parseBoolean(boolean bool) {
        return bool ? "Si" : "No";
    }

    public String capitalize(String value) {
        return StringUtils.capitalize(value.replace('_', ' ').toLowerCase());
    }
}
