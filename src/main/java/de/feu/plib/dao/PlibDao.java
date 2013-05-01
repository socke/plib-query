package de.feu.plib.dao;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.xml.catalogue.CatalogueType;

import java.util.List;

public interface PlibDao {

    /**
     * For testing purposes
     * @return a list of company names from the DB
     */
    public List<String> getCompanyNames();

    /**
     * Loads the Objects from the database by the {@link EnrichedQuery}.
     *
     * @param enrichedQuery the enriched Query.
     * @return the catalogue holding the objects (items) the query.
     */
    public CatalogueType loadObjectsFrom(EnrichedQuery enrichedQuery);

}
