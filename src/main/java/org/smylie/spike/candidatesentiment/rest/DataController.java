package org.smylie.spike.candidatesentiment.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.smylie.spike.candidatesentiment.om.Candidate;
import org.smylie.spike.candidatesentiment.om.CandidateDao;
import org.smylie.spike.candidatesentiment.om.GraphData;
import org.smylie.spike.candidatesentiment.om.GraphDataDao;
import org.smylie.spike.candidatesentiment.om.SentimentMeasurement;
import org.smylie.spike.candidatesentiment.om.SentimentMeasurementDao;
import org.smylie.spike.candidatesentiment.om.Website;
import org.smylie.spike.candidatesentiment.om.WebsiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/data")
public class DataController {

	@Autowired
	private CandidateDao candidateDao;

	@Autowired
	private WebsiteDao websiteDao;

	@Autowired
	private SentimentMeasurementDao sentimentMeasurementDao;

	@Autowired
	private GraphDataDao graphDataDao;

	public void setCandidateDao(CandidateDao dao) {
		candidateDao = dao;
	}

	public void setWebsiteDao(WebsiteDao dao) {
		websiteDao = dao;
	}

	public void setSentimentMeasurementDao(SentimentMeasurementDao dao) {
		sentimentMeasurementDao = dao;
	}

	public void setGraphDataDao(GraphDataDao dao) {
		graphDataDao = dao;
	}

	@RequestMapping(value="/allCandidates",method=RequestMethod.GET)
    public @ResponseBody List<Candidate> getAllCandidates() {
        System.out.println("getting all candidates");
        return candidateDao.getAllCandidates();
    }

	@RequestMapping(value="/allWebsites",method=RequestMethod.GET)
    public @ResponseBody List<Website> getAllWebsites() {
        System.out.println("getting all websites");
        return websiteDao.getAllWebsites();
    }

	@RequestMapping(value="/allMeasurements",method=RequestMethod.GET)
    public @ResponseBody List<SentimentMeasurement> getAllMeasurements() {
        System.out.println("getting all measurements");
        return sentimentMeasurementDao.getAllSentimentMeasurements();
    }

    @RequestMapping(value="/sentimentDataByDay",method=RequestMethod.GET)
    public @ResponseBody List<GraphData> sentimentByDay(){

    	List<GraphData> responseList = graphDataDao.getOverallSentimentByCandidate();
    	
    	/*
    	ArrayList<GraphData> responseList = new ArrayList();
		Random rand = new Random();
    	    	
    	for(int i=0; i<20; i++) {
    		
        	GregorianCalendar cal = new GregorianCalendar();
    		cal.add(GregorianCalendar.DAY_OF_MONTH, -i);
    		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yy");	
    		
    		GraphData resp = new GraphData();
    		resp.put("date", format1.format(cal.getTime()));
    		resp.put("clinton",(rand.nextDouble() * 2 - 1));
    		resp.put("trump",(rand.nextDouble() * 2 - 1));
    		
    		responseList.add(resp);
    	}
    	*/

    	return responseList;
    }


}
