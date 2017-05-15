package com.smartlogger.smartclient;

import com.smartlogger.smartclient.entity.LogEntity;
import com.smartlogger.smartclient.repository.LogRepository;
import com.smartlogger.smartclient.rest.SmartRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

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

    @GetMapping("/log-form")
    public String logForm(Model model) {
	    model.addAttribute("log", new LogEntity());
	    return "log-form";
    }

    @PostMapping("/log-form")
    public String logSubmitted(@ModelAttribute("log") @Valid LogEntity entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "log-form";
        }
        entity.setId(UUID.randomUUID().toString());
        this.logRepository.save(entity);
        model.addAttribute("message", "Log saved : " + entity.toString());
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
