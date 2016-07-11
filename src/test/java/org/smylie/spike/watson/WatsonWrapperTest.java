package org.smylie.spike.watson;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class WatsonWrapperTest 
    extends TestCase
{

	public void testCallSentimentAnalysis() {
		WatsonWrapper wrapper = new WatsonWrapper();
		wrapper.callSentimentAnalysisService("www.cnn.com","Clinton");
		wrapper.callSentimentAnalysisService("www.espn.com","Durant");
	}

	public void testCallEntities() {
		WatsonWrapper wrapper = new WatsonWrapper();
		wrapper.callEntitiesService("www.cnn.com");
		wrapper.callEntitiesService("www.espn.com");
	}

	public void testCallTaxonomy() {
		WatsonWrapper wrapper = new WatsonWrapper();
		wrapper.callTaxonomyService("www.cnn.com");
		wrapper.callTaxonomyService("www.espn.com");
	}

	public void testCallConcepts() {
		WatsonWrapper wrapper = new WatsonWrapper();
		wrapper.callConceptsService("www.cnn.com");
		wrapper.callConceptsService("www.espn.com");
	}

	public void testCallKeywords() {
		WatsonWrapper wrapper = new WatsonWrapper();
		wrapper.callKeywordsService("www.cnn.com");
		wrapper.callKeywordsService("www.espn.com");
	}

}
