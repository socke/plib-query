package de.feu.plib.dao.procedures;

import de.feu.plib.dao.procedures.types.PropStringObjT;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Class GetObjString represents the StoredProcedure GET_OBJ_STRING
 * of the Oracle package PACK_PROPERTY.
 *
 * INPUT Parameters: EXT_PROD_ID -> String
 * OUTPUT Parameters: PROP_STRING_OBJ_NTT which is a table ob PROP_STRING_OBJ_T
 */
public class GetObjString extends StoredProcedure {

    /**
     * The oracle procedure name. Note the package definition (PACK_PROPERTY) which is needed!!!
     */
    private static final String PROCEDURE_NAME = "PACK_PROPERTY.GET_OBJ_STRING";

    /**
     * Expected length of columns. Must match the entries from oracle type definition.
     */
    public static final int EXPECTED_COL_LENGTH = 7;

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(GetObjString.class);

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

                ArrayList<PropStringObjT> entries = new ArrayList<PropStringObjT>();

                for(Object row : rows){
                    Object[] cols = ((oracle.sql.STRUCT)row).getAttributes();

                    for (Object col : cols) {
                        LOGGER.debug(col + " ");
                    }

                    if (cols.length == EXPECTED_COL_LENGTH) {
                        LOGGER.trace("Expected col length - OK - now mapping values to domain object");

                        PropStringObjT propertyEntry = mapAttributes(cols);

                        entries.add(propertyEntry);
                    } else {
                        LOGGER.error("Cols length has not expected value. length is <"+ cols.length + "> expected length is <" +  EXPECTED_COL_LENGTH + ">");
                        return Collections.emptyList();
                    }
                }
                return entries;
            }

            private PropStringObjT mapAttributes(Object[] cols) {
                PropStringObjT propertyEntry = new PropStringObjT();

                if (null != cols[0]) {
                    propertyEntry.setId(((BigDecimal) cols[0]).longValue());
                }
                if (null != cols[1]) {
                    propertyEntry.setIrdi(cols[1].toString());
                }
                if (null != cols[2]) {
                    propertyEntry.setValue(cols[2].toString());
                }
                if (null != cols[3]) {
                    propertyEntry.setUnit(cols[3].toString());
                }
                if (null != cols[4]) {
                    propertyEntry.setPrefix(cols[4].toString());
                }
                if (null != cols[5]) {
                    propertyEntry.setTolerance((Long) cols[5]);
                }
                if (null != cols[6]) {
                    propertyEntry.setValueId((Long) cols[6]);
                }
                return propertyEntry;
            }
        };
    }

    public List<PropStringObjT> execute(String propertyId) {
        Map in = new HashMap();
        in.put("EXT_PROD_ID", propertyId);
        Map<String, Object> out = execute(in);
        for (Map.Entry<String, Object> entry : out.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        if (!out.isEmpty())
            return (List<PropStringObjT>) out.get("OUTTBL_OBJ_STRING");
        else
            return Collections.emptyList();
    }
}

