package org.smylie.spike.candidatesentiment.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping("/")
    String index(){
        return "index";
    }

    @RequestMapping("/sentimentgraph")
    String sentimentGraph(){
        return "sentimentgraph";
    }

    @RequestMapping(value="/data", method = RequestMethod.GET, produces = "application/json")
    String data(){
        return "data";
    }

}
