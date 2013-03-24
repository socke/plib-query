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

/**
 * Capsulates the marshalling and unmarshalling of XML files. You can use it for any type of JAXB
 * annotated files as this is a generic un-/marshaller.
 * 
 */
public class XMLMarshallerImpl implements XMLMarshaller {

    /** Logger instance */
    static final Logger LOGGER = Logger.getLogger(XMLMarshallerImpl.class);

    /**
     * Unmarshalls the given xmlString. Note that we use {@link StreamSource} with a
     * {@link StringReader} and then unmarshalling that with jaxb. The big benefit is that we do not
     * need an @XMLRootElement annotation on the xml file which will normally not be generated.
     * Great is that we do not need casting. 
     * 
     * @{inheritDoc}
     */
    @Override
    public <T> T unmarshallXML(String xmlString, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Source xmlSource = new StreamSource(new java.io.StringReader(xmlString));
            JAXBElement<T> queryElement = jaxbUnmarshaller.unmarshal(xmlSource, clazz);
            T query = queryElement.getValue();
            LOGGER.info(query);
            return query;
        } catch (JAXBException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error during unmarshalling of XML.", e.getCause());
            // TODO create error xml file?!
        }
    }

    /** 
     * @{inheritDoc}
     */
    @Override
    public <T> String marshall(T catalogue) {
        JAXBContext context;
        StringWriter writer = new StringWriter();
        try {
            context = JAXBContext.newInstance(catalogue.getClass());
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(catalogue, writer);

        } catch (JAXBException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error during unmarshalling of XML.", e.getCause());
            // TODO create error xml file?!
        }
        return writer.toString();
    }
}
