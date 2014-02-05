package de.feu.plib.xml;

import de.feu.plib.xml.catalogue.CatalogueType;

/**
 * Capsulates the marshalling and unmarshalling of XML files. You can use it for any type of JAXB
 * annotated files as this is a generic un-/marshaller.
 * 
 */
public interface XMLMarshaller {

    /**
     * <p>
     * This method is generic, so can be used to convert an arbitrary XML file given as string to a
     * corresponding class. You have to pass that expected class type as parameter. This is
     * necessary as we cannot grab the concrete type from a generic type. The compiler throws the
     * type away.</p>
     * 
     * <p><strong>Usage example:</strong></p>
     * 
     * <pre>
     * QueryType queryType = marshaller.unmarshallXML(&quot;&lt;query&gt;...&lt;/query&gt;&quot;, QueryType.class);
     * </pre>
     * 
     * @param <T> the type of the class you expect to get returned
     * 
     * @param xmlString the given string which holds the xml
     * @param clazz the class of the expected type you assert you get returned by your xml.
     * 
     */
    public <T> T unmarshallXML(String xmlString, Class<T> clazz);

    /**
     * Marshalls the given CatalogueType parameter to an corresponding xml.
     * 
     * <p><strong>Usage example:</strong></p>
     * <pre>
     * String catalogue = marshaller.marshall(new CatalogueItem());
     * </pre>
     *
     * @param catalogue type to convert to xml
     * @return the string holding the converted xml
     */
    public String marshall(CatalogueType catalogue);

}