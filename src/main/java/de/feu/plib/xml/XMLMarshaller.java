/**
 * 
 */
package de.feu.plib.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.query.QueryType;

/**
 * 
 */
public class XMLMarshaller {
    
    /** Logger instance */
    static final Logger LOGGER = Logger.getLogger(XMLMarshaller.class);
    
    /**
     * Unmarshalls the given xmlString. Note that we use {@link StreamSource}
     * with a {@link StringReader} and then unmarshalling that with jaxb. The
     * big benefit is that we do not need an @XMLRootElement annotation on the
     * xml file which will normally not be generated. Great is that we do not
     * need casting.
     * 
     * @param xmlString
     *            the given string which holds the xml
     */
    public QueryType unmarshallXML(String xmlString) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(QueryType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Source xmlSource = new StreamSource(new java.io.StringReader(xmlString));
            JAXBElement<QueryType> queryElement = jaxbUnmarshaller.unmarshal(xmlSource, QueryType.class);
            QueryType query = queryElement.getValue();
            LOGGER.info(query);
            return query;
        } catch (JAXBException e) {
            throw new RuntimeException("Error during unmarshalling of XML.", e.getCause());
        }
    }

    public String marshall(CatalogueType catalogue) {
        JAXBContext context;
        StringWriter writer = new StringWriter();
        try {
            context = JAXBContext.newInstance(CatalogueType.class);
            Marshaller m = context.createMarshaller();
            
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(catalogue, writer);

        } catch (JAXBException e) {
            LOGGER.error(e);
        }
        return writer.toString();
    }
}
