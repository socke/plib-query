package de.feu.plib.processor.handler;

import de.feu.plib.dao.procedures.types.PropStringObjT;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.value.MeasureRangeValueType;
import de.feu.plib.xml.value.MeasureSingleNumberValueType;
import de.feu.plib.xml.value.NumericValueType;
import de.feu.plib.xml.value.RealValueType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract Query Service class which holds template functionality for all Query Services
 */
public abstract class AbstractQueryService {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(AbstractQueryService.class);

    /** the catalogue holding the answer as return */
    CatalogueType catalogueType;

    private EnrichedQuery enrichedQuery;

    public EnrichedQuery getEnrichedQuery() {
        return enrichedQuery;
    }

    public void setEnrichedQuery(EnrichedQuery enrichedQuery) {
        this.enrichedQuery = enrichedQuery;
    }

    protected boolean allPropertiesExistWithValuesIn(List<List<PropStringObjT>> listOfItems) {
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

    protected void setPropertyValue(String value, PropertyValueType propertyValue) {
        MeasureSingleNumberValueType msv = new MeasureSingleNumberValueType();
        RealValueType realValue = new RealValueType();
        realValue.setValue(Double.valueOf(value));
        msv.setRealValue(realValue);
        propertyValue.setMeasureSingleNumberValue(msv);
    }

    protected void setPropertyValueByBounds(PropertyValueType propertyValue, String bound1, String bound2) {
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

    protected boolean boundsAreSet(String bound1, String bound2) {
        return StringUtils.isNotEmpty(bound1) && StringUtils.isNotEmpty(bound2);
    }

    /**
     * Grabs all property ids from the property items.
     *
     * @param listOfItems the big list of all items
     * @return a simple list with all property ids only.
     *         todo extract from PropStringObjT getId into interface PropObj or something
     */
    protected List<String> getPropertyIdsFromProperties(List<List<PropStringObjT>> listOfItems) {
        List<String> propertyIds = new ArrayList<String>();
        for (List<PropStringObjT> propList : listOfItems) {
            for (PropStringObjT prop : propList) {
                propertyIds.add(prop.getId().toString());
                LOGGER.info("property id value: " + prop.getId());
            }
        }
        return propertyIds;
    }
}
