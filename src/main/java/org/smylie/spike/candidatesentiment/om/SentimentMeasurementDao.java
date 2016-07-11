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
public class SentimentMeasurementDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getNumberOfSentimentMeasurements() {
		int rowCount = this.jdbcTemplate.queryForObject("select count(*) from sentiment_measurement", Integer.class);
		return rowCount;
	}

	public List<SentimentMeasurement> getAllSentimentMeasurements() {
		return this.jdbcTemplate.query( "select sentiment_measurement_key, site_key, candidate_key, measurement_date, sentiment_type, sentiment, url from sentiment_measurement", new SentimentMeasurementMapper());
	}

	public int insertSentimentMeasurement(SentimentMeasurement measurement) {

		int newKey = 1;
		
		// get count of current records to make sure the table isn't empty
		int num = getNumberOfSentimentMeasurements();
		if(num > 0) {
			int maxKey = this.jdbcTemplate.queryForObject("select max(sentiment_measurement_key) from sentiment_measurement", Integer.class);
			newKey = maxKey+1;
		}
		
		this.jdbcTemplate.update(
		        "insert into sentiment_measurement (sentiment_measurement_key, site_key, candidate_key, measurement_date, sentiment_type, sentiment, url) "+
		        "values (?, ?, ?, ?, ?, ?, ?)",
		        newKey, measurement.getSiteKey(), measurement.getCandidateKey(), measurement.getMeasurementDate(), measurement.getSentimentType(), measurement.getSentiment(), measurement.getUrl());
		
		return newKey;
	}

	public void deleteSentimentMeasurement(int sentimentMeasurementKey) {
		
		this.jdbcTemplate.update(
		        "delete from sentiment_measurement where sentiment_measurement_key=?",
		        sentimentMeasurementKey);
	}

	private static final class SentimentMeasurementMapper implements RowMapper<SentimentMeasurement> {

	    public SentimentMeasurement mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	SentimentMeasurement measurement = new SentimentMeasurement();
	    	measurement.setSentimentMeasurementKey(rs.getInt("sentiment_measurement_key"));
	    	measurement.setSiteKey(rs.getInt("site_key"));
	    	measurement.setCandidateKey(rs.getInt("candidate_key"));
	    	measurement.setSentiment(rs.getString("sentiment"));
	    	measurement.setSentimentType(rs.getString("sentiment_type"));
	    	measurement.setMeasurementDate(rs.getDate("measurement_date"));
	    	measurement.setUrl(rs.getString("url"));
	        return measurement;
	    }
	}
}