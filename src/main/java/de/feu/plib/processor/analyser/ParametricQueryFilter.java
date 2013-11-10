package de.feu.plib.processor.analyser;

import de.feu.plib.xml.query.QueryType;

/**
 * Parametric query filter, adds some additional information to the generic query from the client.
 */
public class ParametricQueryFilter implements QueryFilter {
    @Override
    public EnrichedQuery filter(QueryType query) {
        EnrichedQuery enrichedQuery = initialise(query);
        return enrichedQuery;
    }

    private EnrichedQuery initialise(QueryType query) {
        EnrichedQuery enrichedQuery = new EnrichedQuery(query);
        enrichedQuery.setType(QueryKind.PARAMETRIC);
        return enrichedQuery;
    }
}
