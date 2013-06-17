package de.feu.plib.business.handler;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.dao.PlibDao;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.value.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO: document file
 */
public class SimpleQueryService {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(SimpleQueryService.class);

    @Autowired
    @Qualifier(value = "plibDao")
    private PlibDao plibDao;

    private EnrichedQuery enrichedQuery;

    public EnrichedQuery getEnrichedQuery() {
        return enrichedQuery;
    }

    public void setEnrichedQuery(EnrichedQuery enrichedQuery) {
        this.enrichedQuery = enrichedQuery;
    }

    public SimpleQueryService() {}

    public SimpleQueryService(EnrichedQuery enrichedQuery) {
        this.enrichedQuery = enrichedQuery;
    }

    /**
     * Checks if objects exists in database, then load the items of the objects and fills each item
     * with its properties.
     * Then add all the items to the catalogue object
     * @return CatalogueType holding all items with properties
     */
    public CatalogueType loadData() {
        CatalogueType catalogueType = new CatalogueType();

        if (objectsExistInDatabase()) {
            List<List<Map<String, Object>>> listOfItems = loadItems();

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);

            List<Map<String, Object>> propertyTypesAndValues = loadTypesAndUnitsFromPropertyIds(propertyIds);

            for (List<Map<String, Object>> propList : listOfItems) {
                ItemType item = new ItemType();
                item.setClassRef(enrichedQuery.getIrdi());

                for (Map<String, Object> map : propList) {
                    String irdi = "";
                    String value = "";
                    BigDecimal id = BigDecimal.ZERO;
                    PropertyValueType propertyValue = new PropertyValueType();

                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        LOGGER.info("key: " + entry.getKey());
                        LOGGER.info("value: " + entry.getValue());

                        if ("IRDI".equals(entry.getKey())) {
                            irdi =  (String)entry.getValue();
                        }
                        if ("ID".equals(entry.getKey())) {
                            id = (BigDecimal)entry.getValue();
                        }
                        if ("VALUE".equals(entry.getKey())) {
                            value = (String)entry.getValue();
                        }

                        item.getPropertyValue().add(propertyValue);
                    }
                    propertyValue.setPropertyRef(irdi);
                    propertyValue = loadFromTypeAndUnitMap(id, value, propertyValue, propertyTypesAndValues);
                    item.getPropertyValue().add(propertyValue);
                }

                catalogueType.getItem().add(item);
            }
        }
        return catalogueType;
    }

    private PropertyValueType loadFromTypeAndUnitMap(BigDecimal id, String value, PropertyValueType propertyValue, List<Map<String, Object>> propertyTypesAndValues) {
        String propertyId = id.toPlainString();
        for (Map<String, Object> propertyTypesAndValue : propertyTypesAndValues) {
            String subType = "";
            BigDecimal propertyIdValue = BigDecimal.ZERO;
            String bound1 = "";
            String bound2 = "";
            for (Map.Entry<String, Object> entry : propertyTypesAndValue.entrySet()) {
                if ("SUB_TYPE".equals(entry.getKey())) {
                    subType = (String) entry.getValue();
                }
                if ("PROPERTY_ID".equals(entry.getKey())) {
                    propertyIdValue = (BigDecimal) entry.getValue();
                }
                if ("BOUND1".equals(entry.getKey())) {
                    bound1 = (String) entry.getValue();
                }
                if ("BOUND2".equals(entry.getKey())) {
                    bound2 = (String) entry.getValue();
                }
            }

            if (propertyIdValue.toPlainString().equals(propertyId)) {
                LOGGER.info("property id match: " + propertyId);

                if (StringUtils.isNotEmpty(subType)) {
                    // these are all actually available types in database.
                    // TODO create mapping enum so that it can be mapped automatically
                    if ("real_type".equals(subType))  {
                        RealValueType realValue = new RealValueType();
                        realValue.setValue(Double.valueOf(value));
                        propertyValue.setRealValue(realValue);
                    }
                    if ("int_type".equals(subType))  {
                        IntegerValueType intType = new IntegerValueType();
                        intType.setValue(Integer.valueOf(value));
                        propertyValue.setIntegerValue(intType);
                    }
                    if ("string_type".equals(subType))  {
                        StringValueType stringType = new StringValueType();
                        stringType.setValue(String.valueOf(value));
                        propertyValue.setStringValue(stringType);
                    }
                    // TODO see 29002-10 chapter 6.5
                    // we have here defined measure_type with prefix real.
                    if ("real_measure_type".equals(subType))  {
                        // TODO we have three measure types and must decide which to use
                        if (boundsAreSet(bound1, bound2)) {
                            /*
                             * TODO assuming that bound1 is lower bound and bound2 is upper bound
                             * we further assume that a real value was given per bound value.
                             * The reason is that it is called real_measure_type.
                             * But we do not know if there are other value types, e.g. Complex...
                             */
                            MeasureRangeValueType mrt = new MeasureRangeValueType();
                            NumericValueType lowerValue = new NumericValueType();
                            NumericValueType upperValue = new NumericValueType();
                            RealValueType rvtBound1 = new RealValueType();
                            rvtBound1.setValue(Double.valueOf(bound1));
                            lowerValue.setRealValue(rvtBound1);
                            RealValueType rvtBound2 = new RealValueType();
                            rvtBound2.setValue(Double.valueOf(bound1));
                            upperValue.setRealValue(rvtBound2);
                            mrt.setLowerValue(lowerValue);
                            mrt.setUpperValue(upperValue);
                            propertyValue.setMeasureRangeValue(mrt);
                        }

                        if (!boundsAreSet(bound1, bound2) ) {
                            MeasureSingleNumberValueType msv = new MeasureSingleNumberValueType();
                            RealValueType realValue = new RealValueType();
                            realValue.setValue(Double.valueOf(value));
                            msv.setRealValue(realValue);
                            propertyValue.setMeasureSingleNumberValue(msv);
                        }

                        /*
                         * TODO check what this MeasureQualifiedNumberValueType is and how to map that from DB
                         */
                        if (false) {
                            MeasureQualifiedNumberValueType mqnv = new MeasureQualifiedNumberValueType();
                            QualifiedValueType qualifiedValueType = new QualifiedValueType();
                            RealValueType realValueType = new RealValueType();
                            realValueType.setValue(Double.valueOf(value));
                            qualifiedValueType.setRealValue(realValueType);
                            mqnv.getQualifiedValue().add(qualifiedValueType);
                            propertyValue.setMeasureQualifiedNumberValue(mqnv);
                        }

                        //propertyValue.setMeasureSingleNumberValue();
                    }
                    if ("non_quantitative_code_type".equals(subType))  {
                        // TODO non_quantitative_code_type - what kind of type is this?
                    }
                }
            }
        }
        return propertyValue;
    }

    private boolean boundsAreSet(String bound1, String bound2) {
        return StringUtils.isNotEmpty(bound1) && StringUtils.isNotEmpty(bound2);
    }

    private List<Map<String, Object>> loadTypesAndUnitsFromPropertyIds(List<String> propertyIds) {
        List<Map<String, Object>> propertyTypesAndUnits = new ArrayList<Map<String, Object>>();

        for (String id : propertyIds) {
            List<Map<String, Object>> typesAndUnitsList = plibDao.loadTypeAndUnitOfPropertyBy(id);
            for (Map<String, Object> currentTypesAndUnitMap : typesAndUnitsList) {
                propertyTypesAndUnits.add(currentTypesAndUnitMap);
            }
        }
        LOGGER.info("propertyTypesAndUnits: " + propertyTypesAndUnits);
        return propertyTypesAndUnits;
    }

    private List<String> getPropertyIdsFromProperties(List<List<Map<String, Object>>> listOfItems) {
        List<String> propertyIds = new ArrayList<String>();
        for (List<Map<String, Object>> propList : listOfItems) {
            for (Map<String, Object> map : propList) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    LOGGER.info("key: " + entry.getKey());
                    LOGGER.info("value: " + entry.getValue());
                    if ("ID".equals(entry.getKey())) {
                        LOGGER.info("found id: " + entry.getValue());
                        BigDecimal value = (BigDecimal) entry.getValue();
                        propertyIds.add(value.toPlainString());
                        break;
                    }
                }
            }
        }
        return propertyIds;
    }

    private List<List<Map<String,Object>>> loadItems() {
        List<BigDecimal> listOfExternalIds = loadExternalIds();
        // TODO load all other item database tables (DO_STRING, DO_NUMBER ...)
        return plibDao.loadStringPropertiesByExternalIds(listOfExternalIds);
    }

    private ItemType fillItemWithProperties(List<Map<String, Object>> propList) {
        ItemType item = new ItemType();
        /**
        for (PropertyValueType propertyValueType : propList) {
            item.getPropertyValue().add(propertyValueType);
        }
         */
        return item;
    }

    private List<BigDecimal> loadExternalIds() {
        return plibDao.readExternalProductIdsBy(enrichedQuery);
    }

    private boolean objectsExistInDatabase() {
        return plibDao.doObjectsExistsWithThis(enrichedQuery);
    }

    public PlibDao getPlibDao() {
        return plibDao;
    }

    public void setPlibDao(PlibDao plibDao) {
        this.plibDao = plibDao;
    }
}
