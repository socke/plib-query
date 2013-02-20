package de.feu.plib;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.feu.plib.dao.PlibDao;

public class SpringJDBCTemplate {

	/** Logger instance */
	static final Logger LOGGER = Logger.getLogger(SpringJDBCTemplate.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PropertyConfigurator.configureAndWatch("log4j.properties");
		LOGGER.info("STARING UP APP");
		
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"beans.xml");
	 
			PlibDao obj = (PlibDao) context.getBean("templateTest");
			List<String> companyNames = obj.getCompanyNames();
			for (String string : companyNames) {
				LOGGER.info(string);
			}
	}

}
