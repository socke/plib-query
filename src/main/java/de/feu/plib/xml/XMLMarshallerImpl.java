/**
 * 
 */
package de.feu.plib.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.springframework.util.FileSystemUtils;
import org.xml.sax.SAXException;

/**
 * Capsulates the marshalling and unmarshalling of XML files. You can use it for any type of JAXB
 * annotated files as this is a generic un-/marshaller.
 * 
 */
public class XMLMarshallerImpl implements XMLMarshaller {

    /** location of the query schema file */
    public static final String QUERY_XSD = "/schema/query.xsd";

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
            jaxbUnmarshaller.setSchema(getSchema());
            JAXBElement<T> queryElement = jaxbUnmarshaller.unmarshal(xmlSource, clazz);
            T query = queryElement.getValue();
            LOGGER.info(query);
            return query;
        } catch (JAXBException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error during unmarshalling of XML.", e.getCause());
            // TODO create error xml file?!
        } catch (SAXException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error, cannot receive Schema file", e.getCause());
        } catch (URISyntaxException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error, cannot receive Schema file", e.getCause());
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
            m.setSchema(getSchema());
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(catalogue, writer);
            writer.close();

        } catch (JAXBException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error during marshalling of XML.", e.getCause());
            // TODO create error xml file?!
        } catch (SAXException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error, cannot receive Schema file", e.getCause());
        } catch (URISyntaxException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error, cannot receive Schema file", e.getCause());
        } catch (IOException e) {
            LOGGER.error(e);
            throw new RuntimeException("Error during closing of writer", e.getCause());
        }
        return writer.toString();
    }

    private Schema getSchema() throws SAXException, URISyntaxException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL schemaResource = XMLMarshallerImpl.class.getResource(QUERY_XSD);
        File schemaFile = new File(schemaResource.toURI());
        Schema schema = schemaFactory.newSchema(schemaFile);
        return schema;
    }
}
