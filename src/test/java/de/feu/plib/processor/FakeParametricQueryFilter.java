package de.feu.plib.processor;

import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.processor.analyser.QueryFilter;
import de.feu.plib.xml.query.QueryType;

/**
 * FakeParametricQueryFilter for tests
 */
public class FakeParametricQueryFilter implements QueryFilter {
    @Override
    public EnrichedQuery filter(QueryType query) {
        QueryType queryType = new QueryType();
        queryType.setItemDescription("Fake Parametric Query Filter");
        return new EnrichedQuery(queryType);
    }
}
