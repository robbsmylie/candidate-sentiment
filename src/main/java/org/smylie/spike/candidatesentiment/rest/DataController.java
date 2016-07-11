package org.smylie.spike.candidatesentiment.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.smylie.spike.candidatesentiment.om.Candidate;
import org.smylie.spike.candidatesentiment.om.CandidateDao;
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
	
	public void setCandidateDao(CandidateDao dao) {
		candidateDao = dao;
	}

	public void setWebsiteDao(WebsiteDao dao) {
		websiteDao = dao;
	}

	public void setSentimentMeasurementDao(SentimentMeasurementDao dao) {
		sentimentMeasurementDao = dao;
	}

	@RequestMapping(value="/allCandidates",method=RequestMethod.GET)
    public  @ResponseBody List<Candidate> getAllCandidates() {
        System.out.println("getting all candidates");
        return candidateDao.getAllCandidates();
    }

	@RequestMapping(value="/allWebsites",method=RequestMethod.GET)
    public  @ResponseBody List<Website> getAllWebsites() {
        System.out.println("getting all websites");
        return websiteDao.getAllWebsites();
    }

	@RequestMapping(value="/allMeasurements",method=RequestMethod.GET)
    public  @ResponseBody List<SentimentMeasurement> getAllMeasurements() {
        System.out.println("getting all measurements");
        return sentimentMeasurementDao.getAllSentimentMeasurements();
    }
}
