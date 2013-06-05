package de.feu.plib.business;

import de.feu.plib.xml.AbstractXMLTest;
import de.feu.plib.xml.XMLMarshaller;
import de.feu.plib.xml.XMLMarshallerImpl;
import de.feu.plib.xml.query.QueryType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TODO: document file
 */
public class QueryProcessorTest extends AbstractXMLTest {

    private QueryProcessor queryProcessor;

    @Before
    public void setUp() {
        super.setUp();
        queryProcessor = new QueryProcessor();
    }

    @After
    public void tearDown() {
        super.tearDown();
        queryProcessor = null;
    }

    @Test
    public void shouldBeFalseWithEmptyQuery() throws Exception {
        assertFalse(queryProcessor.isSimpleQuery(new QueryType()));
    }

    @Test
    public void shouldBeSimpleQueryWithOnlyIrdi() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithIllegalIrdi() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi_illegal.xml"), QueryType.class);
        queryProcessor.isSimpleQuery(queryType);
    }

    @Test
    public void shouldBeSimpleQueryWithIrdiAndOneProperty() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi_one_property.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
    }

    @Test
    public void shouldBeSimpleQueryWithIrdiAndMultipleProperties() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi_multiple_properties.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
        assertEquals(4, queryType.getPropertyRef().size());
    }

    @Test
    public void shouldBeSimpleQueryWithGivenItem() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_class_irdi_item.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
    }

    @Test
    public void shouldBeParametricQueryWithRangeExpression() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/query_parametric_range.xml"), QueryType.class);
        assertTrue(queryProcessor.isParametricQuery(queryType));
        assertFalse(queryProcessor.isSimpleQuery(queryType));
    }
}
