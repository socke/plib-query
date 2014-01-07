package de.feu.plib.webservice;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.xml.ws.Endpoint;

/**
 * TODO document class QueryServer
 */
public class QueryServer {
    /** Logger instance */
    static final Logger LOGGER = Logger.getLogger(StringServer.class);

    public static void main(String args[]) {
        LOGGER.info("Start query web service");
        QuerySOAPService queryService = new QuerySOAPService();
        Endpoint endpoint = Endpoint.publish(
                "http://localhost:8088/soap/query", queryService);
        LOGGER.info("Web service started");
        JOptionPane.showMessageDialog(null, "Server beenden");
        endpoint.stop();
        LOGGER.info("Web service started");
    }
}
