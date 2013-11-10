package de.feu.plib.processor.analyser;

import de.feu.plib.xml.query.QueryType;

/**
 * Query Filter interface. Defines an interface for all query filters.
 */
public interface QueryFilter {
    /**
     * filter method filters the query by some specific information, decides that it is a specific query, and marks
     * some additional information in the query by creating an {@link EnrichedQuery}.
     * @param query The passed query from the client.
     * @return enriched query with additional information about the content and type of the query
     */
    EnrichedQuery filter(QueryType query);
}
