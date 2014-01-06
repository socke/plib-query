package de.feu.plib.processor.handler;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;

/**
 * TODO document class FakeSimpleQueryService
 */
public class FakeSimpleQueryService extends SimpleQueryService {
    @Override
    public CatalogueType loadDataWithIRDIOnly() {
        CatalogueType catalogue = new CatalogueType();
        ItemType item = new ItemType();
        item.setClassRef("simplequeryloaded");
        catalogue.getItem().add(item);
        return catalogue;
    }
}
