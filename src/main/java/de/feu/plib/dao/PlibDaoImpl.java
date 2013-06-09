package de.feu.plib.dao;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.business.analyser.Irdi;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.value.StringValueType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
public class PlibDaoImpl implements PlibDao {

    /**
     * Logger instance
     */
    static final Logger LOGGER = Logger.getLogger(PlibDaoImpl.class);

    private static final String SELECT = "SELECT name FROM DE_COMPANY";

    private static final String CHECK_IRDI_SQL = "SELECT COUNT(*) FROM DE_CLASS c, DO_OBJECT o WHERE c.ID = o.C_ID AND c.IRDI = ?";

    private static final String GET_EXT_ID_SQL = "SELECT o.DI_ID FROM DE_CLASS c, DO_OBJECT o WHERE c.ID = o.C_ID AND c.IRDI = ?";

    private static final String GET_STRING_PROPERTIES = "SELECT P.IRDI, LOJ.VALUE, LOJ.UNIT, LOJ.PREFIX, LOJ.TOLERANCE, LOJ.VALUE_ID " +
            "       FROM (SELECT DI_ID, P_ID,  VALUE, UNIT, PREFIX, TOLERANCE, VALUE_ID " +
            "             FROM DO_STRING LEFT OUTER JOIN (SELECT DI_ID, P_ID, UNIT, PREFIX, TOLERANCE, VALUE_ID" +
            "                                             FROM DO_ADDITIONAL_DATA " +
            "                                             WHERE ERR_CODE = 1)" +
            "             USING (DI_ID, P_ID) " +
            "             WHERE (DI_ID =?) AND (DO_STRING.ERR_CODE = 1))LOJ JOIN DE_PROPERTY P" +
            "                                                                       ON LOJ.P_ID = P.ID";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BigDecimal> readExternalProductIdsBy(Irdi irdi) {
        List<BigDecimal> externalIds = new ArrayList<BigDecimal>();
        List<Map<String, Object>> extIdList = jdbcTemplate.queryForList(GET_EXT_ID_SQL, new Object[]{irdi.getIrdi()});
        LOGGER.info("Read ext_product_ids of irdi: " + irdi.getIrdi());
        for (Map<String, Object> extIdRow : extIdList) {
            for (Map.Entry<String, Object> extIdEntry : extIdRow.entrySet()) {
                LOGGER.info("Column name: " + extIdEntry.getKey());
                LOGGER.info("Column value: " + extIdEntry.getValue());
                externalIds.add((BigDecimal) extIdEntry.getValue());
            }
        }
        LOGGER.info(extIdList.toString());
        return externalIds;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean doObjectsExistsWithThis(Irdi irdi) {
        int numberOfObjects = getNumberOfObjectsOfIrdi(irdi);
        if (numberOfObjects > 0) {
            return true;
        }
        return false;
    }

    public boolean noObjectsExistsWithThis(Irdi irdi) {
        return !doObjectsExistsWithThis(irdi);
    }

    @Transactional(readOnly = true)
    @Override
    public int getNumberOfObjectsOfIrdi(Irdi irdi) {
        int numberOfObjects = 0;
        numberOfObjects = jdbcTemplate.queryForInt(CHECK_IRDI_SQL, new Object[]{irdi.getIrdi()});
        LOGGER.info("numberOfObjects: " + numberOfObjects);
        return numberOfObjects;
    }

    /**
     * This is just a test method to receive all companies defined in database.
     * Uses spring rowmapper as example for receiving response.
     *
     * @return a list of all companies
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getCompanyNames() {
        @SuppressWarnings("rawtypes")
        List companyNames = jdbcTemplate.query(SELECT, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                LOGGER.info(rs.getString(1));
                return rs.getString(1);
            }
        });
        return companyNames;
    }


    // TODO rename to loadStringPropertiesByExternalIds
    // TODO do the logic in business logic
    @Override
    public CatalogueType loadObjectsFrom(EnrichedQuery enrichedQuery) {
        CatalogueType catalogue = new CatalogueType();

        if (noObjectsExistsWithThis(enrichedQuery)) {
            return catalogue;
        }

        return catalogue;
    }

    /**
     * Load the string properties plain from the database in the type the database returns.
     * Handle business logic in other class (facade, or business logic)
     * TODO refactor doublelist to a model class
     * @param externalIds list of external ids
     * @return list of items which hold a list of property value pair
     */
    @Override
    public List<List<PropertyValueType>> loadStringPropertiesByExternalIds(List<BigDecimal> externalIds) {
        LOGGER.info("external ids: " + externalIds);
        List<List<PropertyValueType>> itemValueList = new ArrayList<List<PropertyValueType>>();
        List<Map<String, Object>> propertyIdList;

        for (BigDecimal extId : externalIds) {
            List<PropertyValueType> propertyValueTypeList = new ArrayList<PropertyValueType>();
            propertyIdList = jdbcTemplate.queryForList(GET_STRING_PROPERTIES, new Object[]{extId});
            LOGGER.info("Property id list of external id: " + extId + " is " + propertyIdList.toString());

            for (Map<String, Object> extIdRow : propertyIdList) {
                PropertyValueType propertyValueType = getKeyValueFromRow(extIdRow.entrySet());
                propertyValueTypeList.add(propertyValueType);
            }
            itemValueList.add(propertyValueTypeList);
        }

        return itemValueList;
    }

    @Override
    public List<PropertyValueType> loadNumberPropertiesByExternalIds(List<String> externalIds) {
        return null;
    }

    private PropertyValueType getKeyValueFromRow(Set<Map.Entry<String, Object>> entries) {
        PropertyValueType property = new PropertyValueType();
        for (Map.Entry<String, Object> propertyEntry : entries) {
            if ("IRDI".equals(propertyEntry.getKey())) {
                LOGGER.info("Property IRDI found in entry: " + propertyEntry.getValue());
                property.setPropertyRef((String) propertyEntry.getValue());
            }
            if ("VALUE".equals(propertyEntry.getKey())) {
                LOGGER.info("Property VALUE found in entry: " + propertyEntry.getValue());
                // TODO: check which value type was given here and do a check
                // TODO: e.g. integer, string, date, boolean...
                StringValueType stringValueType = new StringValueType();
                stringValueType.setValue((String) propertyEntry.getValue());
                property.setStringValue(stringValueType);
            }
        }
        return property;
    }

    public String callProcedure() {
        jdbcTemplate.execute(new CallableStatementCreator() {
                                 public CallableStatement createCallableStatement(Connection con) throws SQLException {
                                     CallableStatement cs = con.prepareCall("{call MY_STORED_PROCEDURE(?, ?, ?)}");
                                     //cs.setInt(1, ...); // first argument
                                     //cs.setInt(2, ...); // second argument
                                     //cs.setInt(3, ...); // third argument
                                     return cs;
                                 }
                             },
                new CallableStatementCallback() {
                    public Object doInCallableStatement(CallableStatement cs) throws SQLException {
                        cs.execute();
                        return null; // Whatever is returned here is returned from the jdbcTemplate.execute method
                    }
                }
        );
        return null;
    }
}
