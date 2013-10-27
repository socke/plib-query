package de.feu.plib.xml;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Abstract test class. Extend from this class to get functionality to read XML files for your test.
 */
public class AbstractXMLTest {

    protected XMLMarshaller marshaller;

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(AbstractXMLTest.class);


    @Before
    public void setUp() {
        marshaller = new XMLMarshallerImpl();
    }

    @After
    public void tearDown() {
        marshaller = null;
    }

    /**
     * Reads the xml file from given filename
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
