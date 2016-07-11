package org.smylie.spike.candidatesentiment.om;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WebsiteDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getNumberOfWebsites() {
		int rowCount = this.jdbcTemplate.queryForObject("select count(*) from website", Integer.class);
		return rowCount;
	}

	public List<Website> getAllWebsites() {
		return this.jdbcTemplate.query( "select site_key, name, url from website", new WebsiteMapper());
	}
	
	private static final class WebsiteMapper implements RowMapper<Website> {

	    public Website mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Website website = new Website();
	    	website.setWebsiteKey(rs.getInt("site_key"));
	    	website.setName(rs.getString("name"));
	    	website.setUrl(rs.getString("url"));
	        return website;
	    }
	}
}