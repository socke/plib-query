package de.feu.plib.business.filter;

import de.feu.plib.xml.query.QueryType;

/**
 * TODO: document file
 */
public interface QueryFilter {
    public QueryResult filter(QueryType queryType);
}
