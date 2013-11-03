package de.feu.plib.dao.procedures;


import de.feu.plib.dao.procedures.types.PropStringObjT;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Tests the GetObjString Procedure class.
 * Assures that the values from an item can be read from the oracle database.
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
    public void shouldReturnTwoEntriesWithCorrectIrdis() {

        GetObjString getObjString = new GetObjString(ds);
        List<PropStringObjT> resultList = getObjString.execute("EXT_300000001");

        assertEquals(2, resultList.size());
        assertEquals("0173-1#02-AAA762#1", resultList.get(0).getIrdi());
        assertEquals("0173-1#02-AAB011#1", resultList.get(1).getIrdi());

    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
}

