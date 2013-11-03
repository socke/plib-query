package de.feu.plib.dao.procedures;

import static org.junit.Assert.*;

import de.feu.plib.dao.procedures.types.Rectype;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
public class ObjectTypeProcedureTest {

    @Autowired javax.sql.DataSource ds;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testProcedureCall() {

        ObjectTypeProcedure sp = new ObjectTypeProcedure(ds);
        //String i = sp.execute(1l, 1l);
        Rectype rt = new Rectype();
        rt.setCol1("col1");
        rt.setCol2("col2");
        String rtreturn = sp.execute("col1", "col2");

    }
    @Test
    public void testProcedureTwoParametersCall() {

        TestProcedure sp = new TestProcedure(ds);
        Map i = sp.execute(1l, 1l);
        assertEquals("test", i);

    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
}
