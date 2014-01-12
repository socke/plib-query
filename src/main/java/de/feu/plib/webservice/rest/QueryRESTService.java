package de.feu.plib.webservice.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.feu.plib.processor.QueryPipe;
import de.feu.plib.xml.error.ErrorType;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.feu.plib.xml.XMLMarshaller;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.query.QueryType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * This is the Query Webservice based on REST. The basepath is /ws which means in case of having the
 * service locally on port 8080: http://localhost:8080/plib-characteristic-data/rest/ws/query/ Note
 * that in our case we have also /rest as path part as this is configured in web.xml for Jersey
 * servlet mapping.
 * <p/>
 * Send a post request to this URI with the XML file as POST payload.
 */
@Path("/ws")
public class QueryRESTService {

    /**
     * Marshaller instance used for converting xml to objects and vice versa
     */
    private XMLMarshaller marshaller;

    /**
     * Query processor instance
     */
    private QueryPipe queryPipe;

    /**
     * Logger instance
     */
    private static final Logger LOGGER = Logger.getLogger(QueryRESTService.class);

    /**
     * Application context for receiving beans
     */
    private ApplicationContext context;

    /**
     * Constructor which ensures correct instantiation of needed beans
     */
    public QueryRESTService() {
        this.context = initializeContext();
        this.marshaller = getMarshaller();
        this.queryPipe = getQueryPipe();
    }

    /**
     * Entry point which takes the xml string and after that starts the processing of the xml. The
     * XML must be according to ISO/TS 29002-31 - query.xsd schema. The result is an XML file
     * according to ISO/TS 29002-10 - catalogue.xsd where item is holding the items
     * <p/>
     * As this is the main entry point, this is what we are doing:
     * <ol>
     * <li>Read the incoming xml and unmarshall it to a {@link QueryType} Object.</li>
     * <li>Pass that object to the QueryPipe, it decides what kind of query it is and who is responsible for
     * further handling</li>
     * <li>After returning from the processor the method returns the catalogue xml file holding all items as
     * response to the query</li>
     * </ol>
     *
     * @param queryXML contains the query xml file
     * @return The XML file holding the result of the query. Holding items.
     */
    @POST
    @Path("/query")
    @Consumes("application/xml")
    @Produces(MediaType.APPLICATION_XML)
    public String query(String queryXML) {
        CatalogueType catalogue = new CatalogueType();
        QueryType queryType = new QueryType();
        String marshalledCatalogue = "";

        LOGGER.info("Incoming query XML content :" + queryXML);
        try {
            queryType = unmarshall(queryXML);
        } catch (RuntimeException e) {
            catalogue = handleUnMarshallingError(e);
        } finally {
            try {
                marshalledCatalogue = marshall(catalogue);
                return marshalledCatalogue;
            } catch (RuntimeException e) {
                handleMarshallingError(e);
            }
        }
        LOGGER.info("QueryType: " + queryType);
        catalogue = queryPipe.filter(queryType);

        LOGGER.info("Filled Catalogue: " + catalogue);
        try {
            marshalledCatalogue = marshall(catalogue);
        } catch (RuntimeException e) {
            handleMarshallingError(e);
        }
        LOGGER.info("Marshalled catalogue: " + marshalledCatalogue);
        return marshalledCatalogue;
    }

    private CatalogueType handleMarshallingError(RuntimeException e) {
        CatalogueType catalogue;
        LOGGER.error("Error occured during marshalling of xml: " + e);
        catalogue = new CatalogueType();
        ErrorType errorType = new ErrorType();
        errorType.setShortErrorMessage("Error occured during marshalling of xml. Probably a XSD validation error?!");
        String stackTrace = printStrackTraceToString(e);
        errorType.setLongErrorMessage(stackTrace);
        catalogue.setError(errorType);
        return catalogue;
    }

    private String printStrackTraceToString(RuntimeException e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        return writer.toString();
    }

    private CatalogueType handleUnMarshallingError(RuntimeException e) {
        CatalogueType catalogue;
        LOGGER.error("Error occured during unmarshalling of xml: " + e);
        catalogue = new CatalogueType();
        ErrorType errorType = new ErrorType();
        errorType.setShortErrorMessage("Error occured during unmarshalling of xml");
        String stackTrace = printStrackTraceToString(e);
        errorType.setLongErrorMessage(stackTrace);
        catalogue.setError(errorType);
        return catalogue;
    }

    private String marshall(CatalogueType catalogue) {
        return marshaller.marshall(catalogue);
    }

    private QueryType unmarshall(String queryXML) {
        return marshaller.unmarshallXML(queryXML, QueryType.class);
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
        QueryPipe queryPipe = (QueryPipe) context.getBean("queryPipe");
        return queryPipe;
    }

}
