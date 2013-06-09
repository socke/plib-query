package de.feu.plib.business.handler;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.dao.PlibDao;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.catalogue.PropertyValueType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;
import java.util.List;

/**
 * TODO: document file
 */
public class SimpleQueryService {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(SimpleQueryService.class);

    @Autowired
    @Qualifier(value = "plibDao")
    private PlibDao plibDao;

    private EnrichedQuery enrichedQuery;

    public EnrichedQuery getEnrichedQuery() {
        return enrichedQuery;
    }

    public void setEnrichedQuery(EnrichedQuery enrichedQuery) {
        this.enrichedQuery = enrichedQuery;
    }

    public SimpleQueryService() {}

    public SimpleQueryService(EnrichedQuery enrichedQuery) {
        this.enrichedQuery = enrichedQuery;
    }

    /**
     * Checks if objects exists in database, then load the items of the objects and fills each item
     * with its properties.
     * Then add all the items to the catalogue object
     * @return CatalogueType holding all items with properties
     */
    public CatalogueType loadData() {
        CatalogueType catalogueType = new CatalogueType();
        if (objectsExistInDatabase()) {
            List<List<PropertyValueType>> listOfItems = loadItems();
            for (List<PropertyValueType> propList : listOfItems) {
                ItemType item = fillItemWithProperties(propList);
                catalogueType.getItem().add(item);
            }
        }
        return catalogueType;
    }

    private ItemType fillItemWithProperties(List<PropertyValueType> propList) {
        ItemType item = new ItemType();
        for (PropertyValueType propertyValueType : propList) {
            item.getPropertyValue().add(propertyValueType);
        }
        return item;
    }

    private List<List<PropertyValueType>> loadItems() {
        List<BigDecimal> listOfExternalIds = loadExternalIds();
        return plibDao.loadStringPropertiesByExternalIds(listOfExternalIds);
    }

    private List<BigDecimal> loadExternalIds() {
        return plibDao.readExternalProductIdsBy(enrichedQuery);
    }

    private boolean objectsExistInDatabase() {
        return plibDao.doObjectsExistsWithThis(enrichedQuery);
    }

    public PlibDao getPlibDao() {
        return plibDao;
    }

    public void setPlibDao(PlibDao plibDao) {
        this.plibDao = plibDao;
    }
}
