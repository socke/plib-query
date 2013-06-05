package de.feu.plib.dao;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.business.analyser.Irdi;
import de.feu.plib.business.analyser.QueryKind;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.query.QueryType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Integration Test of PLIB Dao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans_for_tests.xml"})
public class PlibDaoIT {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(PlibDaoIT.class);

    @Autowired
    PlibDao plib;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLoadObjectsFrom() throws Exception {
    }

    @Test
    public void shouldReturnTrueWithExistingIRDI() {
        Irdi irdi = createMultiListTestIrdi();
        assertTrue(plib.doObjectsExistsWithThis(irdi));
    }

    @Test
    public void shouldReturnFalseWithIRDINotinDB() {
        Irdi irdi = createNonExistingTestIrdi();
        assertFalse(plib.doObjectsExistsWithThis(irdi));
    }

    @Test
    public void shouldReturnFalseWithEmptyIRDI() {
        Irdi irdi = new Irdi() {
            @Override
            public String getIrdi() {
                return "";
            }
        };
        assertFalse(plib.doObjectsExistsWithThis(irdi));
    }

    @Test
    public void testGetNumberOfObjectsOfIrdiExistingIrdi() {
        Irdi irdi = createMultiListTestIrdi();
        assertEquals(8, plib.getNumberOfObjectsOfIrdi(irdi));
    }

    @Test
    public void testGetNumberOfObjectsOfIrdiNotExistingIrdi() {
        Irdi irdi = createNonExistingTestIrdi();
        assertEquals(0, plib.getNumberOfObjectsOfIrdi(irdi));
    }

    @Test
    public void testThatWhenReadExternalProductIdsByIrdiMustReturnSome() throws Exception {
        Irdi irdi = createMultiListTestIrdi();
        List<String> productIds = plib.readExternalProductIdsBy(irdi);
        assertNotNull(productIds);
        assertEquals(8, productIds.size());
    }

    /**
     * Currently there are two instances in the database, seems that these are duplicates but not sure.
     * TODO: check why there are two instances in DB and correct testcase then. This is a dictionary issue.
     */
    @Test
    public void shouldReturnOneTestExternalIdWithTestIrdi() {
        Irdi irdi = createSkalpellIrdi();
        List<String> externalProductIds = plib.readExternalProductIdsBy(irdi);
        assertNotNull(externalProductIds);
        assertEquals(2, externalProductIds.size());
    }

    /**
     * This is a bigger integration test.
     * <ul>
     *     <li>First create an irdi instance</li>
     *     <li>create an enriched query</li>
     *     <li>Then load the objects from the database with its properties</li>
     *     <li>There should be the same number ob objects than with the previous check</li>
     * </ul>
     */
    @Test
    public void shouldReturnOneInstanceOfSkalpellWithTwoProperties() {
        Irdi skalpellIrdi = createSkalpellIrdi();

        List<String> externalProductIds = plib.readExternalProductIdsBy(skalpellIrdi);

        EnrichedQuery query = createEnrichedQueryFrom(skalpellIrdi);
        query.setType(QueryKind.SIMPLE);
        CatalogueType catalogueType = plib.loadObjectsFrom(query);
        List<ItemType> itemTypes = catalogueType.getItem();
        assertEquals(externalProductIds.size(), itemTypes.size());
        assertEquals(2, itemTypes.get(0).getPropertyValue().size());
    }

    @Test
    public void testLoadPropertiesByExternalIds() {
        List<String> externalIds = new ArrayList<String>();
        externalIds.add("300000001");
        List<PropertyValueType> valueTypeList = plib.loadStringPropertiesByExternalIds(externalIds);
        LOGGER.info(valueTypeList);
        assertEquals(2, valueTypeList.size());
    }

    private EnrichedQuery createEnrichedQueryFrom(Irdi skalpellIrdi) {
        QueryType queryType = new QueryType();
        queryType.setClassRef(skalpellIrdi.getIrdi());
        return new EnrichedQuery(queryType);
    }

    private Irdi createNonExistingTestIrdi() {
        return new Irdi() {
            @Override
            public String getIrdi() {
                return "0141-1#01-xxx#1";
            }
        };
    }

    private Irdi createMultiListTestIrdi() {
        return new Irdi() {
            @Override
            public String getIrdi() {
                return "0141-1#01-UKU1#1";
            }
        };
    }

    /**
     * Creates the irdi of an item which was created by me as testdata.
     * It is a Skalpell (PREFERRED_NAME of class in DB)
     * @return the irdi of the test item skalpell.
     */
    private Irdi createSkalpellIrdi() {
        return new Irdi() {
            @Override
            public String getIrdi() {
                return "0173-1#01-BAD803#2";
            }
        };
    }
}
