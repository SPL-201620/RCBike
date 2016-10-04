package co.rcbike.usuarios;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;

import co.rcbike.autenticacion.AutenticacionManager;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RegistroManager implements Serializable{

    @Getter
    @Setter
    @NotNull
    private String email;

    @Getter
    @Setter
    @NotNull
    private String clave;

    @Getter
    @Setter
    @NotNull
    private String nombres;

    @Getter
    @Setter
    @NotNull
    private String apellidos;

    @Getter
    @Setter
    @ManagedProperty(value="#{autenticacionManager}")
    private AutenticacionManager autenticacionManager;

    @PostConstruct
    public void init() {
        this.email = autenticacionManager.getEmail();
        this.clave = autenticacionManager.getClave();
        autenticacionManager.setClave(null);
        
    }

    public void registrar() {
       System.out.println(this); 
    }
}
