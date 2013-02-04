package de.feu.plib;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: stefan
 * Date: 27.01.13
 * Time: 21:30
 * To change this template use File | Settings | File Templates.
 */
public class TestServlet extends HttpServlet {
	
	/** Logger instance */
	static final Logger LOGGER = Logger.getLogger(TestServlet.class);
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String yourName = request.getParameter("nachname");
        LOGGER.info("doPost called");
        out.println("Hello " + yourName);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
