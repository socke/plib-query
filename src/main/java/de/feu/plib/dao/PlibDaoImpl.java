package de.feu.plib.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import de.feu.plib.business.analyser.EnrichedQuery;
import de.feu.plib.xml.catalogue.CatalogueType;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PlibDaoImpl implements PlibDao {

	/** Logger instance */
	static final Logger LOGGER = Logger.getLogger(PlibDaoImpl.class);
	
	private static final String SELECT = "SELECT name FROM DE_COMPANY";
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getCompanyNames() {
		@SuppressWarnings("rawtypes")
		List companyNames = jdbcTemplate.query(SELECT, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				LOGGER.info(rs.getString(1));
				return rs.getString(1);
			}
		});
		return companyNames;
	}

    @Override
    public CatalogueType loadObjectsFrom(EnrichedQuery enrichedQuery) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String callProcedure() {
		jdbcTemplate.execute(new CallableStatementCreator() {
	        public CallableStatement createCallableStatement(Connection con) throws SQLException{
	            CallableStatement cs = con.prepareCall("{call MY_STORED_PROCEDURE(?, ?, ?)}");
	            //cs.setInt(1, ...); // first argument
	            //cs.setInt(2, ...); // second argument
	            //cs.setInt(3, ...); // third argument
	            return cs;
	        }
	    },
	    new CallableStatementCallback() {
	        public Object doInCallableStatement(CallableStatement cs) throws SQLException{
	            cs.execute();
	            return null; // Whatever is returned here is returned from the jdbcTemplate.execute method
	        }
	    });
		return null;
	}
}
