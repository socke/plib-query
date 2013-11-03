package de.feu.plib.dao.procedures.types;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 * TODO document class Footype
 */
public class Footype implements SQLData {
    @Override
    public String getSQLTypeName() throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void readSQL(SQLInput sqlInput, String s) throws SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void writeSQL(SQLOutput sqlOutput) throws SQLException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
