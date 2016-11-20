package co.rcbike.gui;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.menu.MenuModel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;

@SuppressWarnings("serial")
@ManagedBean(eager = true)
@ApplicationScoped
@JBossLog
public class ModulosManager implements Serializable {

    public enum Modulo {
        autenticacion,
        usuarios,
        //
        venta,
        alquiler,
        configurador,
        reportes,
        mensajeria,
        desplazamientos,
        //
        sinc_redes;

    }

    public static class ModDesplazamientos {
        public static final String ENDPNT_GRUPAL = "grupal";
        public static final String ENDPNT_INDIVIDUAL = "individual";

    }

    @Getter
    @Setter
    private MenuModel model;

}
