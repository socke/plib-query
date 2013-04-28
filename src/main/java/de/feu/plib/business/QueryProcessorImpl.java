/**
 *
 */
package de.feu.plib.business;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.query.QueryType;
import de.feu.plib.xml.value.BooleanValueType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

/**
 *
 */
public class QueryProcessorImpl implements QueryProcessor {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(QueryProcessorImpl.class);

    @Override
    public CatalogueType analyse(QueryType query) {
        if (isSimpleQuery(query)) {
            // we need to read out what was filled
            // check what kind of simple query it is
            // does it have only a irdi?
            // are properties selected?
            // do we have a known item given?
            return createCatalogue();
        }
        if (isParametricQuery(query)) {

        }
        // if it is neither a simple nor a parametric query, it is not supported, thus return emtpy result.
        LOGGER.warn("No query type recognized - empty catalogue will be returned");
        return emtpyCatalogue();
    }

    private CatalogueType emtpyCatalogue() {
        return new CatalogueType();
    }

    protected boolean isParametricQuery(QueryType query) {
        return false;
    }

    /**
     * The query is only simple query, if we have at least a class reference given and if we do not have a
     * characteristic data query expression.
     *
     * @param query the query
     * @return true if it is a simple query expression.
     */
    protected boolean isSimpleQuery(QueryType query) {
        if (StringUtils.isNotEmpty(query.getClassRef()) && CollectionUtils.isEmpty(query.getCharacteristicDataQueryExpression())) {
            LOGGER.info("Is simple query - query: " + query);
            return true;
        }
        LOGGER.info("Is not a simple query - query: " + query);
        return false;
    }

    private CatalogueType createCatalogue() {
        ItemType item = new ItemType();
        item.setClassRef("abc");
        PropertyValueType propertyValueType = new PropertyValueType();

        BooleanValueType bvt = new BooleanValueType();
        bvt.setValue(true);
        propertyValueType.setBooleanValue(bvt);
        item.getPropertyValue().add(propertyValueType);
        CatalogueType catalogue = new CatalogueType();
        catalogue.getItem().add(item);

        return catalogue;
    }

}

