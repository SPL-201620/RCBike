package co.rcbike.usuarios.model;

@SuppressWarnings("serial")
public class RegistroUsuario extends Usuario {

    private String clave;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
