import de.feu.plib.dao.PlibDao;
import de.feu.plib.dao.PlibDaoFake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO: document file
 */
public class PlibDaoTest {

    private PlibDao plib;

    @Before void setUp() throws Exception {
        plib = new PlibDaoFake();
    }

    @After
    public void tearDown() throws Exception {
        plib = null;
    }

    @Test
    public void testLoadObjectsFrom() throws Exception {

    }
}
