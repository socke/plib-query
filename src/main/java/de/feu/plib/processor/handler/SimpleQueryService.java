package de.feu.plib.processor.handler;

import de.feu.plib.dao.procedures.types.PropStringObjT;
import de.feu.plib.processor.analyser.BaseIrdi;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.dao.PlibDao;
import de.feu.plib.processor.analyser.Irdi;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SimpleQueryService handles simple queries.
 * Loads the data from the database and maps the data to the catalogue structure for returning to the client.
 */
public class SimpleQueryService extends AbstractQueryService {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(SimpleQueryService.class);

    @Autowired
    @Qualifier(value = "plibDao")
    private PlibDao plibDao;

    public SimpleQueryService() {
    }

    public SimpleQueryService(EnrichedQuery enrichedQuery) {
        this.setEnrichedQuery(enrichedQuery);
    }

    /**
     * Checks if objects exists in database, then load the items of the objects and fills each item
     * with its properties.
     * Then add all the items to the catalogue object
     *
     * @return CatalogueType holding all items with properties
     */
    public CatalogueType loadDataWithIRDIOnly() {

        catalogueType = new CatalogueType();

        if (objectsExistInDatabase()) {
            LOGGER.trace("Objects exist in database");
            List<List<PropStringObjT>> listOfItems = loadItems();
            LOGGER.trace("Items loaded from db");

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);
            LOGGER.trace("property ids grabbed from properties");

            List<Map<String, Object>> propertyTypesAndValues = loadTypesAndUnitsFromPropertyIds(propertyIds);
            LOGGER.trace("property types and values loaded from db");

            // now we must put all that data together, items + irdi + properties of the items + their irdis + all
            // properties and types. These must be mapped to the catalogue model
            mapItemDataToCatalogue(listOfItems, propertyTypesAndValues);
        }
        return catalogueType;
    }

    /**
     * First get irdis of class_ref of all items.
     * Load the data from the items with that class_refs.
     * Check if the the properties match.
     * If so then fill the catalogue with all items, if not the data does not match
     * (validation)
     *
     * @return CatalogueType holding all items with properties
     */
    @Override
    public CatalogueType loadDataWithItemsOnly() {

        catalogueType = new CatalogueType();

        Irdi irdi = new BaseIrdi(getEnrichedQuery().getQuery().getItem().getClassRef());

        if (objectsExistInDatabase(irdi)) {
            LOGGER.trace("Objects exist in database");
            List<List<PropStringObjT>> listOfItems = loadItems(irdi);
            LOGGER.trace("Items loaded from db");

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);
            LOGGER.trace("property ids grabbed from properties");

            List<Map<String, Object>> propertyTypesAndUnits = loadTypesAndUnitsFromPropertyIds(propertyIds);
            LOGGER.trace("property types and values loaded from db");

            //  get all property data from item in query then check if we have an item in result with the property ref and the value
            if (allPropertiesExistWithValuesIn(listOfItems)) {
                // if so we must put all that data together, items + irdi + properties of the items + their irdis + all
                // properties and units. These must be mapped to the catalogue model.
                mapItemDataToCatalogue(listOfItems, propertyTypesAndUnits, irdi.getIrdi());
            }
        }

        return catalogueType;
    }


    private boolean allPropertiesExistWithValuesIn(List<List<PropStringObjT>> listOfItems) {
        List<PropertyValueType> allPropertyValues = getEnrichedQuery().getQuery().getItem().getPropertyValue();

        // TODO currently we only check the string and real properties, need to do that for all other properties as well.
        Map<PropertyValueType, Boolean> valueFoundMap = new HashMap<PropertyValueType, Boolean>();

        for (PropertyValueType propType : allPropertyValues) {
            valueFoundMap.put(propType, Boolean.FALSE);
            String queryPropertyRefIrdi = propType.getPropertyRef();

            if (null != propType.getMeasureSingleNumberValue()) {
                Double queryItemvalue = propType.getMeasureSingleNumberValue().getRealValue().getValue();
                // TODO implement for all other subtypes of MeasuredSingleNumberValue

                for (List<PropStringObjT> propList : listOfItems) {
                    for (PropStringObjT propStringObj : propList) {
                        String irdi = propStringObj.getIrdi();
                        Double value = Double.valueOf(propStringObj.getValue());

                        if (queryItemvalue.equals(value) && queryPropertyRefIrdi.equals(irdi)) {
                            valueFoundMap.remove(propType);
                            valueFoundMap.put(propType, Boolean.TRUE);
                        }
                    }
                }
            }

            if (null != propType.getStringValue()) {

                String queryItemvalue = propType.getStringValue().getValue();

                for (List<PropStringObjT> propList : listOfItems) {
                    for (PropStringObjT propStringObj : propList) {
                        String irdi = propStringObj.getIrdi();
                        String value = propStringObj.getValue();

                        if (queryItemvalue.equals(value) && queryPropertyRefIrdi.equals(irdi)) {
                            valueFoundMap.remove(propType);
                            valueFoundMap.put(propType, Boolean.TRUE);
                        }
                    }
                }
            }

            if (null != propType.getRealValue()) {

                Double queryItemvalue = propType.getRealValue().getValue();

                for (List<PropStringObjT> propList : listOfItems) {
                    for (PropStringObjT propStringObj : propList) {
                        String irdi = propStringObj.getIrdi();
                        Double value = Double.valueOf(propStringObj.getValue());

                        if (queryItemvalue.equals(value) && queryPropertyRefIrdi.equals(irdi)) {
                            valueFoundMap.remove(propType);
                            valueFoundMap.put(propType, Boolean.TRUE);
                        }
                    }
                }
            }
            if (null != propType.getBagValue()) {
                // TODO implement value type check
            }
            if (null != propType.getBooleanValue()) {
                // TODO implement value type check
            }
            if (null != propType.getComplexValue()) {
                // TODO implement value type check
            }
            if (null != propType.getCompositeValue()) {
                // TODO implement value type check
            }
            if (null != propType.getControlledValue()) {
                // TODO implement value type check
            }
            if (null != propType.getCurrencyValue()) {
                // TODO implement value type check
            }
            if (null != propType.getDateTimeValue()) {
                // TODO implement value type check
            }
            if (null != propType.getDateValue()) {
                // TODO implement value type check
            }
            if (null != propType.getEnvironment()) {
                // TODO implement value type check
            }
            if (null != propType.getFileValue()) {
                // TODO implement value type check
            }
            if (null != propType.getIntegerValue()) {
                // TODO implement value type check
            }
            if (null != propType.getItemReferenceValue()) {
                // TODO implement value type check
            }
            if (null != propType.getLocalizedTextValue()) {
                // TODO implement value type check
            }
            if (null != propType.getMeasureQualifiedNumberValue()) {
                // TODO implement value type check
            }
            if (null != propType.getMeasureRangeValue()) {
                // TODO implement value type check
            }
            if (null != propType.getNullValue()) {
                // TODO implement value type check
            }
            if (null != propType.getOneOf()) {
                // TODO implement value type check
            }
            if (null != propType.getRationalValue()) {
                // TODO implement value type check
            }
            if (null != propType.getSequenceValue()) {
                // TODO implement value type check
            }
            if (null != propType.getSetValue()) {
                // TODO implement value type check
            }
            if (null != propType.getTimeValue()) {
                // TODO implement value type check
            }
            if (null != propType.getYearMonthValue()) {
                // TODO implement value type check
            }
            if (null != propType.getYearValue()) {
                // TODO implement value type check
            }
        }

        // check if we have one not found property in the map where we marked the searched properties
        // if so, then return false
        for (Map.Entry<PropertyValueType, Boolean> entry : valueFoundMap.entrySet()) {
            if (Boolean.FALSE.equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CatalogueType loadDataWithProjection() {
        return null;
    }

    /**
     * Maps the collected data to add it finally to the return type catalogue model.
     * Will be mapped into {@link #catalogueType}
     *
     * @param listOfItems
     * @param propertyTypesAndValues
     * @param classIrdi
     */
    private void mapItemDataToCatalogue(List<List<PropStringObjT>> listOfItems, List<Map<String, Object>> propertyTypesAndValues, String classIrdi) {
        for (List<PropStringObjT> propList : listOfItems) {
            ItemType item = new ItemType();
            item.setClassRef(classIrdi);
            LOGGER.info("set class ref: " + classIrdi);

            for (PropStringObjT propStringObj : propList) {
                String irdi = propStringObj.getIrdi();
                String value = propStringObj.getValue();
                BigDecimal id = BigDecimal.valueOf(propStringObj.getId());
                LOGGER.info("irdi: " + irdi + " value: " + value + " id: " + id);

                PropertyValueType propertyValue = new PropertyValueType();

                LOGGER.trace("Set irdi and property values in propertyValue instance then add to item");
                propertyValue.setPropertyRef(irdi);
                propertyValue = loadFromTypeAndUnitMap(id, value, propertyValue, propertyTypesAndValues);
                item.getPropertyValue().add(propertyValue);
            }
            LOGGER.info("Add item to catalogue: " + item);
            catalogueType.getItem().add(item);
        }
    }

    /**
     * Maps the collected data to add it finally to the return type catalogue model.
     * Delegates to {@link #mapItemDataToCatalogue(java.util.List, java.util.List, String)}
     *
     * @param listOfItems
     * @param propertyTypesAndValues
     */
    private void mapItemDataToCatalogue(List<List<PropStringObjT>> listOfItems, List<Map<String, Object>> propertyTypesAndValues) {
        mapItemDataToCatalogue(listOfItems, propertyTypesAndValues, getEnrichedQuery().getIrdi());
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
                    if ("real_type".equals(subType)) {
                        RealValueType realValue = new RealValueType();
                        realValue.setValue(Double.valueOf(value));
                        propertyValue.setRealValue(realValue);
                    }
                    if ("int_type".equals(subType)) {
                        IntegerValueType intType = new IntegerValueType();
                        intType.setValue(Integer.valueOf(value));
                        propertyValue.setIntegerValue(intType);
                    }
                    if ("string_type".equals(subType)) {
                        StringValueType stringType = new StringValueType();
                        stringType.setValue(String.valueOf(value));
                        propertyValue.setStringValue(stringType);
                    }
                    // TODO see 290002-10 chapter 6.5
                    // we have here defined measure_type with prefix real.
                    if ("real_measure_type".equals(subType)) {
                        // TODO we have three measure types and must decide which to use
                        if (boundsAreSet(bound1, bound2)) {
                            /*
                             * TODO assuming that bound1 is lower bound and bound2 is upper bound
                             * we further assume that a real value was given per bound value.
                             * The reason is that it is called real_measure_type.
                             * But we do not know if there are other value types, e.g. Complex...
                             */
                            setPropertyValueByBounds(propertyValue, bound1, bound2);
                        }

                        if (!boundsAreSet(bound1, bound2)) {
                            setPropertyValue(value, propertyValue);
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

                    }
                    if ("non_quantitative_code_type".equals(subType)) {
                        // TODO non_quantitative_code_type - what kind of type is this?
                    }
                }
            }
        }
        return propertyValue;
    }

    private void setPropertyValue(String value, PropertyValueType propertyValue) {
        MeasureSingleNumberValueType msv = new MeasureSingleNumberValueType();
        RealValueType realValue = new RealValueType();
        realValue.setValue(Double.valueOf(value));
        msv.setRealValue(realValue);
        propertyValue.setMeasureSingleNumberValue(msv);
    }

    private void setPropertyValueByBounds(PropertyValueType propertyValue, String bound1, String bound2) {
        MeasureRangeValueType mrt = new MeasureRangeValueType();
        NumericValueType lowerValue = new NumericValueType();
        NumericValueType upperValue = new NumericValueType();
        RealValueType rvtBound1 = new RealValueType();
        rvtBound1.setValue(Double.valueOf(bound1));
        lowerValue.setRealValue(rvtBound1);
        RealValueType rvtBound2 = new RealValueType();
        rvtBound2.setValue(Double.valueOf(bound2));
        upperValue.setRealValue(rvtBound2);
        mrt.setLowerValue(lowerValue);
        mrt.setUpperValue(upperValue);
        propertyValue.setMeasureRangeValue(mrt);
    }

    private boolean boundsAreSet(String bound1, String bound2) {
        return StringUtils.isNotEmpty(bound1) && StringUtils.isNotEmpty(bound2);
    }

    /**
     * Loads the types and units from the specific tables of the plib database.
     * The items have also types and units, but we have that as strings so we cannot use them.
     * So we read the date from the specific tables, where it is clear which type or unit it is.
     *
     * @param propertyIds all property ids
     * @return a list holding mapped types and units of the given property ids.
     */
    private List<Map<String, Object>> loadTypesAndUnitsFromPropertyIds(List<String> propertyIds) {
        List<Map<String, Object>> propertyTypesAndUnits = new ArrayList<Map<String, Object>>();
        LOGGER.info("All given property ids for which we must load types and units: " + propertyIds);
        for (String id : propertyIds) {
            List<Map<String, Object>> typesAndUnitsList = plibDao.loadTypeAndUnitOfPropertyBy(id);
            for (Map<String, Object> currentTypesAndUnitMap : typesAndUnitsList) {
                propertyTypesAndUnits.add(currentTypesAndUnitMap);
            }
        }
        LOGGER.info("propertyTypesAndUnits: " + propertyTypesAndUnits);
        return propertyTypesAndUnits;
    }

    /**
     * Grabs all property ids from the property items.
     *
     * @param listOfItems the big list of all items
     * @return a simple list with all property ids only.
     *         todo extract from PropStringObjT getId into interface PropObj or something
     */
    private List<String> getPropertyIdsFromProperties(List<List<PropStringObjT>> listOfItems) {
        List<String> propertyIds = new ArrayList<String>();
        for (List<PropStringObjT> propList : listOfItems) {
            for (PropStringObjT prop : propList) {
                propertyIds.add(prop.getId().toString());
                LOGGER.info("property id value: " + prop.getId());
            }
        }
        return propertyIds;
    }

    /**
     * Purpose: Method load all konkrete Item instances.
     * <p/>
     * First loads all external ids by given query, then loads all items of this ids.
     * <p/>
     * The loading of the external ids is necessary, as the procedures provided by the plib database do not allow
     * to pass the IRDI.
     * It would be more efficient if that would be done in the database, however, we have to pass the
     * irdi, and load all external ids, which are special ids for each instance of an item (with irdi).
     * After that, each external id can be passed to the procedure, to load the data (all properties) of it.
     * Therefore we call each procedure separately to have the control.
     * There is a generic procedure, which call all these automatically, probably that can be done later.
     *
     * @return a list of all items
     */
    private List<List<PropStringObjT>> loadItems() {
        List<String> listOfExternalIds = loadExternalIds();
        // TODO load all other item database tables (DO_STRING, DO_NUMBER ...)
        List<List<PropStringObjT>> stringPropertyList = plibDao.loadStringPropertiesBy(listOfExternalIds);
        //List<List<Map<String, Object>>> numberPropertyList = plibDao.loadNumberPropertiesByExternalIds(listOfExternalIds);

        return stringPropertyList;
    }

    private List<List<PropStringObjT>> loadItems(Irdi irdi) {
        List<String> listOfExternalIds = loadExternalIds(irdi);
        // TODO load all other item database tables (DO_STRING, DO_NUMBER ...)
        List<List<PropStringObjT>> stringPropertyList = plibDao.loadStringPropertiesBy(listOfExternalIds);
        //List<List<Map<String, Object>>> numberPropertyList = plibDao.loadNumberPropertiesByExternalIds(listOfExternalIds);

        return stringPropertyList;
    }

    private List<String> loadExternalIds() {
        return plibDao.readExternalProductIdsBy(getEnrichedQuery());
    }

    private List<String> loadExternalIds(Irdi irdi) {
        return plibDao.readExternalProductIdsBy(irdi);
    }

    private boolean objectsExistInDatabase() {
        return plibDao.doObjectsExistsWithThis(getEnrichedQuery());
    }

    private boolean objectsExistInDatabase(Irdi irdi) {
        return plibDao.doObjectsExistsWithThis(irdi);
    }

    private boolean objectsExistInDatabase(List<Irdi> irdiList) {
        boolean objectsExists = false;
        for (Irdi irdi : irdiList) {
            if (plibDao.doObjectsExistsWithThis(irdi)) {
                return true;
            }
        }
        return objectsExists;
    }

    public PlibDao getPlibDao() {
        return plibDao;
    }

    public void setPlibDao(PlibDao plibDao) {
        this.plibDao = plibDao;
    }
}
