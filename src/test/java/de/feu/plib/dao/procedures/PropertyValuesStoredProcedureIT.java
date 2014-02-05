package de.feu.plib.dao.procedures;

/**
 * This is an quick integration test to check how we can call stored procedures.
 * The result is that it works, but we know that only by checking a existing external ID as IN-Parameter and
 * a not existing.
 * With the existing parameter, we have a green test, but no result coming back from database. The reason for that is
 * simple, the HILF_GET_PROP_VAL_STRING procedure creates an output which looks as follows:
 * LIST_STRING_PROPS 1. Stelle
 * Property-IRDI :  val : Euro
 * LIST_STRING_PROPS 2. Stelle
 * Property-IRDI :  val : 2008-12-31
 * LIST_STRING_PROPS 3. Stelle
 * Property-IRDI :  val : 170202
 * LIST_STRING_PROPS 4. Stelle
 * Property-IRDI :  val : 24-31-01-01
 * LIST_STRING_PROPS 5. Stelle
 * Property-IRDI :  val : Rundolf-75
 * LIST_STRING_PROPS 6. Stelle
 * Property-IRDI :  val : 2008-05-01
 *
 * The output was created with DBMS_OUTPUT.PUT_LINE commands in oracle database, thus we do not have a return table
 * or type which we can access.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
public class PropertyValuesStoredProcedureIT {
    @Autowired
    javax.sql.DataSource ds;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testShowsThatWeHaveThatinDB() {

        PropertyValuesStoredProcedure propertyValuesStoredProcedure = new PropertyValuesStoredProcedure(ds);
        propertyValuesStoredProcedure.execute("EXT_50766884");

    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowExceptionAsExtIDIsNotInDB() {

        PropertyValuesStoredProcedure propertyValuesStoredProcedure = new PropertyValuesStoredProcedure(ds);
        propertyValuesStoredProcedure.execute("EXT_555");

    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

}
