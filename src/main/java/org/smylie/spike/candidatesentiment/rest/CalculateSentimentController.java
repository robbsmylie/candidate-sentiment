package org.smylie.spike.candidatesentiment.rest;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/calculate")
public class CalculateSentimentController {

	@RequestMapping(value="/sentiment",method=RequestMethod.GET)
    public  @ResponseBody String calcualteSentiment() {
        System.out.println("CALCULATING SENTIMENT");
        return "{status:SUCCESS;code:200}";
    }
}
