//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.02 at 09:55:09 AM CEST 
//


package de.feu.plib.xml.identifier;

import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.feu.plib.xml.identifier package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IRDI_QNAME = new QName("urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier", "IRDI");
    private final static QName _IRDIList_QNAME = new QName("urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier", "IRDI_list");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.feu.plib.xml.identifier
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IRDISequenceType }
     * 
     */
    public IRDISequenceType createIRDISequenceType() {
        return new IRDISequenceType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier", name = "IRDI")
    public JAXBElement<String> createIRDI(String value) {
        return new JAXBElement<String>(_IRDI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:iso:std:iso:ts:29002:-5:ed-1:tech:xml-schema:identifier", name = "IRDI_list")
    public JAXBElement<List<String>> createIRDIList(List<String> value) {
        return new JAXBElement<List<String>>(_IRDIList_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

}
