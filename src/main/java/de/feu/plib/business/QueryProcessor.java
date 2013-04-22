/**
 * 
 */
package de.feu.plib.business;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.query.QueryType;

/**
 * This is the start class which is the processing entry point. It starts the processing of the given query.
 *
 * We use the pipes and filters pattern to analyse and then process the query.
 * The steps are as follows:
 * <ul>
 *     <li>Check the query through the QueryFilters</li>
 *     <li>This will be done by passing the initial QueryType instance and each filter
 *     checks if its filter matches</li>
 *     <li>If the filter matches, then </li>
 * </ul>
 */
public interface QueryProcessor {
    /**
     * Starts the analysing of a query.
     * These are the steps of query analysis and processing:
     * <ul>
     *     <li>Check if it is a simple query, which means that an IRDI class reference and zero, one or more property IRDIs
     *     and zero, one or more known items with known characteristic data.</li>
     *     <li>It is only a simple query, if no characteristic_data_query_expression exists. (comparable to where clause in sql)</li>
     *
     * </ul>
     *
     * @param query the query to analyse and to process.
     * @return the result catalog holding items which are the result of the query.
     */
    public CatalogueType analyse(QueryType query);
}
