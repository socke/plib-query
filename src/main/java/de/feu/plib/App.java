package de.feu.plib;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App {

	/** Logger instance */
	static final Logger LOGGER = Logger.getLogger(App.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configureAndWatch("log4j.properties");
		LOGGER.info("STARING UP APP");
	}

}
