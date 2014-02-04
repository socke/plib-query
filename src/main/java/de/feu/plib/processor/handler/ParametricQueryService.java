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

import java.util.ArrayList;
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

            filterResultByQueryExpression();
        }
        return catalogueType;
    }

    public CatalogueType loadDataWithProjection() {
        return loadDataWithIRDIOnly();
    }

    private void filterResultByQueryExpression() {
        List<CharacteristicDataQueryExpressionType> queryExpression = getEnrichedQuery().getQuery().getCharacteristicDataQueryExpression();
        for (CharacteristicDataQueryExpressionType qe : queryExpression) {
            filterRange(qe);
            filterAnd(qe);
            filterCardinality(qe);
            filterDataEnvironment(qe);
            filterNot(qe);
            filterOr(qe);
            filterStringPattern(qe);
            filterStringSize(qe);
            filterSubset(qe);
        }
    }

    private void filterSubset(CharacteristicDataQueryExpressionType qe) {
        if (qe.getSubset() != null) {

        }
    }

    private void filterStringSize(CharacteristicDataQueryExpressionType qe) {
        if (qe.getStringSize() != null) {

        }
    }

    private void filterStringPattern(CharacteristicDataQueryExpressionType qe) {
        if (qe.getStringPattern() != null) {

        }
    }

    private void filterOr(CharacteristicDataQueryExpressionType qe) {
        if (qe.getOr() != null) {

        }
    }

    private void filterNot(CharacteristicDataQueryExpressionType qe) {
        if (qe.getNot() != null) {

        }
    }

    private void filterDataEnvironment(CharacteristicDataQueryExpressionType qe) {
        if (qe.getDataEnvironment() != null) {

        }
    }

    private void filterCardinality(CharacteristicDataQueryExpressionType qe) {
        if (qe.getCardinality() != null) {

        }
    }

    private void filterRange(CharacteristicDataQueryExpressionType qe) {
        if (qe.getRange() != null) {
            PropertyReferenceType propertyReference = qe.getRange().getPropertyReference();
            filterCatalogueByRange(qe.getRange().getMinValue(), qe.getRange().getMaxValue(), propertyReference, qe.getRange().isIsInclusive());
        }
    }

    private void filterAnd(CharacteristicDataQueryExpressionType qe) {
        if (qe.getAnd() != null) {
            boolean allOperandsEvaluatedToTrue = true;
            List<CharacteristicDataQueryExpressionType> operands = qe.getAnd().getOperand();
            for (CharacteristicDataQueryExpressionType queryExpressionType : operands) {
                filterRange(queryExpressionType);
                //allOperandsEvaluatedToTrue = operandIsInRange(queryExpressionType);
                filterAnd(queryExpressionType);
            }
            if (allOperandsEvaluatedToTrue) {
                return;
            }

        }
    }

    private boolean operandIsInRange(CharacteristicDataQueryExpressionType qe) {
        if (qe.getRange() != null) {
            PropertyReferenceType propertyReference = qe.getRange().getPropertyReference();
            return isInRangeOfCatalogue(qe.getRange().getMinValue(), qe.getRange().getMaxValue(), propertyReference, qe.getRange().isIsInclusive());
        }
        return false;
    }

    private boolean isInRangeOfCatalogue(Float minValue, Float maxValue, PropertyReferenceType propertyReference, boolean isInclusive) {
        return false;
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
    private void filterCatalogueByRange(Float min, Float max, PropertyReferenceType propertyReference, boolean isInclusive) {
        List<ItemType> itemsToDelete = new ArrayList<ItemType>();

        List<ItemType> itemTypes = catalogueType.getItem();
        for (Iterator<ItemType> itemIterator = itemTypes.iterator(); itemIterator.hasNext(); ) {
            boolean propertyFound = false;
            boolean inRange = false;

            ItemType currentItem = itemIterator.next();
            for (Iterator<PropertyValueType> propertyIterator = currentItem.getPropertyValue().iterator(); propertyIterator.hasNext(); ) {
                // check if property matches
                PropertyValueType valueType = propertyIterator.next();
                if (valueType.getPropertyRef().equals(propertyReference.getPropertyRef())) {
                    propertyFound = true;
                    inRange = isMeasureSingleNumberValueInRange(min, max, valueType, propertyReference, isInclusive);
                    //checkRealValue(min, max, propertyIterator, propertyReference);
                    //checkIntegerValue(min, max, propertyIterator, propertyReference);
                }
            }
            if (!propertyFound || !inRange) {
                itemIterator.remove();
                itemsToDelete.add(currentItem);
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

    private boolean isMeasureSingleNumberValueInRange(Float min, Float max, PropertyValueType valueType, PropertyReferenceType propertyReference, boolean isInclusive) {

        if (valueType.getMeasureSingleNumberValue() != null && valueType.getMeasureSingleNumberValue().getRealValue() != null && propertyReference.getPropertyRef() != null) {
            double realValue = valueType.getMeasureSingleNumberValue().getRealValue().getValue();

            // if inclusive then the bounds are included in the value search
            if (isInclusive) {
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
            } else {
                // real range check - value between given min max
                if (min != null && max != null) {
                    if (Double.valueOf(min) < realValue && Double.valueOf(max) > realValue) {
                        return true;
                    }
                }

                // check for value smaller than max
                if (min == null && max != null) {
                    if (Double.valueOf(max) > realValue) {
                        return true;
                    }
                }

                // check for value bigger than min
                if (min != null && max == null) {
                    if (Double.valueOf(min) < realValue) {
                        return true;
                    }
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
