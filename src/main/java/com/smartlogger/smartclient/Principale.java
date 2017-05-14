package com.smartlogger.smartclient;

import com.smartlogger.smartclient.repository.LogRepository;
import com.smartlogger.smartclient.rest.SmartRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/smartlogger")
public class Principale {
    @Autowired
    private LogRepository logRepository;

    private SmartRest smartRest = new SmartRest();

	@RequestMapping("/")
    public String home(Model model) {
	    model.addAttribute("logs", this.logRepository.findAll());
        return "index";
    }
	
	@RequestMapping("/editakka")
    public String akka() {
        return "editakka";
    }

    @RequestMapping("/akka")
    public String editAkka() {
        return "akka";
    }
    
    @RequestMapping("/editdatabase")
    public String database() {
        return "editdatabase";
    }

    @RequestMapping("/database")
    public String editDatabase() {
        return "database";
    }   
    
    @RequestMapping("/editfeatures")
    public String editFeatures() {
        return "editfeatures";
    }

    @RequestMapping("/features")
    public String features() {
        return "features";
    }

    @RequestMapping("/editmail")
    public String editMail() {
        return "editmail";
    }

    @RequestMapping("/mail")
    public String mail() {
        return "mail";
    }

    @RequestMapping("/editslack")
    public String editSlack() {
        return "editslack";
    }

    @RequestMapping("/slack")
    public String slack() {
        return "slack";
    }
}
