package org.smylie.spike.candidatesentiment;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CandidateSentimentConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(CandidateSentimentConfiguration.class, args);
    }

}