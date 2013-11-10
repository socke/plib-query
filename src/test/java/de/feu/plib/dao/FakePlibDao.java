package de.feu.plib.dao;

import de.feu.plib.dao.procedures.types.PropStringObjT;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.processor.analyser.Irdi;
import de.feu.plib.processor.analyser.QueryKind;
import de.feu.plib.xml.catalogue.CatalogueType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * TODO: document file
 */
public class FakePlibDao implements PlibDao {
    @Override
    public boolean doObjectsExistsWithThis(Irdi irdi) {
        return false;
    }

    @Override
    public int getNumberOfObjectsOfIrdi(Irdi irdi) {
        return 0;
    }

    @Override
    public List<String> getCompanyNames() {
        return null;
    }

    @Override
    public List<String> readExternalProductIdsBy(Irdi irdi) {
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

    @Override
    public List<List<Map<String, Object>>> loadStringPropertiesByExternalIds(List<BigDecimal> externalIds) {
        return null;
    }

    @Override
    public List<List<Map<String, Object>>> loadNumberPropertiesByExternalIds(List<BigDecimal> externalIds) {
        return null;
    }


    @Override
    public List<Map<String, Object>> loadTypeAndUnitOfPropertyBy(String propertyId) {
        return null;
    }

    @Override
    public List<List<PropStringObjT>> loadStringPropertiesBy(List<String> externalIds) {
        return Collections.emptyList();
    }


}
