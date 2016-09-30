package co.rcbike.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ModulosManager {

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
        sinc_redes
    }

}
