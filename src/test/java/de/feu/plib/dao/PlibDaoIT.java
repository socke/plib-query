package de.feu.plib.dao;

import de.feu.plib.dao.procedures.types.PropStringObjT;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.processor.analyser.Irdi;
import de.feu.plib.processor.analyser.QueryKind;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.query.QueryType;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

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
    private PlibDao plib;

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
     */
    @Test
    public void shouldReturnOneTestExternalIdWithTestIrdi() {
        Irdi irdi = createSkalpellIrdi();
        List<String> externalProductIds = plib.readExternalProductIdsBy(irdi);
        assertNotNull(externalProductIds);
        assertEquals(2, externalProductIds.size());
        LOGGER.info("external product ids: " + externalProductIds.get(0));
        assertEquals("EXT_300000001", externalProductIds.get(0));
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
    @Ignore("loadObjectsFrom is obsolte, maybe later reimplemented")
    public void shouldReturnOneInstanceOfSkalpellWithTwoProperties() {
        Irdi skalpellIrdi = createSkalpellIrdi();

        List<String> externalProductIds = plib.readExternalProductIdsBy(skalpellIrdi);

        EnrichedQuery query = createEnrichedQueryFrom(skalpellIrdi);
        query.setType(QueryKind.SIMPLE);
        CatalogueType catalogueType = plib.loadObjectsFrom(query);
        List<ItemType> itemTypes = catalogueType.getItem();
        assertEquals(externalProductIds.size(), itemTypes.size());
        assertEquals(1, itemTypes.get(0).getPropertyValue().size());
    }

    /**
     * Test should return two items where the first one would be checked.
     * Should have two properties.
     */
    @Test
    public void testLoadPropertiesByExternalIds() {
        List<BigDecimal> externalIds = createDIIDList();
        List<List<Map<String, Object>>> valueTypeList = plib.loadStringPropertiesByExternalIds(externalIds);
        LOGGER.info("valuetype list: " + valueTypeList);
        assertEquals("should be one instance", 1, valueTypeList.size());
        assertEquals("should be two properties",2, valueTypeList.get(0).size());
        assertThatIrdiAndValueAreAvailable(valueTypeList.get(0).get(0).entrySet(), "0173-1#02-AAA762#1");
        assertThatIrdiAndValueAreAvailable(valueTypeList.get(0).get(1).entrySet(), "0173-1#02-AAB011#1");
    }


    @Test
    public void testLoadStringPropertiesBy() {
        List<List<PropStringObjT>> propStringObjList = plib.loadStringPropertiesBy(createExternalIdsList());
        LOGGER.info("propStringObjList list: " + propStringObjList);
        assertEquals("should be one instance", 1, propStringObjList.size());
        assertEquals("should be two properties",2, propStringObjList.get(0).size());

    }

    /**
     * Load data type and unit for skalpell length property which should be mm and mm as well.
     * Property id: 300903090000033914 and 300903090000034450
     */
    @Test
    public void testLoadDataTypeAndUnitForAPropertyById() {
        List<Map<String, Object>> propertyTypeAndUnit = plib.loadTypeAndUnitOfPropertyBy("300903090000033914");
        LOGGER.info("property size: " + propertyTypeAndUnit.size());
        assertTrue(propertyTypeAndUnit.size() == 1);

        assertThatUnitAndSubTypeAreAvailable(propertyTypeAndUnit);

    }

    private List<String> createExternalIdsList() {
        List<String> externalIds = new ArrayList<String>();
        String exampleId = new String("EXT_300000001");

        externalIds.add(exampleId);
        return externalIds;
    }

    private List<BigDecimal> createDIIDList() {
        List<BigDecimal> externalIds = new ArrayList<BigDecimal>();
        BigDecimal bigDecimal = new BigDecimal("300000001");

        externalIds.add(bigDecimal);
        return externalIds;
    }

    private void assertThatIrdiAndValueAreAvailable(Set<Map.Entry<String, Object>> entrySet, String knownIRDI) {

        Set<Map.Entry<String, Object>> entries = entrySet;
        boolean irdiFound = false;
        boolean valueFound = false;
        for (Map.Entry<String, Object> entry : entries) {
            LOGGER.info("key of property: " + entry.getKey());
            LOGGER.info("value of property: " + entry.getValue());
            if ("IRDI".equals(entry.getKey()) && null != entry.getValue() && !"null".equals(entry.getValue())) {
                irdiFound = true;
                assertEquals(knownIRDI, entry.getValue());
            }
            if ("VALUE".equals(entry.getKey()) && null != entry.getValue() && !"null".equals(entry.getValue())) {
                valueFound = true;
            }

        }
        assertTrue(irdiFound && valueFound);
    }


    private void assertThatUnitAndSubTypeAreAvailable(List<Map<String, Object>> propertyTypeAndUnit) {
        Set<Map.Entry<String,Object>> entries = propertyTypeAndUnit.get(0).entrySet();

        /*
        * we need to assure that we have the unit (in this case SYMBOL, e.g. mm or m or cm)
        * in the values as well as the type of the value (in this case real_measure_type)
        */
        String property_unit_symbol = "SYMBOL";
        String property_type = "SUB_TYPE";
        boolean unitfound = false;
        boolean typefound = false;
        for (Map.Entry<String, Object> entry : entries) {
            LOGGER.info("property key: " + entry.getKey());
            LOGGER.info("property value: " + entry.getValue());
            if ("SYMBOL".equals(entry.getKey()) && null != entry.getValue() && !"null".equals(entry.getValue())) {
                unitfound = true;
            }
            if ("SUB_TYPE".equals(entry.getKey()) && null != entry.getValue() && !"null".equals(entry.getValue())) {
                typefound = true;
            }
        }
        assertTrue(unitfound && typefound);
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
