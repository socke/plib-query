package de.feu.plib.processor;

import de.feu.plib.processor.handler.FakeSimpleQueryService;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.query.QueryType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * TODO document class QueryProcessorTest
 */
public class QueryProcessorTest {

    private QueryProcessor processor;

    @Before
    public void setUp() throws Exception {
        processor = new QueryProcessor();
        processor.setParametricQueryFilter(new FakeParametricQueryFilter());
        processor.setSimpleQueryFilter(new FakeSimpleQueryFilter());
        processor.setSimpleQueryService(new FakeSimpleQueryService());
    }

    @After
    public void tearDown() throws Exception {
        processor = null;
    }

    @Test
    public void testFilter() throws Exception {

    }

    @Test
    public void shouldBeAParametricQuery() throws Exception {

    }

    @Test
    public void shouldBeASimpleQueryWithIRDIOnly() throws Exception {
        QueryType query = new QueryType();
        query.setClassRef("classref");
        CatalogueType simpleQueryCatalogue = processor.filter(query);
        assertEquals("simplequeryloaded", simpleQueryCatalogue.getItem().get(0).getClassRef());
    }

    @Test
    public void testSetSimpleQueryFilter() throws Exception {

    }

    @Test
    public void testGetSimpleQueryFilter() throws Exception {

    }

    @Test
    public void testGetParametricQueryFilter() throws Exception {

    }

    @Test
    public void testSetParametricQueryFilter() throws Exception {

    }
}
