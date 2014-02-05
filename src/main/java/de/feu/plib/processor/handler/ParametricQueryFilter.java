package de.feu.plib.processor.handler;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.query.CharacteristicDataQueryExpressionType;

/**
 * TODO document class ParametricQueryFilter
 */
public interface ParametricQueryFilter {
    public CatalogueType filter(CatalogueType catalogueType, CharacteristicDataQueryExpressionType queryExpressionType);
}
