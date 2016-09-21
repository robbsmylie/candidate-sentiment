package org.smylie.spike.candidatesentiment.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/data")
    String data(){
        return "data";
    }

}
