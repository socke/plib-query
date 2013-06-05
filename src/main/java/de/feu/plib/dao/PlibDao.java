package de.feu.plib.dao;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.business.analyser.Irdi;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.query.QueryType;

import java.util.List;

public interface PlibDao {


    /**
     * Checks if objects of the IRDI given in the {@link Irdi} exists in the database.
     * The objects are instances of the class defined with the IRDI.
     *
     * @param irdi the irdi
     * @return false if no objects exist, true if they exist.
     */
    boolean doObjectsExistsWithThis(Irdi irdi);


    /**
     * Returns the number of objects of the given irdi.
     * @param irdi the irdi
     * @return the number of objects
     */
    int getNumberOfObjectsOfIrdi(Irdi irdi);

    /**
     * For testing purposes
     * @return a list of company names from the DB
     */
    List<String> getCompanyNames();

    /**
     * Loads the external product ids from the passed irdi.
     *
     * @param irdi of the class where
     * @return list containing external product ids.
     */
    List<String> readExternalProductIdsBy(Irdi irdi);

    /**
     * Loads the Objects from the database by the {@link EnrichedQuery}.
     *
     * @param enrichedQuery the enriched Query.
     * @return the catalogue holding the objects (items) the query.
     */
    CatalogueType loadObjectsFrom(EnrichedQuery enrichedQuery);

    /**
     * Loads the string properties by the given external ids in the string list.
     * The external ids are needed as this is predefined by the procedures as IN parameter.
     * They look like "EXT_4000001", for the testdata usually with prefix EXT_
     *
     * @param externalIds the external ids for which the string properties should be loaded
     * @return a list of propertyvaluetype
     */
    List<PropertyValueType> loadStringPropertiesByExternalIds(List<String> externalIds);

    /**
     * Loads the number Properties by given external ids in the string list.
     * @see #loadStringPropertiesByExternalIds(java.util.List)
     * @param externalIds
     * @return a list of propertyvaluetype
     */
    List<PropertyValueType> loadNumberPropertiesByExternalIds(List<String> externalIds);


}
