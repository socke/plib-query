package de.feu.plib.business.analyser;

import de.feu.plib.xml.query.QueryType;

/**
 * TODO: document file
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
