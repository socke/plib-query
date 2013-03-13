package de.feu.plib.webservice.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

/**
 * This is the Query Webservice based on REST.
 * The basepath is /ws which means in case of having the service locally on port 8080:
 * http://localhost:8080/plib-characteristic-data/rest/ws/query/
 * Note that in our case we have also /rest as path part as this is configured in web.xml for Jersey 
 * servlet mapping. 
 * 
 * Send a post request to this URI with the XML file as POST payload.
 */
@Path("/ws")
public class QueryService {
	
	/** Logger instance */
	private static final Logger LOGGER = Logger.getLogger(QueryService.class);
	
    /**
     * Entry point which takes the xml string and after that starts the processing of the xml.
     * The XML must be according to ISO/TS 29002-31 - query.xsd schema. 
     * The result is an XML file according to ISO/TS 29002-10 - catalogue.xsd where item is holding the items
     * 
     * @param queryXML contains the query xml file 
     * @return The XML file holding the result of the query. Holding items. 
     */
	@POST  
    @Path("/query")  
    @Consumes("application/xml")  
    @Produces(MediaType.APPLICATION_XML)  
    public String query(String queryXML) {  
		LOGGER.info("Incoming query XML content :" + queryXML);  
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey </hello>";
	} 
}
