package de.feu.plib.processor.handler;

import de.feu.plib.dao.procedures.types.PropStringObjT;
import de.feu.plib.processor.analyser.BaseIrdi;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.dao.PlibDao;
import de.feu.plib.processor.analyser.Irdi;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import de.feu.plib.xml.value.*;
import org.apache.commons.lang.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.*;

/**
 * SimpleQueryService handles simple queries.
 * Loads the data from the database and maps the data to the catalogue structure for returning to the client.
 */
public class SimpleQueryService extends AbstractQueryService {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(SimpleQueryService.class);

    @Autowired
    @Qualifier(value = "plibDao")
    private PlibDao plibDao;

    public SimpleQueryService() {
    }

    public SimpleQueryService(EnrichedQuery enrichedQuery) {
        this.setEnrichedQuery(enrichedQuery);
    }

    /**
     * Checks if objects exists in database, then load the items of the objects and fills each item
     * with its properties.
     * Then add all the items to the catalogue object
     *
     * @return CatalogueType holding all items with properties
     */
    public CatalogueType loadDataWithIRDIOnly() {

        catalogueType = new CatalogueType();

        if (objectsExistInDatabase()) {
            LOGGER.trace("Objects exist in database");
            List<List<PropStringObjT>> listOfItems = loadItems();
            LOGGER.trace("Items loaded from db");

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);
            LOGGER.trace("property ids grabbed from properties");

            List<Map<String, Object>> propertyTypesAndValues = loadTypesAndUnitsFromPropertyIds(propertyIds, plibDao);
            LOGGER.trace("property types and values loaded from db");

            // now we must put all that data together, items + irdi + properties of the items + their irdis + all
            // properties and types. These must be mapped to the catalogue model
            mapItemDataToCatalogue(listOfItems, propertyTypesAndValues);
        }
        return catalogueType;
    }

    /**
     * First get irdis of class_ref of all items.
     * Load the data from the items with that class_refs.
     * Check if the the properties match.
     * If so then fill the catalogue with all items, if not the data does not match
     * (validation)
     *
     * @return CatalogueType holding all items with properties
     */
    public CatalogueType loadDataWithItemsOnly() {

        catalogueType = new CatalogueType();

        Irdi irdi = new BaseIrdi(getEnrichedQuery().getQuery().getItem().getClassRef());

        if (objectsExistInDatabase(irdi)) {
            LOGGER.trace("Objects exist in database");
            List<List<PropStringObjT>> listOfItems = loadItems(irdi);
            LOGGER.trace("Items loaded from db");

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);
            LOGGER.trace("property ids grabbed from properties");

            List<Map<String, Object>> propertyTypesAndUnits = loadTypesAndUnitsFromPropertyIds(propertyIds, plibDao);
            LOGGER.trace("property types and values loaded from db");

            //  get all property data from item in query then check if we have an item in result with the property ref and the value
            if (allPropertiesExistWithValuesIn(listOfItems)) {
                // if so we must put all that data together, items + irdi + properties of the items + their irdis + all
                // properties and units. These must be mapped to the catalogue model.
                mapItemDataToCatalogue(listOfItems, propertyTypesAndUnits, irdi.getIrdi());
            }
        }

        return catalogueType;
    }

    public CatalogueType loadDataWithProjection() {
        catalogueType = new CatalogueType();

        if (objectsExistInDatabase()) {
            LOGGER.trace("Objects exist in database");
            List<List<PropStringObjT>> listOfItems = loadItems();
            LOGGER.trace("Items loaded from db");

            List<String> propertyIds = getPropertyIdsFromProperties(listOfItems);
            LOGGER.trace("property ids grabbed from properties");

            List<Map<String, Object>> propertyTypesAndValues = loadTypesAndUnitsFromPropertyIds(propertyIds, plibDao);
            LOGGER.trace("property types and values loaded from db");

            // now we must put all that data together, items + irdi + properties of the items + their irdis + all
            // properties and types. These must be mapped to the catalogue model
            mapItemDataToCatalogue(listOfItems, propertyTypesAndValues);

            filterCatalogueByPropertyIrdis();
        }
        return catalogueType;
    }

    /**
     * This method does the projection by filtering the properties in the catalogue from the database by the
     * given properties from the query.
     * You can just call the method after you loaded all the data from the database into {@link #catalogueType},
     * then all not defined properties will be deleted from the {@link #catalogueType}.
     *
     * Note: you can only remove items from Lists which you currently iterate when using {@link Iterator}
     */
    private void filterCatalogueByPropertyIrdis() {
        List<String> filteredProperties = getEnrichedQuery().getQuery().getPropertyRef();

        if (null != filteredProperties) {
            List<ItemType> itemTypes = catalogueType.getItem();
            for (Iterator<ItemType> item = itemTypes.iterator(); item.hasNext(); ) {
                for (Iterator<PropertyValueType> prop = item.next().getPropertyValue().iterator(); prop.hasNext(); ) {
                    if (!filteredProperties.contains(prop.next().getPropertyRef())) {
                        prop.remove();
                    }
                }
            }
        }
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

    public PlibDao getPlibDao() {
        return plibDao;
    }

    public void setPlibDao(PlibDao plibDao) {
        this.plibDao = plibDao;
    }
}
