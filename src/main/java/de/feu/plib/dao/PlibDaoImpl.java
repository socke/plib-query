package de.feu.plib.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
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
}
