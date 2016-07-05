package org.smylie.spike.candidatesentiment.db;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbDataSource {

	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {
		
		String databaseUrlProperty = System.getenv("DATABASE_URL");
		System.out.println("database url="+databaseUrlProperty);
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
