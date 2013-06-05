/**
 * 
 */
package de.feu.plib.xml;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.query.QueryType;
import de.feu.plib.xml.value.BooleanValueType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the marshalling and unmarshalling of xml files.
 */
public class XMLMarshallerTest extends AbstractXMLTest {

    /** XML Marshaller instance under test */
    XMLMarshaller marshaller;

    /** Logger instance */
    private static final Logger LOGGER = Logger.getLogger(XMLMarshallerTest.class);

    @Test
    public void testMarshallingWithValidArbitraryCatalogue() {
        String catalogue = marshaller.marshall(createCatalogueWithOneItem());
        LOGGER.info(catalogue);
        assertTrue(catalogue.contains("0173-1#01-AAA352#4"));
        assertTrue(catalogue.contains("true"));
    }

    @Test
    public void testUnMarshallingWithValidArbitraryClassIrdi() {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi.xml"),
                QueryType.class);
        assertEquals("0173-1#01-AAA352#4", queryType.getClassRef());
    }

    /**
     * TODO create specific runtime exceptions
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionDuringXMLValidationWithIllegalIrdi() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi_illegal.xml"),
                QueryType.class);
    }

    /**
     * creates a sample catalogue for testing
     * 
     * @return the sample catalogue with one item
     */
    private CatalogueType createCatalogueWithOneItem() {
        ItemType item = new ItemType();
        item.setClassRef("0173-1#01-AAA352#4");
        PropertyValueType propertyValueType = new PropertyValueType();

        BooleanValueType bvt = new BooleanValueType();
        bvt.setValue(true);
        propertyValueType.setBooleanValue(bvt);
        propertyValueType.setPropertyRef("0173-1#01-A35AA2#4");
        item.getPropertyValue().add(propertyValueType);
        CatalogueType catalogue = new CatalogueType();
        catalogue.getItem().add(item);

        return catalogue;
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() {
        marshaller = new XMLMarshallerImpl();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() {
        marshaller = null;
    }

}
