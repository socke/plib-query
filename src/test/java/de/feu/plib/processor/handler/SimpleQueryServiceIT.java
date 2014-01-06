package de.feu.plib.processor.handler;

import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.query.QueryType;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * An Integration test which tests {@link SimpleQueryService}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans_for_tests.xml"})
public class SimpleQueryServiceIT {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(SimpleQueryServiceIT.class);

    @Autowired
    private SimpleQueryService simpleQueryService;

    @Test
    public void testLoadData() throws Exception {
        QueryType query = new QueryType();
        query.setClassRef("0173-1#01-BAD803#2");
        EnrichedQuery enrichedQuery = new EnrichedQuery(query);
        simpleQueryService.setEnrichedQuery(enrichedQuery);
        CatalogueType catalogue = simpleQueryService.loadDataWithIRDIOnly();

        assertEquals(2, catalogue.getItem().size());

        List<ItemType> items = catalogue.getItem();
        LOGGER.info("items : " + items);

        assertEquals("0173-1#02-AAA762#1", items.get(0).getPropertyValue().get(0).getPropertyRef());

    }
}
