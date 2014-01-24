package de.feu.plib.processor.handler;

import de.feu.plib.dao.PlibDao;
import de.feu.plib.dao.procedures.types.PropStringObjT;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.processor.analyser.Irdi;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.query.CharacteristicDataQueryExpressionType;
import de.feu.plib.xml.query.PropertyReferenceType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ParametricQueryService handles parametric (search) queries.
 * Loads the data from the database and maps the data to the catalogue structure for returning to the client.
 */
public class ParametricQueryService extends AbstractQueryService {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(ParametricQueryService.class);

    @Autowired
    @Qualifier(value = "plibDao")
    private PlibDao plibDao;

    public ParametricQueryService() {
    }

    public ParametricQueryService(EnrichedQuery enrichedQuery) {
        this.setEnrichedQuery(enrichedQuery);
    }


    public CatalogueType loadDataWithIRDIOnly() {
        catalogueType = new CatalogueType();

        if (objectsExistInDatabase()) {
            LOGGER.trace("Objects exist in database");
            List<List<PropStringObjT>> listOfItems = loadItems();
            LOGGER.trace("Items loaded from db");

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);
            LOGGER.trace("property ids grabbed from properties");

            List<Map<String, Object>> propertyTypesAndValues = loadTypesAndUnitsFromPropertyIds(propertyIds, plibDao);
            LOGGER.trace("property types and values loaded from db");

            // now we must put all that data together, items + irdi + properties of the items + their irdis + all
            // properties and types. These must be mapped to the catalogue model
            mapItemDataToCatalogue(listOfItems, propertyTypesAndValues);

            List<CharacteristicDataQueryExpressionType> queryExpression = getEnrichedQuery().getQuery().getCharacteristicDataQueryExpression();
            for (CharacteristicDataQueryExpressionType qe : queryExpression) {
                if (qe.getRange() != null) {
                    PropertyReferenceType propertyReference = qe.getRange().getPropertyReference();
                    filterCatalogueByRange(qe.getRange().getMinValue(), qe.getRange().getMaxValue(), propertyReference);
                }
                if (qe.getAnd() != null) {

                }
                if (qe.getCardinality() != null) {

                }
                if (qe.getDataEnvironment() != null) {

                }
                if (qe.getNot() != null) {

                }
                if (qe.getOr() != null) {

                }
                if (qe.getStringPattern() != null) {

                }
                if (qe.getStringSize() != null) {

                }
                if (qe.getSubset() != null) {

                }
            }
        }
        return catalogueType;
    }


    /**
     * Filters the catalogue by a given range.
     * Will remove the item from the catalogue if it is not in range of max and min.
     * Every double and float types in the catalogue have to be checked.
     * Currently we check real, measuresinglenumbervalue and integer value
     * TODO: probably complex value, currency value, and subitem types have to be considered.
     *
     * @param min minimum value of the range
     * @param max maximum value of the range
     */
    private void filterCatalogueByRange(Float min, Float max, PropertyReferenceType propertyReference) {

        List<ItemType> itemTypes = catalogueType.getItem();
        for (Iterator<ItemType> itemIterator = itemTypes.iterator(); itemIterator.hasNext(); ) {
            boolean propertyFound = false;
            boolean inRange = false;

            for (Iterator<PropertyValueType> propertyIterator = itemIterator.next().getPropertyValue().iterator(); propertyIterator.hasNext(); ) {
                // check if property matches
                PropertyValueType valueType = propertyIterator.next();
                if (valueType.getPropertyRef().equals(propertyReference.getPropertyRef())) {
                    propertyFound = true;
                    inRange = isMeasureSingleNumberValueInRange(min, max, valueType, propertyReference);
                }

                //checkRealValue(min, max, propertyIterator, propertyReference);
                //checkIntegerValue(min, max, propertyIterator, propertyReference);
            }
            if (!propertyFound || !inRange) {
                itemIterator.remove();
            }
        }
    }

    private void checkIntegerValue(Float min, Float max, Iterator<PropertyValueType> propertyIterator, PropertyReferenceType propertyReference) {
        PropertyValueType valueType = propertyIterator.next();
        if (valueType.getIntegerValue() != null) {
            double realValue = (double) valueType.getIntegerValue().getValue();
            isInRange(min, max, propertyIterator, realValue, propertyReference.getPropertyRef(), valueType.getPropertyRef());
        }
    }

    private boolean isMeasureSingleNumberValueInRange(Float min, Float max, PropertyValueType valueType, PropertyReferenceType propertyReference) {

        if (valueType.getMeasureSingleNumberValue() != null && valueType.getMeasureSingleNumberValue().getRealValue() != null && propertyReference.getPropertyRef() != null) {
            double realValue = valueType.getMeasureSingleNumberValue().getRealValue().getValue();

            // real range check - value between given min max
            if (min != null && max != null) {
                if (Double.valueOf(min) <= realValue && Double.valueOf(max) >= realValue) {
                    return true;
                }
            }

            // check for value smaller than max
            if (min == null && max != null) {
                if (Double.valueOf(max) >= realValue) {
                    return true;
                }
            }

            // check for value bigger than min
            if (min != null && max == null) {
                if (Double.valueOf(min) <= realValue) {
                    return true;
                }
            }
        }
        return false;
    }


    private void checkRealValue(Float min, Float max, Iterator<PropertyValueType> prop, PropertyReferenceType propertyReference) {
        PropertyValueType valueType = prop.next();
        if (valueType.getRealValue() != null) {
            double realValue = valueType.getRealValue().getValue();
            isInRange(min, max, prop, realValue, propertyReference.getPropertyRef(), valueType.getPropertyRef());
        }
    }

    private boolean isInRange(Float min, Float max, Iterator<PropertyValueType> propertyIterator, double realValue, String propertyRef, String valuePropertyRef) {
        if ((propertyRef.equals(valuePropertyRef)) && !(Double.valueOf(min) <= realValue && Double.valueOf(max) >= realValue)) {
            return true;
        }
        return false;
    }

    public CatalogueType loadDataWithProjection() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    /**
     * Purpose: Method load all concrete Item instances.
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
}
