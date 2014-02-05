package de.feu.plib.dao;

import de.feu.plib.processor.analyser.Irdi;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * TODO create unit test - integration test already exists
 */

public class PlibDaoImplTest {

    private PlibDaoImpl plib;

    @Before
    public void setUp() throws Exception {
        plib = new PlibDaoImpl();

    }
    @After
    public void tearDown() throws Exception {
        plib = null;
    }

    @Test
    public void testReadExternalProductIdsBy() throws Exception {
        Irdi irdi = getIrdi();
    }

    private Irdi getIrdi() {
        return new Irdi() {
            @Override
            public String getIrdi() {
                return "IRDI-test";
            }
        };
    }

    public void testDoObjectsExistsWithThis() throws Exception {

    }

    public void testNoObjectsExistsWithThis() throws Exception {

    }

    public void testGetNumberOfObjectsOfIrdi() throws Exception {

    }

    public void testGetCompanyNames() throws Exception {

    }

    public void testLoadObjectsFrom() throws Exception {

    }

    public void testLoadStringPropertiesByExternalIds() throws Exception {

    }

    public void testLoadNumberPropertiesByExternalIds() throws Exception {

    }

    public void testLoadTypeAndUnitOfPropertyBy() throws Exception {

    }
}
