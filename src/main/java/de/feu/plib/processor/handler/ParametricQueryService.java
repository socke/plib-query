package de.feu.plib.processor.handler;

import de.feu.plib.dao.PlibDao;
import de.feu.plib.processor.analyser.EnrichedQuery;
import de.feu.plib.xml.catalogue.CatalogueType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
       return null;
    }

    public CatalogueType loadDataWithProjection() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}
