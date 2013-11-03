package de.feu.plib.dao.procedures;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * This is a test procedure, which was for evaluating purposes only!!!
 */
public class TestProcedure extends StoredProcedure {

    private static final String SQL = "PACK_PROPERTY.GET_OBJ_STRING";

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(TestProcedure.class);

	public TestProcedure(DataSource ds) {
		super(ds, SQL);
		declareParameter(new SqlParameter("EXT_PROD_ID", OracleTypes.VARCHAR));

		declareParameter(new SqlOutParameter("OUTTBL_OBJ_STRING", OracleTypes.ARRAY, "PROP_STRING_OBJ_NTT", new SqlReturnType() {
            @Override
            public Object getTypeValue(CallableStatement callableStatement, int paramIndex, int sqlType, String typeName) throws SQLException {
                ARRAY array = (ARRAY) callableStatement.getObject(paramIndex);

                Object[] rows = (Object[])array.getArray();

                for(Object row : rows){
                    Object[] cols = ((oracle.sql.STRUCT)row).getAttributes();
                    for (Object col : cols) {
                        System.out.print(col + " ");
                    }
                    System.out.println();

                }

                return "construct your data structure above and return here";
            }
        }));
		//declareParameter(new SqlOutParameter("OUTTABLE_STRING", OracleTypes.ARRAY));

		//setFunction(true);// you must set this as it distinguishes it from a sproc
		compile();
	}

	public String execute(String propertyId, Long is_cvp) {
		Map in = new HashMap();
		in.put("EXT_PROD_ID", propertyId);
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
		//String out = execute(in);
		//String out = execute(in);
		if (!out.isEmpty())
			return out.get("OUTTBL_OBJ_STRING").toString();
		else
			return null;
	}
}
