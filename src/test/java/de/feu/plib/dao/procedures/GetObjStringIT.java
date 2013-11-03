package de.feu.plib.dao.procedures;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * TODO document class GetObjStringIT
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
public class GetObjStringIT {

    @Autowired
    javax.sql.DataSource ds;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testProcedureCall() {

        GetObjString getObjString = new GetObjString(ds);
        String i = getObjString.execute("EXT_300000001");
        assertEquals("construct your data structure above and return here", i);

    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
}

