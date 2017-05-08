package com.smartlogger.smartclient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class Principale {

	@RequestMapping("/")
    public String principale() {
        return "principale";
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