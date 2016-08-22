package co.rcbike.alquiler.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Alquiler implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

}
