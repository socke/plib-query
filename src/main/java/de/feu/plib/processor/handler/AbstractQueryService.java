package de.feu.plib.processor.handler;

import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.xml.catalogue.CatalogueType;

/**
 * Abstract Query Service class which holds template functionality for all Query Services
 */
public abstract class AbstractQueryService {

    /** the catalogue holding the answer as return */
    CatalogueType catalogueType;

    private EnrichedQuery enrichedQuery;

    public EnrichedQuery getEnrichedQuery() {
        return enrichedQuery;
    }

    public void setEnrichedQuery(EnrichedQuery enrichedQuery) {
        this.enrichedQuery = enrichedQuery;
    }
}
