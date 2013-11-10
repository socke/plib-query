package de.feu.plib.processor.analyser;

import de.feu.plib.xml.query.QueryType;

/**
 * Simple query filter responsible for filtering simple queries.
 * Add additional information to the query.
 */
public class SimpleQueryFilter implements QueryFilter {
    @Override
    public EnrichedQuery filter(QueryType query) {
        EnrichedQuery enrichedQuery = initialise(query);
        return enrichedQuery;
    }

    private EnrichedQuery initialise(QueryType query) {
        EnrichedQuery enrichedQuery = new EnrichedQuery(query);
        enrichedQuery.setType(QueryKind.SIMPLE);
        return enrichedQuery;
    }
}
