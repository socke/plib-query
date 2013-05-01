package de.feu.plib.dao;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.business.analyser.QueryKind;
import de.feu.plib.xml.catalogue.CatalogueType;

import java.util.List;

/**
 * TODO: document file
 */
public class PlibDaoFake implements PlibDao {
    @Override
    public List<String> getCompanyNames() {
        return null;
    }

    @Override
    public CatalogueType loadObjectsFrom(EnrichedQuery enrichedQuery) {
        if (QueryKind.SIMPLE.equals(enrichedQuery.getType())) {

        }
        if (QueryKind.PARAMETRIC.equals(enrichedQuery.getType())) {

        }
        return null;
    }
}
