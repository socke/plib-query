package de.feu.plib.dao.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import de.feu.plib.dao.procedures.types.PropStringNtt;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class TestProcedure extends StoredProcedure {

    private static final String SQL = "PACK_PROPERTY.GET_PROP_VALS_STRING";

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(TestProcedure.class);

	public TestProcedure(DataSource ds) {
		super(ds, SQL);
		declareParameter(new SqlParameter("EXT_PROD_ID", OracleTypes.VARCHAR));

		declareParameter(new SqlOutParameter("OUTTABLE_STRING", OracleTypes.STRUCT, "PACK_PROPERTY.PROP_STRING_T", new SqlReturnSqlData(PropStringNtt.class)));
		//declareParameter(new SqlOutParameter("OUTTABLE_STRING", OracleTypes.ARRAY));

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

    public String execute(String propertyId) {
		Map in = new HashMap();
		in.put("EXT_PROD_ID", propertyId);
        //in.put("is_cvp", is_cvp);
		Map out = execute(in);
		if (!out.isEmpty())
			return out.get("OUTTABLE_STRING").toString();
		else
			return null;
	}
}
