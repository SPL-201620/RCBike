package co.rcbike.web.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UtilEL implements Serializable {

    @Getter
    private static final String FACEBOOK_APP_ID = "183527332051435";
    @Getter
    private static final String GMAP_KEY = "AIzaSyBfB1qF1ZdhXSYI_zBqrhRh03xy05pQfKs";

    public String parseBoolean(boolean bool) {
        return bool ? "Si" : "No";
    }

    public String capitalize(String value) {
        return StringUtils.capitalize(value.replace('_', ' ').toLowerCase());
    }
}
