package co.rcbike.configurador_bici.model;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class Pieza implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoPiezaBicicleta tipo;

    @NotNull
    @NotEmpty
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPiezaBicicleta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPiezaBicicleta tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int i;
        Long hash = 0L;
        hash += (getId() != null ? getId().hashCode() : 0L);
        i = hash.intValue();
        return i;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof PiezaWeb)) {
            return false;
        }
        PiezaWeb other = (PiezaWeb) object;
        if ((this.getId() == null && other.getId() != null)
                || (this.getId() != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }
}
