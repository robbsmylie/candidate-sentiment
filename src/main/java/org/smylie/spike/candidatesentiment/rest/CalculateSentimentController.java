package org.smylie.spike.candidatesentiment.rest;

import org.smylie.spike.candidatesentiment.CalculateSentiment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/calculate")
public class CalculateSentimentController {

	@Autowired
	CalculateSentiment calc = null;
	
	public void setCalculateSentiment(CalculateSentiment calculateSentiment) {
		calc = calculateSentiment;
	}
	
	
	@RequestMapping(value="/sentiment",method=RequestMethod.GET)
    public  @ResponseBody String calcualteSentiment() {
        System.out.println("CALCULATING SENTIMENT");
        calc.calculateSentiment();
        return "{status:SUCCESS;code:200}";
    }
}
