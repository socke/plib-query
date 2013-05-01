package de.feu.plib.business.analyser;

import de.feu.plib.xml.query.QueryType;

/**
 * TODO: document file
 */
public interface QueryFilter {
    /**
     *
     * @param query
     */
    EnrichedQuery filter(QueryType query);
}
