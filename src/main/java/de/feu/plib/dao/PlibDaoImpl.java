package de.feu.plib.dao;

import de.feu.plib.dao.procedures.GetObjNumber;
import de.feu.plib.dao.procedures.GetObjString;
import de.feu.plib.dao.procedures.types.PropertyObjectT;
import de.feu.plib.processor.analyser.Irdi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Transactional
public class PlibDaoImpl implements PlibDao {

    /**
     * Logger instance
     */
    static final Logger LOGGER = Logger.getLogger(PlibDaoImpl.class);

    @Autowired
    @Qualifier(value = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(value = "dataSource")
    javax.sql.DataSource ds;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> readExternalProductIdsBy(Irdi irdi) {
        List<String> externalIds = new ArrayList<String>();
        List<Map<String, Object>> extIdList = getExternalIds(irdi);
        LOGGER.info("Read ext_product_ids of irdi: " + irdi.getIrdi());
        for (Map<String, Object> extIdRow : extIdList) {
            for (Map.Entry<String, Object> extIdEntry : extIdRow.entrySet()) {
                LOGGER.info("Column name: " + extIdEntry.getKey());
                LOGGER.info("Column value: " + extIdEntry.getValue());
                externalIds.add((String) extIdEntry.getValue());
            }
        }
        LOGGER.info(extIdList);
        return externalIds;
    }

    /**
     * Get the external ids from the property with given irdi
     *
     * @param irdi the irdi of the property
     * @return a list of external ids
     */
    protected List<Map<String, Object>> getExternalIds(Irdi irdi) {
        return jdbcTemplate.queryForList(SQLQuery.GET_EXT_ID_SQL.getSql(), new Object[]{irdi.getIrdi()});
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

    @Transactional(readOnly = true)
    @Override
    public int getNumberOfObjectsOfIrdi(Irdi irdi) {
        int numberOfObjects = 0;
        numberOfObjects = jdbcTemplate.queryForInt(SQLQuery.CHECK_IRDI.getSql(), new Object[]{irdi.getIrdi()});
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
        List companyNames = jdbcTemplate.query(SQLQuery.SELECT.getSql(), new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                LOGGER.info(rs.getString(1));
                return rs.getString(1);
            }
        });
        return companyNames;
    }


    /**
     * Load the string properties plain from the database in the type the database returns.
     * Handle business logic in other class (facade, or business logic)
     * We cannot map the plain lists to value objects. This would be double work as we currently do not
     * exactly know which type it is. We only load it from the predefined DO_STRING table, but we do not know if
     * it really is a string!
     *
     * @param externalIds list of external ids
     * @return list of items which hold a list of property value pair
     */
    @Override
    public List<List<Map<String, Object>>> loadStringPropertiesByExternalIds(List<BigDecimal> externalIds) {
        LOGGER.info("external ids: " + externalIds);
        List<List<Map<String, Object>>> itemValueList = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> propertyIdList;

        for (BigDecimal extId : externalIds) {
            propertyIdList = jdbcTemplate.queryForList(SQLQuery.GET_STRING_PROPERTIES.getSql(), new Object[]{extId});
            LOGGER.info("Property id list of external id: " + extId + " is " + propertyIdList.toString());
            itemValueList.add(propertyIdList);
        }

        return itemValueList;
    }

    /**
     * @param externalIds list of external ids
     * @return List of PropStringObjT elements holding the
     */
    @Override
    public List<List<PropertyObjectT>> loadStringPropertiesBy(List<String> externalIds) {
        List<List<PropertyObjectT>> itemValueList = new ArrayList<List<PropertyObjectT>>();

        GetObjString getObjString = new GetObjString(ds);
        for (String extId : externalIds) {
            List<PropertyObjectT> resultList = getObjString.execute(extId);
            LOGGER.info("PropStringObjT list of external id: " + extId + " is " + resultList.toString());
            itemValueList.add(resultList);
        }
        return itemValueList;
    }

    @Override
    public List<List<PropertyObjectT>> loadNumberPropertiesBy(List<String> externalIds) {
        List<List<PropertyObjectT>> itemValueList = new ArrayList<List<PropertyObjectT>>();

        GetObjNumber getObjNumber = new GetObjNumber(ds);
        for (String extId : externalIds) {
            List<PropertyObjectT> resultList = getObjNumber.execute(extId);
            LOGGER.info("PropNumberObjT list of external id: " + extId + " is " + resultList.toString());
            itemValueList.add(resultList);
        }
        return itemValueList;
    }

    /**
     * TODO implement method loadNumberPropertiesByExternalIds
     *
     * @param externalIds list of external ids
     * @return list of items which hold a list of property value pair
     */
    @Override
    public List<List<Map<String, Object>>> loadNumberPropertiesByExternalIds(List<BigDecimal> externalIds) {
        LOGGER.info("external ids: " + externalIds);
        List<List<Map<String, Object>>> itemValueList = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> propertyIdList;

        for (BigDecimal extId : externalIds) {
            propertyIdList = jdbcTemplate.queryForList(SQLQuery.GET_NUMBER_PROPERTIES.getSql(), new Object[]{extId});
            LOGGER.info("Property id list of external id: " + extId + " is " + propertyIdList.toString());
            itemValueList.add(propertyIdList);
        }

        return itemValueList;
    }

    @Override
    public List<Map<String, Object>> loadTypeAndUnitOfPropertyBy(String propertyId) {
        List<Map<String, Object>> propertyIdList = jdbcTemplate.queryForList(SQLQuery.GET_PROPERTY_DATA_TYPE.getSql(), new Object[]{propertyId});
        return propertyIdList;
    }

}
