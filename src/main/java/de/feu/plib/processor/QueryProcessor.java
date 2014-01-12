/**
 *
 */
package de.feu.plib.processor;

import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.processor.analyser.QueryFilter;
import de.feu.plib.processor.handler.ParametricQueryService;
import de.feu.plib.processor.handler.SimpleQueryService;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.error.ErrorType;
import de.feu.plib.xml.query.QueryType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier(value = "simpleQueryAnalyser")
    private QueryFilter simpleQueryFilter;

    @Autowired
    @Qualifier(value = "parametricQueryAnalyser")
    private QueryFilter parametricQueryFilter;

    @Autowired
    @Qualifier(value = "simpleQueryService")
    private SimpleQueryService simpleQueryService;

    @Autowired
    @Qualifier(value = "parametricQueryService")
    private ParametricQueryService parametricQueryService;

    @Override
    public CatalogueType filter(QueryType query) {

        if (isSimpleQuery(query)) {

            enrichQuery(query, simpleQueryFilter);

            if (isIRDIOnlyQuery(query)) {
                return simpleQueryService.loadDataWithIRDIOnly();
            }
            if (isItemOnlyQuery(query)) {
                return simpleQueryService.loadDataWithItemsOnly();
            }
            if (isProjectionQuery(query)) {
                return simpleQueryService.loadDataWithProjection();
            }
            if (isProjectionOnItemQuery(query)) {
                // TODO implement path
            }
        }
        if (isParametricQuery(query)) {

            enrichQuery(query, parametricQueryFilter);

            if (isIRDIOnlyQuery(query)) {
                return parametricQueryService.loadDataWithIRDIOnly();
            }
            // items are ignored, does not make sense, so handle projection with item and irdi as projection case
            if (isProjectionQuery(query) || isProjectionOnItemWithIRDI(query)) {
                return parametricQueryService.loadDataWithProjection();
            }
        }
        // if it is neither a simple nor a parametric query, it is not supported, thus return emtpy result.
        LOGGER.warn("No query type recognized - empty catalogue will be returned");

        CatalogueType catalogueType = emptyCatalogue();
        ErrorType errorType = new ErrorType();
        errorType.setShortErrorMessage("No query type recognized - empty catalogue will be returned");

        return catalogueType;
    }

    private void enrichQuery(QueryType query, QueryFilter queryFilter) {
        EnrichedQuery enrichedQuery = queryFilter.filter(query);
        simpleQueryService.setEnrichedQuery(enrichedQuery);
    }

    private CatalogueType emptyCatalogue() {
        return new CatalogueType();
    }

    /**
     * Checks if the given query is a parametric query or not.
     *
     * @param query the query to check
     * @return true if it is a parametric query expression otherwise false
     */
    protected boolean isParametricQuery(QueryType query) {
        if (!CollectionUtils.isEmpty(query.getCharacteristicDataQueryExpression())) {
            LOGGER.info("Is parametric query - query: " + query);
            return true;
        }
        LOGGER.info("Is not a parametric query - query: " + query);
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
        if (CollectionUtils.isEmpty(query.getCharacteristicDataQueryExpression())) {
            LOGGER.info("Is simple query - query: " + query);
            return true;
        }
        LOGGER.info("Is not a simple query - query: " + query);
        return false;
    }

    private boolean isProjectionOnItemWithIRDI(QueryType query) {
        return StringUtils.isNotEmpty(query.getClassRef()) && !CollectionUtils.isEmpty(query.getPropertyRef()) && query.getItem() != null;
    }

    private boolean isProjectionOnItemQuery(QueryType query) {
        return StringUtils.isEmpty(query.getClassRef()) && !CollectionUtils.isEmpty(query.getPropertyRef()) && query.getItem() != null;
    }

    private boolean isProjectionQuery(QueryType query) {
        return StringUtils.isNotEmpty(query.getClassRef()) && !CollectionUtils.isEmpty(query.getPropertyRef()) && query.getItem() == null;
    }

    private boolean isItemOnlyQuery(QueryType query) {
        return StringUtils.isEmpty(query.getClassRef()) && CollectionUtils.isEmpty(query.getPropertyRef()) && query.getItem() != null;
    }

    private boolean isIRDIOnlyQuery(QueryType query) {
        return StringUtils.isNotEmpty(query.getClassRef()) && CollectionUtils.isEmpty(query.getPropertyRef()) && query.getItem() == null;
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

    public SimpleQueryService getSimpleQueryService() {
        return simpleQueryService;
    }

    public void setSimpleQueryService(SimpleQueryService simpleQueryService) {
        this.simpleQueryService = simpleQueryService;
    }
}

