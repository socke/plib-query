package de.feu.plib;

import de.feu.plib.processor.QueryPipe;
import de.feu.plib.webservice.rest.QueryRESTService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * Query Bean which is the model and controller of query page.
 */
@ManagedBean
@SessionScoped
public class QueryBean implements Serializable {

    /**
     * Logger instance
     */
    private static Logger LOGGER = Logger.getLogger(QueryBean.class);

    private String query;

    private String response;

    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void sendQuery() throws IOException {
        String fileName = FilenameUtils.getName(uploadedFile.getName());
        String contentType = uploadedFile.getContentType();
        byte[] bytes = uploadedFile.getBytes();
        String query = new String(bytes, "UTF-8");

        this.query = query;

        QueryRESTService queryRESTService = new QueryRESTService();
        response = queryRESTService.query(this.query);

        LOGGER.trace("Response is : " + response);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));

        LOGGER.info("sendQuery called, query is: " + query);
    }

}
