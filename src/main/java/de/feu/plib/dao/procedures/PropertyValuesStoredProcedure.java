package de.feu.plib.dao.procedures;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * This class calls the HILF_GET_PROP_VALS procedure.
 * However, during tests I found out that we have no real out-parameter.
 * Thus either must be changed or Queries from the procedures must be used as simple sql.
 */
public class PropertyValuesStoredProcedure extends StoredProcedure {
    private static final String PROCEDURE_NAME = "PACK_PROPERTY.HILF_GET_PROP_VALS_STRING";

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(TestProcedure.class);

    public PropertyValuesStoredProcedure(DataSource ds) {
        super(ds, PROCEDURE_NAME);
        //declareParameter(new SqlOutParameter("OBJ_ID", Types.VARCHAR));
        declareParameter(new SqlParameter("OBJ_ID", Types.VARCHAR));
        compile();
    }

    public String execute(String objectId) {
        Map in = new HashMap();
        in.put("OBJ_ID", objectId);
        Map out = execute(in);
        LOGGER.info("Output: " + out);
        if (!out.isEmpty())
            return out.get("OBJ_ID").toString();
        else
            return null;
    }
}
