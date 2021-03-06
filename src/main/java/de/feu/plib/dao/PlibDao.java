package de.feu.plib.dao;

import de.feu.plib.dao.procedures.types.PropertyObjectT;
import de.feu.plib.processor.analyser.Irdi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
     * Loads the string properties by the given external ids in the string list.
     * The external ids are needed as this is predefined by the procedures as IN parameter.
     * They look like "EXT_4000001", for the testdata usually with prefix EXT_
     *
     * @deprecated use {@link #loadStringPropertiesBy(java.util.List)} instead. It is deprecated as we have use a plain
     * SQL string for receiving the data from database. New Method loads it via Procedure (recommended)
     *
     * @param externalIds the external ids for which the string properties should be loaded
     * @return a list of propertyvaluetype
     */
    @Deprecated
    List<List<Map<String, Object>>> loadStringPropertiesByExternalIds(List<BigDecimal> externalIds);

    /**
     * Loads the number Properties by given external ids in the string list.
     * @see #loadStringPropertiesByExternalIds(java.util.List)
     * @param externalIds
     * @return a list of propertyvaluetype
     */
    List<List<Map<String, Object>>> loadNumberPropertiesByExternalIds(List<BigDecimal> externalIds);


    /**
     * Load the units and the types of a property by given id.
     */
    List<Map<String, Object>> loadTypeAndUnitOfPropertyBy(String propertyId);


    /**
     * Loads the string properties by the given external ids in the string list.
     * The external ids are needed as this is predefined by the procedures as IN parameter.
     * They look like "EXT_4000001", for the testdata usually with prefix EXT_
     * Makes usage of the procedure calls, so thats the reason for the PropStringObjT type, which holds the
     * item values as defined in the procedure.
     *
     * @param externalIds the external ids for which the string properties should be loaded
     * @return a list of propertyvaluetype
     */
    List<List<PropertyObjectT>> loadStringPropertiesBy(List<String> externalIds);

    /**
     * Loads the string properties by the given external ids in the number list.
     * The external ids are needed as this is predefined by the procedures as IN parameter.
     * They look like "EXT_4000001", for the testdata usually with prefix EXT_
     * Makes usage of the procedure calls, so thats the reason for the PropNumberObjT type, which holds the
     * item values as defined in the procedure.
     *
     * @param externalIds the external ids for which the string properties should be loaded
     * @return a list of propertyvaluetype
     */
    List<List<PropertyObjectT>> loadNumberPropertiesBy(List<String> externalIds);
}
