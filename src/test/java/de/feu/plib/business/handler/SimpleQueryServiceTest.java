package de.feu.plib.business.handler;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.query.QueryType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * TODO: document file
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans_for_tests.xml"})
public class SimpleQueryServiceTest {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(SimpleQueryServiceTest.class);

    @Autowired
    private SimpleQueryService simpleQueryService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoadData() throws Exception {
        QueryType query = new QueryType();
        query.setClassRef("0173-1#01-BAD803#2");
        EnrichedQuery enrichedQuery = new EnrichedQuery(query);
        simpleQueryService.setEnrichedQuery(enrichedQuery);
        CatalogueType catalogue = simpleQueryService.loadData();

        assertEquals(2, catalogue.getItem().size());

        List<ItemType> items = catalogue.getItem();
        LOGGER.info("items : " + items);

        assertEquals("0173-1#02-AAA762#1", items.get(0).getPropertyValue().get(0).getPropertyRef());

        //assertEquals(new ItemType(), items.get(0));

    }
}
