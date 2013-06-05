package de.feu.plib.dao.RowMapper;

import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.value.StringValueType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO: document file
 */
public class StringPropertyRowMapper implements RowMapper<PropertyValueType> {

    @Override
    public PropertyValueType mapRow(ResultSet rs, int rowNum) throws SQLException {
        PropertyValueType propertyValueType = new PropertyValueType();
        StringValueType stringValueType = new StringValueType();
        stringValueType.setValue(rs.getString("VALUE"));
        propertyValueType.setStringValue(stringValueType);
        propertyValueType.setPropertyRef(rs.getString("IRDI"));
        rs.getString("UNIT");
        rs.getString("PREFIX");
        rs.getString("TOLERANCE");
        rs.getString("VALUE_ID");
        return propertyValueType;
    }
}
