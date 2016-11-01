package co.rcbike.autenticacion.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@XmlRootElement(name = "ResultadoAutenticacion")
public class ResultadoAutenticacion implements Serializable {

    public enum EstadoAutenticacion {
        OK,
        CLAVE_ERRONEA,
        NO_EXISTE_USUARIO;
    }

    @JsonIgnore
    private EstadoAutenticacion estado;

    private String email;

    private String clave;

    private String nombresExternos;

    private String apellidosExternos;

    private String fotoExterna;

    public EstadoAutenticacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAutenticacion estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombresExternos() {
        return nombresExternos;
    }

    public void setNombresExternos(String nombresExternos) {
        this.nombresExternos = nombresExternos;
    }

    public String getApellidosExternos() {
        return apellidosExternos;
    }

    public void setApellidosExternos(String apellidosExternos) {
        this.apellidosExternos = apellidosExternos;
    }

    public String getFotoExterna() {
        return fotoExterna;
    }

    public void setFotoExterna(String fotoExterna) {
        this.fotoExterna = fotoExterna;
    }

}
