package de.feu.plib.webservice;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * TODO document class Customer
 */
@XmlRootElement
public class Customer implements Serializable {
    private String name;

    private String vorname;

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
}
