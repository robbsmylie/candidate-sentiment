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
public class GraphDataDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		System.out.println("setting datasource for template");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<GraphData> getOverallSentimentByCandidate() {
		
		List<GraphData> data = this.jdbcTemplate.query( "select avg(case when candidate_key=1 then sentiment::float end) as clinton,avg(case when candidate_key=2 then sentiment::float end) as trump,date_trunc('day',measurement_date) as date from sentiment_measurement group by date", new SentimentByCandidateMapper());
		
		return data;
	}
	
	private static final class SentimentByCandidateMapper implements RowMapper<GraphData> {

	    public GraphData mapRow(ResultSet rs, int rowNum) throws SQLException {
	        GraphData data = new GraphData();
	        data.put("clinton",rs.getFloat("clinton"));
	        data.put("trump",rs.getFloat("trump"));
	        data.put("date",rs.getDate("date"));
	        return data;
	    }
	}
}