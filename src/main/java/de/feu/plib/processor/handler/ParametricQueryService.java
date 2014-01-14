package de.feu.plib.processor.handler;

import de.feu.plib.dao.PlibDao;
import de.feu.plib.dao.procedures.types.PropStringObjT;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.processor.analyser.Irdi;
import de.feu.plib.xml.catalogue.CatalogueType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * TODO document class ParametricQueryService
 */
public class ParametricQueryService extends AbstractQueryService {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(ParametricQueryService.class);

    @Autowired
    @Qualifier(value = "plibDao")
    private PlibDao plibDao;

    public ParametricQueryService() {
    }

    public ParametricQueryService(EnrichedQuery enrichedQuery) {
        this.setEnrichedQuery(enrichedQuery);
    }


    public CatalogueType loadDataWithIRDIOnly() {
        catalogueType = new CatalogueType();

        if (objectsExistInDatabase()) {
            LOGGER.trace("Objects exist in database");
            List<List<PropStringObjT>> listOfItems = loadItems();
            LOGGER.trace("Items loaded from db");

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);
            LOGGER.trace("property ids grabbed from properties");

           // List<Map<String, Object>> propertyTypesAndValues = loadTypesAndUnitsFromPropertyIds(propertyIds);
            LOGGER.trace("property types and values loaded from db");

            // now we must put all that data together, items + irdi + properties of the items + their irdis + all
            // properties and types. These must be mapped to the catalogue model
            //mapItemDataToCatalogue(listOfItems, propertyTypesAndValues);

            //filterCatalogueByPropertyIrdis();
        }

        return null;
    }

    public CatalogueType loadDataWithProjection() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    /**
     * Purpose: Method load all konkrete Item instances.
     * <p/>
     * First loads all external ids by given query, then loads all items of this ids.
     * <p/>
     * The loading of the external ids is necessary, as the procedures provided by the plib database do not allow
     * to pass the IRDI.
     * It would be more efficient if that would be done in the database, however, we have to pass the
     * irdi, and load all external ids, which are special ids for each instance of an item (with irdi).
     * After that, each external id can be passed to the procedure, to load the data (all properties) of it.
     * Therefore we call each procedure separately to have the control.
     * There is a generic procedure, which call all these automatically, probably that can be done later.
     *
     * @return a list of all items
     */
    private List<List<PropStringObjT>> loadItems() {
        List<String> listOfExternalIds = loadExternalIds();
        // TODO load all other item database tables (DO_STRING, DO_NUMBER ...)
        List<List<PropStringObjT>> stringPropertyList = plibDao.loadStringPropertiesBy(listOfExternalIds);
        //List<List<Map<String, Object>>> numberPropertyList = plibDao.loadNumberPropertiesByExternalIds(listOfExternalIds);

        return stringPropertyList;
    }

    private List<List<PropStringObjT>> loadItems(Irdi irdi) {
        List<String> listOfExternalIds = loadExternalIds(irdi);
        // TODO load all other item database tables (DO_STRING, DO_NUMBER ...)
        List<List<PropStringObjT>> stringPropertyList = plibDao.loadStringPropertiesBy(listOfExternalIds);
        //List<List<Map<String, Object>>> numberPropertyList = plibDao.loadNumberPropertiesByExternalIds(listOfExternalIds);

        return stringPropertyList;
    }

    private List<String> loadExternalIds() {
        return plibDao.readExternalProductIdsBy(getEnrichedQuery());
    }

    private List<String> loadExternalIds(Irdi irdi) {
        return plibDao.readExternalProductIdsBy(irdi);
    }

    private boolean objectsExistInDatabase() {
        return plibDao.doObjectsExistsWithThis(getEnrichedQuery());
    }

    private boolean objectsExistInDatabase(Irdi irdi) {
        return plibDao.doObjectsExistsWithThis(irdi);
    }
}
