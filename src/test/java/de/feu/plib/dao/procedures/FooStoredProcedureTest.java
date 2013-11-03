package de.feu.plib.dao.procedures;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO document class FooStoredProcedureTest
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans_for_tests.xml"})
public class FooStoredProcedureTest {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(FooStoredProcedureTest.class);

    @Autowired
    javax.sql.DataSource ds;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFooProcedure() {
        FooStoredProcedure proc = new FooStoredProcedure(ds);

        FooStoredProcedure.MyType type1 = new FooStoredProcedure.MyType();
        type1.setName("testname");
        type1.setValue("testvalue");

        FooStoredProcedure.MyType[] myTypes = {type1};

        FooStoredProcedure.MyType[] types = proc.execute(myTypes);
        LOGGER.info("types return values: " + types.toString());

    }
}
