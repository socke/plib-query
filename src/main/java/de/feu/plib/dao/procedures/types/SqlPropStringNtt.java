package de.feu.plib.dao.procedures.types;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 * This class represents the custom SQL type from the PLIB oracle database PROP_STRING_NTT which will be used
 * in the procedure GET_PROP_VALS_STRING.
 * It can be used to receive the data from the procedure.
 *
 * Note: the naming is not very well chosen, but we stick to the naming of the oracle types to make it recognizable.
 */
public class SqlPropStringNtt extends PropStringNtt implements SQLData {

    @Override
    public String getSQLTypeName() throws SQLException {
        return "PACK_PROPERTY.PROP_STRING_T";
    }

    @Override
    public void readSQL(SQLInput sqlInput, String s) throws SQLException {
        setValue(sqlInput.readString());
        setIrdi(sqlInput.readString());
        setPrefix(sqlInput.readString());
        setTolerance(Long.valueOf(sqlInput.readLong()));
        setUnit(sqlInput.readString());
        setValueId(Long.valueOf(sqlInput.readLong()));
    }

    @Override
    public void writeSQL(SQLOutput sqlOutput) throws SQLException {
        sqlOutput.writeString(getValue());
        sqlOutput.writeString(getIrdi());
        sqlOutput.writeString(getPrefix());
        sqlOutput.writeLong(getTolerance());
        sqlOutput.writeString(getUnit());
        sqlOutput.writeLong(getValueId());
    }
}
