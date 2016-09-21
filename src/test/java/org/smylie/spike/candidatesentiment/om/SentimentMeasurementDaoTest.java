package org.smylie.spike.candidatesentiment.om;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.smylie.spike.candidatesentiment.CandidateSentimentConfiguration;
import org.smylie.spike.candidatesentiment.watson.WatsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 * 
 * For this test to work, the DATABASE_URL environment variable must be set.
 * I set this in my run configuration for this class in Eclipse.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CandidateSentimentConfiguration.class)
public class SentimentMeasurementDaoTest 
{

	@Autowired
	public SentimentMeasurementDao measurementDao = null;
	
	@Test
	public void testGetNumberOfMeasurements() {

		int num = measurementDao.getNumberOfSentimentMeasurements();
		//assertEquals(1,num);
	}

	@Test
	public void testInsertRecord() {

		// get number of measurements before so we can be sure it went up by one
		int totalMeasurementsBefore = measurementDao.getNumberOfSentimentMeasurements();
		
		// create a new measurement to insert
		SentimentMeasurement measurement = new SentimentMeasurement();
		measurement.setCandidateKey(1);
		measurement.setSiteKey(1);
		measurement.setMeasurementDate(new Date());
		measurement.setSentiment("0.578329");
		measurement.setUrl("test.com");
				
		// do the insert, then check that we got back a key greater than 0
		int key = measurementDao.insertSentimentMeasurement(measurement);
		assertTrue(key>totalMeasurementsBefore);

		// check that we have one more than before
		int totalMeasurementsAfter = measurementDao.getNumberOfSentimentMeasurements();
		assertEquals(totalMeasurementsAfter,totalMeasurementsBefore+1);

		// clean up record that was created
		measurementDao.deleteSentimentMeasurement(key);
	}
}
