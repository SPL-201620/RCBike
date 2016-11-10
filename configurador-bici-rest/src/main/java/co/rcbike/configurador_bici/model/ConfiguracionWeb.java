package co.rcbike.configurador_bici.model;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfiguracionWeb extends Configuracion implements Serializable {

    private static final long serialVersionUID = 4744377178698759581L;

    private Set<PiezaConfiguracionWeb> piezasConfiguracion;

    public Set<PiezaConfiguracionWeb> getPiezasConfiguracion() {
        return piezasConfiguracion;
    }

    public void setPiezasConfiguracion(Set<PiezaConfiguracionWeb> piezasConfiguracionWeb) {
        this.piezasConfiguracion = piezasConfiguracionWeb;
    }

}
