package de.feu.plib.webservice;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

public class StringServer {

	/** Logger instance */
	static final Logger LOGGER = Logger.getLogger(StringServer.class);
	
	public static void main(String args[]) {
		LOGGER.info("Start web service");
		StringReturner server = new StringReturner();
		Endpoint endpoint = Endpoint.publish(
				"http://localhost:8088/StringServer", server);
		LOGGER.info("Web service started");
		JOptionPane.showMessageDialog( null, "Server beenden" );
	    endpoint.stop();
	    LOGGER.info("Web service started");
	}

}
