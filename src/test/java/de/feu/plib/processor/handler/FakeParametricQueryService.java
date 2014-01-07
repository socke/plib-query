package de.feu.plib.processor.handler;

import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;

/**
 * TODO document class FakeParametricQueryService
 */
public class FakeParametricQueryService extends AbstractQueryService {
    @Override
    public CatalogueType loadDataWithIRDIOnly() {
        return new CatalogueType();
    }

    @Override
    public CatalogueType loadDataWithItemsOnly() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CatalogueType loadDataWithProjection() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
