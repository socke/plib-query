package de.feu.plib.processor;

import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.processor.analyser.QueryFilter;
import de.feu.plib.xml.query.QueryType;

/**
 * FakeSimpleQueryFilter for testing
 */
public class FakeSimpleQueryFilter implements QueryFilter {
    @Override
    public EnrichedQuery filter(QueryType query) {
        QueryType queryType = new QueryType();
        queryType.setItemDescription("Fake Simple Query Filter");
        return new EnrichedQuery(queryType);
    }
}
