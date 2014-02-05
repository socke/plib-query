package de.feu.plib.processor;

import de.feu.plib.xml.AbstractXMLTest;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.query.QueryType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Integration test-class which tests the QueryProcessor.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans_for_tests.xml"})
public class QueryProcessorIT extends AbstractXMLTest {

    @Autowired
    private QueryProcessor queryProcessor;

    @Before
    public void setUp() {
        super.setUp();
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void shouldBeSimpleQueryWithOnlyIrdi() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/simple_query_irdi.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
    }

    @Test
    public void shouldBeSimpleQueryWithTwoProperties() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/simple_query_irdi.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
        CatalogueType catalogueType = queryProcessor.filter(queryType);
        assertEquals(2, catalogueType.getItem().size());
        assertEquals(2, catalogueType.getItem().get(0).getPropertyValue().size());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithIllegalIrdi() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/simple_query_illegal_irdi.xml"), QueryType.class);
        queryProcessor.isSimpleQuery(queryType);
    }

    @Test
    public void shouldBeSimpleQueryWithIrdiAndOneProperty() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/simple_query_projection_one_property.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
    }

    @Test
    public void shouldBeSimpleQueryWithIrdiAndMultipleProperties() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/simple_query_projection_multiple_properties.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
        assertEquals(2, queryType.getPropertyRef().size());
    }

    @Test
    public void shouldBeSimpleQueryWithGivenItem() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/simple_query_validation.xml"), QueryType.class);
        assertTrue(queryProcessor.isSimpleQuery(queryType));
    }

    @Test
    public void shouldBeParametricQueryWithRangeExpression() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/parametric_query_range.xml"), QueryType.class);
        assertTrue(queryProcessor.isParametricQuery(queryType));
        assertFalse(queryProcessor.isSimpleQuery(queryType));
    }

    @Test
    public void shouldBeParametricQueryWithProjectionAndANDExpression() throws Exception {
        QueryType queryType = marshaller.unmarshallXML(readXMLFrom("/de/feu/plib/xml/parametric_query_projection_range_and.xml"), QueryType.class);
        assertTrue(queryProcessor.isParametricQuery(queryType));
        assertFalse(queryProcessor.isSimpleQuery(queryType));
        assertEquals(1, queryType.getPropertyRef().size()); // one property selected
        assertTrue(queryType.getCharacteristicDataQueryExpression().get(0).getAnd() != null); // and expression is here
        assertEquals(2, queryType.getCharacteristicDataQueryExpression().get(0).getAnd().getOperand().size()); // two operands
        assertTrue(queryType.getCharacteristicDataQueryExpression().get(0).getAnd().getOperand().get(0).getRange() != null); // first operand is range
        assertTrue(queryType.getCharacteristicDataQueryExpression().get(0).getAnd().getOperand().get(1).getRange() != null); // second operand is range
    }
}
