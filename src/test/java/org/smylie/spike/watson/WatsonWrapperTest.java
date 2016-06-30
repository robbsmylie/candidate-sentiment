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
		wrapper.callSentimentAnalysis("www.cnn.com");
		wrapper.callSentimentAnalysis("www.espn.com");
	}

}
