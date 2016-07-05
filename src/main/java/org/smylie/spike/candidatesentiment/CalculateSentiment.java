package org.smylie.spike.candidatesentiment;

import org.smylie.spike.candidatesentiment.om.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableAutoConfiguration
@Component
@ComponentScan
public class CalculateSentiment {

	@Autowired
	private Candidate candidate;
	
	public void setCandidate(Candidate cand) {
		candidate = cand;
	}
	
    public static void main(String[] args) {
        SpringApplication.run(CalculateSentiment.class, args);
    	CalculateSentiment calc = new CalculateSentiment();
    	calc.calculateSentiment();
    }
	
	public void calculateSentiment() {
		System.out.println("STARTING CALCULATING SENTIMENT");
		int count = candidate.getNumberOfCandidates();
		System.out.println("Number of Candidates="+count);		
		System.out.println("ENDING CALCULATING SENTIMENT");
	}
	
}
