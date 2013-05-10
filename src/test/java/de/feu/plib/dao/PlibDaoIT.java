package de.feu.plib.dao;

import de.feu.plib.business.analyser.Irdi;
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
 * Integration Test of PLIB Dao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans_for_tests.xml"})
public class PlibDaoIT {

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
        Irdi irdi = new Irdi() {
            @Override
            public String getIrdi() {
                return "0141-1#01-UKU1#1";
            }
        };
        assertTrue(plib.doObjectsExistsWithThis(irdi));
    }

    @Test
    public void shouldReturnFalseWithIRDINotinDB() {
        Irdi irdi = new Irdi() {
            @Override
            public String getIrdi() {
                return "0141-1#01-xxx#1";
            }
        };
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
        Irdi irdi = new Irdi() {
            @Override
            public String getIrdi() {
                return "0141-1#01-UKU1#1";
            }
        };
        assertEquals(8, plib.getNumberOfObjectsOfIrdi(irdi));
    }

    @Test
    public void testGetNumberOfObjectsOfIrdiNotExistingIrdi() {
        Irdi irdi = new Irdi() {
            @Override
            public String getIrdi() {
                return "0141-1#01-xxx#1";
            }
        };
        assertEquals(0, plib.getNumberOfObjectsOfIrdi(irdi));
    }
}
