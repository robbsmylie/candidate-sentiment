package org.smylie.spike.candidatesentiment.db;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbDataSource {
	
	@Value("${DATABASE_URL}")
	private String databaseUrlProperty;
	public void setDatabaseUrlProperty(String url) {
		databaseUrlProperty = url;
	}
	
	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {
		
		System.out.println("database url: "+databaseUrlProperty);
		URI dbUri = new URI(databaseUrlProperty);

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(dbUrl);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);

		return basicDataSource;
	}
}
