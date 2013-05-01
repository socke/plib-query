package de.feu.plib.dao.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class TestProcedure extends StoredProcedure {

    private static final String SQL = "GET_DO_TABLE";

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(TestProcedure.class);

	public TestProcedure(DataSource ds) {
		super(ds, SQL);
		declareParameter(new SqlOutParameter("loc_do_table", Types.VARCHAR));
		declareParameter(new SqlOutParameter("message", Types.VARCHAR));
		declareParameter(new SqlOutParameter("anz_dse", Types.NUMERIC));
		declareParameter(new SqlParameter("property_id", Types.NUMERIC));
		declareParameter(new SqlParameter("is_cvp", Types.NUMERIC));
		//setFunction(true);// you must set this as it distinguishes it from a sproc
		compile();
	}

	public String execute(Long propertyId, Long is_cvp) {
		Map in = new HashMap();
		in.put("property_id", propertyId);
        in.put("is_cvp", is_cvp);
		Map out = execute(in);
		if (!out.isEmpty())
			return out.get("message").toString();
		else
			return null;
	}
}
