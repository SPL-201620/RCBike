package co.rcbike.web.util;

import java.util.List;

import javax.ws.rs.core.GenericType;

import co.rcbike.configurador_bici.model.PiezaWeb;

public final class UtilRest {

    private UtilRest() {
    }

    public static final GenericType<List<String>> TYPE_LIST_STRING = new GenericType<List<String>>() {
    };

    public static final GenericType<PiezaWeb> TYPE_PIEZA = new GenericType<PiezaWeb>() {
    };

}
