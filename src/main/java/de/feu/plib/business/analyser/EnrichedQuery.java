package de.feu.plib.business.analyser;

import de.feu.plib.xml.query.QueryType;
import org.apache.commons.lang.StringUtils;

/**
 * Bean holds the original query object.
 * Will be enriched with data from the analysis and filtering process.
 * With that data a later filter can decide what to do next based on the {@link QueryType} and the additional enriched data.
 */
public class EnrichedQuery implements Irdi {
    /** original query */
    private QueryType query;

    /** holding the query type */
    private QueryKind type;

    public EnrichedQuery(QueryType query) {
        this.query = query;
    }

    public QueryType getQuery() {
        return query;
    }

    public void setQuery(QueryType query) {
        this.query = query;
    }

    public QueryKind getType() {
        return type;
    }

    public void setType(QueryKind type) {
        this.type = type;
    }

    /** {@inheritDoc} */
    @Override
    public String getIrdi() {
        if (StringUtils.isNotEmpty(query.getClassRef())) {
            return query.getClassRef();
        }
        return "";
    }
}