package de.feu.plib.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {

	@GET
	@Path("/say/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}

//	// This method is called if TEXT_PLAIN is request
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String sayPlainTextHello() {
//		return "Hello Jersey";
//	}
//
	// This method is called if XML is request
	@GET
	@Path("/xml/{param}")
	@Produces(MediaType.APPLICATION_XML)
	public String sayXMLHello(@PathParam("param") String msg) {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + msg + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Path("/json/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayJsonHello(@PathParam("param") String msg) {
		return "{ hello : " + msg + "}";
	}
}
