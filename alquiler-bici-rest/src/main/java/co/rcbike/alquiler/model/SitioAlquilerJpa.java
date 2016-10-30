package co.rcbike.alquiler.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "SitioAlquiler")
@Table(name = "SitioAlquiler")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "findAllSitiosAlquiler", query = "SELECT s FROM SitioAlquiler s"),
        @NamedQuery(name = "findByCreador", query = "SELECT s FROM SitioAlquiler s WHERE s.emailCreador = :emailCreador"),
        @NamedQuery(name = "findByIdSitioAlquiler", query = "SELECT s FROM SitioAlquiler s WHERE s.id = :id"),
        @NamedQuery(name = "findByPunto", query = "SELECT s FROM SitioAlquiler s WHERE "
    			+ "s.latitud >= :latitudInicio and s.latitud <= :latitudFinal "
    			+ "and s.longitud >= :longitudInicio and s.longitud <= :longitudFinal")})
public class SitioAlquilerJpa extends SitioAlquiler implements Serializable {

	private static final long serialVersionUID = 8321387201204368892L;
	public static final String SQ_findAllSitiosAlquiler = "findAllSitiosAlquiler";
	public static final String SQ_findByCreador = "findByCreador";
	public static final String SQ_findByIdSitioAlquiler = "findByIdSitioAlquiler";
	public static final String SQ_findByPunto = "findByPunto";
    public static final String SQ_PARAM_EMAIL_CREADOR = "emailCreador";
    public static final String SQ_PARAM_ID = "id";
	public static final String SQ_PARAM_LATITUD_INICIO = "latitudInicio";
	public static final String SQ_PARAM_LATITUD_FINAL = "latitudFinal";
	public static final String SQ_PARAM_LONGITUD_INICIO = "longitudInicio";
	public static final String SQ_PARAM_LONGITUD_FINAL = "longitudFinal";
    
}
