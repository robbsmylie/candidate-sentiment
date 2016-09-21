package org.smylie.spike.candidatesentiment.watson;

import org.junit.runner.RunWith;
import org.smylie.spike.candidatesentiment.CandidateSentimentConfiguration;
import org.smylie.spike.candidatesentiment.om.SentimentMeasurementDao;
import org.smylie.spike.candidatesentiment.watson.WatsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

/**
 * Unit test for simple App.
 * 
 * For this test to work, the WATSON_API_KEY environment variable must be set.
 * I set this in my run configuration for this class in Eclipse.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CandidateSentimentConfiguration.class)
public class WatsonWrapperTest 
{

	@Autowired
	public WatsonWrapper wrapper;
	
	@Test
	public void testCallSentimentAnalysis() {
		wrapper.callSentimentAnalysisService("www.cnn.com","Clinton");
		wrapper.callSentimentAnalysisService("www.espn.com","Durant");
	}

	@Test
	public void testCallEntities() {
		wrapper.callEntitiesService("www.cnn.com");
		wrapper.callEntitiesService("www.espn.com");
	}

	@Test
	public void testCallTaxonomy() {
		wrapper.callTaxonomyService("www.cnn.com");
		wrapper.callTaxonomyService("www.espn.com");
	}

	@Test
	public void testCallConcepts() {
		wrapper.callConceptsService("www.cnn.com");
		wrapper.callConceptsService("www.espn.com");
	}

	@Test
	public void testCallKeywords() {
		wrapper.callKeywordsService("www.cnn.com");
		wrapper.callKeywordsService("www.espn.com");
	}

}
