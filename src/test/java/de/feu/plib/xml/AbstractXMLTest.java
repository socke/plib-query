package de.feu.plib.xml;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Abstract test class. Extend from this class to get functionality to read XML files for your test.
 */
public class AbstractXMLTest {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(AbstractXMLTest.class);
    /**
     * Reads the xml file from given filenlame
     *
     * @param filename the filename of the xml
     * @return the string content of the xml file
     */
    protected String readXMLFrom(String filename) {
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();

        try {
            String currentLine;

            InputStream is = XMLMarshallerImpl.class.getResourceAsStream(filename);
            br = new BufferedReader(new InputStreamReader(is));

            while ((currentLine = br.readLine()) != null) {
                sb.append(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                LOGGER.info("Exception occured during reading file: " + ex);
            }
        }

        return sb.toString();
    }
}
