package de.feu.plib.webservice.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.feu.plib.processor.QueryPipe;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.feu.plib.xml.XMLMarshaller;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.query.QueryType;

/**
 * This is the Query Webservice based on REST. The basepath is /ws which means in case of having the
 * service locally on port 8080: http://localhost:8080/plib-characteristic-data/rest/ws/query/ Note
 * that in our case we have also /rest as path part as this is configured in web.xml for Jersey
 * servlet mapping.
 * 
 * Send a post request to this URI with the XML file as POST payload.
 */
@Path("/ws")
public class QueryRESTService {

    /** Marshaller instance used for converting xml to objects and vice versa */
    private XMLMarshaller marshaller;

    /** Query processor instance */
    private QueryPipe queryPipe;

    /** Logger instance */
    private static final Logger LOGGER = Logger.getLogger(QueryRESTService.class);

    /** Application context for receiving beans */
    private ApplicationContext context;

    /** Constructor which ensures correct instantiation of needed beans */
    public QueryRESTService() {
        this.context = initializeContext();
        this.marshaller = getMarshaller();
        this.queryPipe = getQueryPipe();
    }

    /**
     * Entry point which takes the xml string and after that starts the processing of the xml. The
     * XML must be according to ISO/TS 29002-31 - query.xsd schema. The result is an XML file
     * according to ISO/TS 29002-10 - catalogue.xsd where item is holding the items
     * 
     * As this is the main entry point, this is what we are doing:
     * <ol>
     * <li>Read the incoming xml and unmarshall it to a {@link QueryType} Object.</li>
     * <li>Pass that object to the QueryPipe, it decides what kind of query it is and who is responsible for
     * further handling</li>
     * <li>After returning from the processor the method returns the catalogue xml file holding all items as
     * response to the query</li>
     * </ol>
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
        QueryType queryType = unmarshall(queryXML);
        LOGGER.info("QueryType: " + queryType);
        CatalogueType catalogue = queryPipe.filter(queryType);

        LOGGER.info("Filled Catalogue: " + catalogue);
        String marshalledCatalogue = marshall(catalogue);

        LOGGER.info("Marshalled catalogue: " + marshalledCatalogue);
        return marshalledCatalogue;
    }

    private String marshall(CatalogueType catalogue) {
        String marshalledCatalogue;
        try {
            marshalledCatalogue = marshaller.marshall(catalogue);
        } catch (RuntimeException e) {
            LOGGER.error("Error occured during marshalling of xml: " + e);
            return "";
        }
        return marshalledCatalogue;
    }

    private QueryType unmarshall(String queryXML) {
        QueryType queryType;
        try {
            queryType = marshaller.unmarshallXML(queryXML, QueryType.class);
        } catch (RuntimeException e) {
            LOGGER.error("Error occured during unmarshalling of xml: " + e);
            // return empty QueryType in exception case
            // TODO later think about better error handling
            return new QueryType();
        }
        return queryType;
    }

    private XMLMarshaller getMarshaller() {
        XMLMarshaller marshaller = (XMLMarshaller) context.getBean("xml.marshaller");
        return marshaller;
    }

    private ClassPathXmlApplicationContext initializeContext() {
        return new ClassPathXmlApplicationContext(
                "beans.xml");
    }

    private QueryPipe getQueryPipe() {
        QueryPipe queryPipe =  (QueryPipe) context.getBean("queryPipe");
        return queryPipe;
    }

}
