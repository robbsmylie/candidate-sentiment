package org.smylie.spike.candidatesentiment;

import java.util.Date;
import java.util.List;

import org.smylie.spike.candidatesentiment.om.Candidate;
import org.smylie.spike.candidatesentiment.om.CandidateDao;
import org.smylie.spike.candidatesentiment.om.SentimentMeasurement;
import org.smylie.spike.candidatesentiment.om.SentimentMeasurementDao;
import org.smylie.spike.candidatesentiment.om.Website;
import org.smylie.spike.candidatesentiment.om.WebsiteDao;
import org.smylie.spike.candidatesentiment.watson.WatsonResponse;
import org.smylie.spike.candidatesentiment.watson.WatsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.JsonPath;

@EnableAutoConfiguration
@Component
@ComponentScan
public class CalculateSentiment {

	@Autowired
	private CandidateDao candidateDao;
	public void setCandidateDao(CandidateDao dao) {
		candidateDao = dao;
	}

	@Autowired
	private WebsiteDao siteDao;
	public void setWebsiteDao(WebsiteDao dao) {
		siteDao = dao;
	}

	@Autowired
	private SentimentMeasurementDao measurementDao;
	public void setSentimentMeasurementDao(SentimentMeasurementDao dao) {
		measurementDao = dao;
	}

	@Autowired
	private WatsonWrapper watson;
	public void setWatsonWrapper(WatsonWrapper wrapper) {
		watson = wrapper;
	}
	
	/*
	public static void main(String[] args) {
		
		SpringApplication.run(CalculateSentiment.class, args);
		CalculateSentiment calc = new CalculateSentiment();
		calc.calculateSentiment();
	}
	*/

	public void calculateSentiment() {
		
		System.out.println("STARTING CALCULATING SENTIMENT");

		//WatsonWrapper watson = new WatsonWrapper();

		// get all the candidates
		List<Candidate> candidates = candidateDao.getAllCandidates();
		System.out.println("Number of candidates="+candidates.size());

		// get all the sites
		List<Website> sites = siteDao.getAllWebsites();
		System.out.println("Number of websites="+sites.size());		

		// loop over candidates and calculate sentiment for each site
		for(Candidate candidate : candidates) {
			String searchTerm = candidate.getSearchTerm();

			// loop over sites and get candidate sentiment for each
			for(Website site: sites) {
				String url = site.getUrl();

				// get sentiment value for this candidate and url
				WatsonResponse response = watson.callSentimentAnalysisService(url,searchTerm);
				
				// if it's not an error
				if(!response.get("status").equals("ERROR")) {

					// store the sentiment in the database
					SentimentMeasurement measurement = new SentimentMeasurement();
					measurement.setCandidateKey(candidate.getCandidateKey());
					measurement.setSiteKey(site.getWebsiteKey());
					measurement.setMeasurementDate(new Date());
					measurement.setUrl(url);
					measurement.setSentiment((String)response.get("sentiment"));
					measurement.setSentimentType((String)response.get("sentimenttype"));
					
					measurementDao.insertSentimentMeasurement(measurement);
				}
			}
		}

		System.out.println("ENDING CALCULATING SENTIMENT");
	}

}
