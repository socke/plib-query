package de.feu.plib.dao.procedures;

import de.feu.plib.dao.procedures.types.Footype;
import de.feu.plib.dao.procedures.types.Rectype;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class ObjectTypeProcedure extends StoredProcedure {

    private static final String PROCEDURE_NAME = "FOO";
    public static final String INPARAM = "RECIN";
    public static final String OUTPARAM = "RECOUT";
    public static final String TYPE_NAME = "RECTYPE";

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(TestProcedure.class);

    public ObjectTypeProcedure(DataSource ds) {
        super(ds, PROCEDURE_NAME);
        declareParameter(new SqlParameter(INPARAM, OracleTypes.ARRAY, TYPE_NAME));

        declareParameter(new SqlOutParameter(OUTPARAM, OracleTypes.ARRAY, TYPE_NAME, new SqlReturnSqlData(Footype.class)));

        //setFunction(true);// you must set this as it distinguishes it from a sproc
        compile();
    }

    public String execute(Long propertyId, Long is_cvp) {
        Map in = new HashMap();
        in.put("EXT_PROD_ID", propertyId);
        in.put("is_cvp", is_cvp);
        Map out = execute(in);
        if (!out.isEmpty())
            return out.get("message").toString();
        else
            return null;

    }

    public String execute(String col1, String col2) {
        Map in = new HashMap();
        Rectype rt = new Rectype();
        rt.setCol1(col1);
        rt.setCol2(col2);
        in.put("RECIN", rt);
        //in.put("is_cvp", is_cvp);
        Map out = execute(in);
        if (!out.isEmpty()) {
            LOGGER.info("empty map");
            return out.get("RECOUT").toString();
        } else
            return null;
    }
}
