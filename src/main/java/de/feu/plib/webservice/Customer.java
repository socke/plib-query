package de.feu.plib.webservice;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Class is for testpurposes only. Could probably be deleted.
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
