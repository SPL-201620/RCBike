package co.rcbike.configurador_bici.model;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfiguracionWeb extends Configuracion implements Serializable {

	private static final long serialVersionUID = 4096409486896497757L;
	
	private Set<PiezaWeb> piezasWeb;

	public Set<PiezaWeb> getPiezasWeb() {
		return piezasWeb;
	}

	public void setPiezasWeb(Set<PiezaWeb> piezasWeb) {
		this.piezasWeb = piezasWeb;
	}
    
 }
