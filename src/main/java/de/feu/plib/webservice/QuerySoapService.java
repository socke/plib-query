package de.feu.plib.webservice;

import de.feu.plib.processor.QueryPipe;
import de.feu.plib.xml.catalogue.CatalogueType;
import de.feu.plib.xml.catalogue.ItemType;
import de.feu.plib.xml.query.QueryType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * TODO document class QuerySOAPService
 */
@WebService
@SOAPBinding(style=SOAPBinding.Style.DOCUMENT)
public class QuerySOAPService {

    /** Query processor instance */
    private QueryPipe queryPipe;

    /** Application context for receiving beans */
    private ApplicationContext context;

    public QuerySOAPService() {
        this.context = initializeContext();
        this.queryPipe = getQueryPipe();
    }

    public CatalogueType query(QueryType query) {
        CatalogueType catalogue = queryPipe.filter(query);
        return catalogue;
    }

    private ClassPathXmlApplicationContext initializeContext() {
        return new ClassPathXmlApplicationContext(
                "beans.xml");
    }

    private QueryPipe getQueryPipe() {
        QueryPipe queryPipe =  (QueryPipe) context.getBean("queryPipe");
        return queryPipe;
    }
}
