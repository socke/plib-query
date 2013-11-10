package de.feu.plib.processor.handler;

import de.feu.plib.xml.catalogue.CatalogueType;

/**
 * Abstract Query Service class which holds template functionality for all Query Services
 */
public abstract class AbstractQueryService {

    CatalogueType catalogueType;
    /**
     * Loads the data from the database
     * @return the items as Catalogue
     */
    public abstract CatalogueType loadData();
}
