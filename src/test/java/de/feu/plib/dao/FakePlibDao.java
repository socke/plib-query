package de.feu.plib.dao;

import de.feu.plib.dao.procedures.types.PropertyObjectT;
import de.feu.plib.processor.analyser.Irdi;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Fake Dao class used in unit tests
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
    public List<List<PropertyObjectT>> loadStringPropertiesBy(List<String> externalIds) {
        return Collections.emptyList();
    }

    @Override
    public List<List<PropertyObjectT>> loadNumberPropertiesBy(List<String> externalIds) {
        return Collections.emptyList();
    }


}
