package de.feu.plib;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;

/**
 * Managed Bean for start page  
 */
@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    /** Logger instance */
	static final Logger LOGGER = Logger.getLogger(HelloBean.class);

    private String name;

    public String getName() {
//    	ApplicationContext context = ApplicationContextProvider.getApplicationContext();
//    	HelloWorld myBean = (HelloWorld) context.getBean("helloWorld");
        
    	return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}