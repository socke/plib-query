package de.feu.plib.dao;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.business.analyser.Irdi;
import de.feu.plib.xml.catalogue.CatalogueType;

import java.util.List;

public interface PlibDao {


    /**
     * Checks if objects of the IRDI given in the {@link Irdi} exists in the database.
     * The objects are instances of the class defined with the IRDI.
     *
     * @param irdi the irdi
     * @return false if no objects exist, true if they exist.
     */
    public boolean doObjectsExistsWithThis(Irdi irdi);


    /**
     * Returns the number of objects of the given irdi.
     * @param irdi the irdi
     * @return the number of objects
     */
    public int getNumberOfObjectsOfIrdi(Irdi irdi);

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
