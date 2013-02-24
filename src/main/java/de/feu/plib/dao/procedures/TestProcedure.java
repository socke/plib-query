package de.feu.plib.dao.procedures;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class TestProcedure extends StoredProcedure {
	private static final String SQL = "package.function";

	public TestProcedure(DataSource ds) {
		super(ds, SQL);
		declareParameter(new SqlOutParameter("param_out", Types.NUMERIC));
		declareParameter(new SqlParameter("param_in", Types.NUMERIC));
		//setFunction(true);// you must set this as it distinguishes it from a sproc
		compile();
	}

	public String execute(Long rdsId) {
		Map in = new HashMap();
		in.put("param_in", rdsId);
		Map out = execute(in);
		if (!out.isEmpty())
			return out.get("param_out").toString();
		else
			return null;
	}
}
