package de.feu.plib.dao.procedures;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.jdbc.core.support.AbstractSqlTypeValue;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO document class FooStoredProcedure
 */
public class FooStoredProcedure {
    private static final String SP_NAME = "FOO";
    private static final String MY_ARRAY = "MY_ARRAY";
    private static final String MY_TYPE = "MY_TYPE";
    private static final String I_ARRAY = "i_array";
    private static final String O_ARRAY = "o_array";

    private final StoredProcedure storedProcedure;

    public FooStoredProcedure(DataSource dataSource) {
        storedProcedure = new StoredProcedure(dataSource, SP_NAME) {
            {
                declareParameter(new SqlParameter(I_ARRAY, Types.ARRAY, MY_ARRAY));
                declareParameter(new SqlOutParameter(O_ARRAY, Types.ARRAY, MY_ARRAY, new SqlReturnType() {

                    @Override
                    public Object getTypeValue(CallableStatement cs, int paramIndex,
                                               int sqlType, String typeName) throws SQLException {
                        Connection connection = cs.getConnection();
                        Map<String, Class<?>> typeMap = connection.getTypeMap();
                        typeMap.put(MY_TYPE, MyType.class);

                        return cs.getObject(paramIndex);
                    }
                }));

                compile();
            }
        };
    }


    /**
     * @return array of {@link MyType} objects or <code>null</code>
     */
    public MyType[] execute(final MyType[] values) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(I_ARRAY, new AbstractSqlTypeValue() {

            @Override
            protected Object createTypeValue(Connection con, int sqlType, String typeName) throws SQLException {
                ArrayDescriptor descriptor = new ArrayDescriptor(typeName, con);
                return new ARRAY(descriptor, con, values);
            }
        });

        Map<?, ?> result = storedProcedure.execute(params);
        if ((!result.containsKey(O_ARRAY) || result.get(O_ARRAY) == null)) {

            return null;
        }
        try {
            Object[] resultArray = (Object[]) ((ARRAY) result.get(O_ARRAY)).getArray();
            return Arrays.copyOf(resultArray, resultArray.length, MyType[].class);
        } catch (SQLException e) {
            throw new DataRetrievalFailureException("Unable to retrieve array", e);
        }
    }

    public static class MyType implements SQLData {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String getSQLTypeName() throws SQLException {
            return MY_TYPE;
        }

        @Override
        public void readSQL(SQLInput stream, String typeName) throws SQLException {
            name = stream.readString();
            value = stream.readString();
        }

        @Override
        public void writeSQL(SQLOutput stream) throws SQLException {
            stream.writeString(name);
            stream.writeString(value);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

}
