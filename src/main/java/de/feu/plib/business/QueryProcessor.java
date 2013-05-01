/**
 *
 */
package de.feu.plib.business;

import de.feu.plib.business.analyser.QueryFilter;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.query.QueryType;
import de.feu.plib.xml.value.BooleanValueType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

/**
 * The QueryProcessor is the central Pipe which controls the calling of the next filter. Thus it decides which
 * concrete filter to call next.
 */
public class QueryProcessor implements QueryPipe {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(QueryProcessor.class);

    private QueryFilter simpleQueryFilter;

    private QueryFilter parametricQueryFilter;


    @Override
    public CatalogueType filter(QueryType query) {
        // TODO use later a QueryFilter class
        if (isSimpleQuery(query)) {

            simpleQueryFilter.filter(query);

            // we need to read out what was filled, check what kind of simple query it is
            // does it have only an irdi? are properties selected?
            // do we have a known item given? then we must decide which DAO to call
            // and how to call it
            return createCatalogue();
        }
        if (isParametricQuery(query)) {
            parametricQueryFilter.filter(query);
        }
        // if it is neither a simple nor a parametric query, it is not supported, thus return emtpy result.
        LOGGER.warn("No query type recognized - empty catalogue will be returned");
        return emtpyCatalogue();
    }

    private CatalogueType emtpyCatalogue() {
        return new CatalogueType();
    }

    /**
     * TODO
     * Checks if the given query is a parametric query or not.
     *
     * @param query
     * @return
     */
    protected boolean isParametricQuery(QueryType query) {
        if (!CollectionUtils.isEmpty(query.getCharacteristicDataQueryExpression())) {
            return true;
        }
        return false;
    }

    /**
     * The query is only a simple query, if we have at least a class reference given and if we do not have a
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

    public void setSimpleQueryFilter(QueryFilter simpleQueryFilter) {
        this.simpleQueryFilter = simpleQueryFilter;
    }

    public QueryFilter getSimpleQueryFilter() {
        return simpleQueryFilter;
    }

    public QueryFilter getParametricQueryFilter() {
        return parametricQueryFilter;
    }

    public void setParametricQueryFilter(QueryFilter parametricQueryFilter) {
        this.parametricQueryFilter = parametricQueryFilter;
    }
}

