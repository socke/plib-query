package de.feu.plib.webservice.rest;

import java.io.StringReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.query.QueryType;

/**
 * This is the Query Webservice based on REST. The basepath is /ws which means
 * in case of having the service locally on port 8080:
 * http://localhost:8080/plib-characteristic-data/rest/ws/query/ Note that in
 * our case we have also /rest as path part as this is configured in web.xml for
 * Jersey servlet mapping.
 * 
 * Send a post request to this URI with the XML file as POST payload.
 */
@Path("/ws")
public class QueryService {

    /** Logger instance */
    private static final Logger LOGGER = Logger.getLogger(QueryService.class);

    /**
     * Entry point which takes the xml string and after that starts the
     * processing of the xml. The XML must be according to ISO/TS 29002-31 -
     * query.xsd schema. The result is an XML file according to ISO/TS 29002-10
     * - catalogue.xsd where item is holding the items
     * 
     * @param queryXML
     *            contains the query xml file
     * @return The XML file holding the result of the query. Holding items.
     */
    @POST
    @Path("/query")
    @Consumes("application/xml")
    @Produces(MediaType.APPLICATION_XML)
    public String query(String queryXML) {
        LOGGER.info("Incoming query XML content :" + queryXML);
        QueryType queryType = unmarshallXML(queryXML);
        marshall();
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey </hello>";
    }

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
    private QueryType unmarshallXML(String xmlString) {
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

    private void marshall() {

        ItemType item = new ItemType();
        item.setClassRef("abc");

        // create JAXB context and instantiate marshaller
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(ItemType.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
           // JAXBElement<ItemType> jaxbElement = new JAXBElement<ItemType>(new QName(
             //       "http://www.namespace.com/schema/My", "My"), ItemType.class, item);

            m.marshal(item, System.out);

        } catch (JAXBException e) {
            // TODO Check if logging exception is enough!
            LOGGER.error(e);
        }
    }
}
