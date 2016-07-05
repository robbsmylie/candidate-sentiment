package org.smylie.spike.candidatesentiment.om;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Candidate {

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

}