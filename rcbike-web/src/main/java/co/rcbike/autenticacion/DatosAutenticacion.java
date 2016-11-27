package co.rcbike.autenticacion;

import lombok.Getter;
import lombok.Setter;

public class DatosAutenticacion {

    public enum EstadoAutenticacion {
        OK,
        CLAVE_ERRONEA,
        NO_EXISTE_USUARIO,
        ERROR;
    }

    @Getter
    @Setter
    private EstadoAutenticacion estado;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private boolean requiereEmail;

    @Getter
    @Setter
    private String nombres;

    @Getter
    @Setter
    private String apellidos;

    @Getter
    @Setter
    private boolean requiereClave;

}
