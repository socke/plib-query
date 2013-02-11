package de.feu.plib;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	/** Logger instance */
	static final Logger LOGGER = Logger.getLogger(App.class);
	
	/**
	 * 
	 * Starts an application, initializing the spring application context manually.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configureAndWatch("log4j.properties");
		LOGGER.info("STARING UP APP");
		
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"beans.xml");
	 
			HelloWorld obj = (HelloWorld) context.getBean("helloBean");
			obj.printHello();
	}

}
