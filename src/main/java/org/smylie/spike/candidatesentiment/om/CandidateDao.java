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
public class CandidateDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		System.out.println("setting datasource for template");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getNumberOfCandidates() {
		int rowCount = this.jdbcTemplate.queryForObject("select count(*) from candidate", Integer.class);
		return rowCount;
	}

	public List<Candidate> getAllCandidates() {
		return this.jdbcTemplate.query( "select candidate_key, first_name, last_name, search_term from candidate", new CandidateMapper());
	}
	
	private static final class CandidateMapper implements RowMapper<Candidate> {

	    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Candidate candidate = new Candidate();
	        candidate.setCandidateKey(rs.getInt("candidate_key"));
	        candidate.setFirstName(rs.getString("first_name"));
	        candidate.setLastName(rs.getString("last_name"));
	        candidate.setSearchTerm(rs.getString("search_term"));
	        return candidate;
	    }
	}
}