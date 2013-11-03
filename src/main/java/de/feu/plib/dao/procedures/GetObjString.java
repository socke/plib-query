package de.feu.plib.dao.procedures;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class GetObjString represents the StoredProcedure GET_OBJ_STRING
 * of the Oracle package PACK_PROPERTY.
 */
public class GetObjString extends StoredProcedure {

    /**
     * The oracle procedure name. Note the package definition (PACK_PROPERTY) which is needed!!!
     */
    private static final String PROCEDURE_NAME = "PACK_PROPERTY.GET_OBJ_STRING";

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(TestProcedure.class);

    /**
     * Constructor declares the parameters
     * @param dataSource the data source must be passed
     */
    public GetObjString(DataSource dataSource) {
        super(dataSource, PROCEDURE_NAME);
        declareParameter(new SqlParameter("EXT_PROD_ID", OracleTypes.VARCHAR));

        declareParameter(new SqlOutParameter("OUTTBL_OBJ_STRING", OracleTypes.ARRAY, "PROP_STRING_OBJ_NTT", createReturnType()));

        compile();
    }

    /**
     * Creates the return type
     * @return
     */
    private SqlReturnType createReturnType() {
        return new SqlReturnType() {
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
        };
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

