package de.feu.plib.dao.procedures;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
public class TestProcedureIT {

    @Autowired javax.sql.DataSource ds;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcedureCall() {

        TestProcedure sp = new TestProcedure(ds);
        //String i = sp.execute(1l, 1l);
        String i = sp.execute("EXT_300000001");
        assertEquals("test", i);

	}
    @Test
    @Ignore
	public void testProcedureTwoParametersCall() {

        TestProcedure sp = new TestProcedure(ds);
        String i = sp.execute(1l, 1l);
        assertEquals("test", i);

	}

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
}
