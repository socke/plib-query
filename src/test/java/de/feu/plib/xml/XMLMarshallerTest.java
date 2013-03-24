/**
 * 
 */
package de.feu.plib.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.query.QueryType;
import de.feu.plib.xml.value.BooleanValueType;

/**
 * Tests the marshalling and unmarshalling of xml files.
 */
public class XMLMarshallerTest {

    /** XML Marshaller instance under test */
    XMLMarshaller marshaller;

    /** Logger instance */
    private static final Logger LOGGER = Logger.getLogger(XMLMarshallerTest.class);

    @Test
    public void testMarshall() {
        String catalogue = marshaller.marshall(createCatalogueWithOneItem());
        LOGGER.info(catalogue);
        assertTrue(catalogue.contains("abc"));
        assertTrue(catalogue.contains("true"));
    }

    @Test
    public void testUnMarshall() {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi.xml"),
                QueryType.class);
        assertEquals("0173-1#01-AAA352#4", queryType.getClassRef());
    }

    /**
     * creates a sample catalogue for testing
     * 
     * @return the sample catalogue with one item
     */
    private CatalogueType createCatalogueWithOneItem() {
        ItemType item = new ItemType();
        item.setClassRef("abc");
        PropertyValueType propertyValueType = new PropertyValueType();

        BooleanValueType bvt = new BooleanValueType();
        bvt.setValue(true);
        propertyValueType.setBooleanValue(bvt);
        item.getPropertyValue().add(propertyValueType);
        CatalogueType catalogue = new CatalogueType();
        catalogue.getItem().add(item);

        return catalogue;
    }

    /**
     * Reads the xml file from given filenlame
     * 
     * @param filename the filename of the xml
     * @return the string content of the xml file
     */
    private String readXMLFrom(String filename) {
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

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        marshaller = new XMLMarshallerImpl();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        marshaller = null;
    }

}
