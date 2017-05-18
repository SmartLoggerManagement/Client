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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/smartlogger")
public class Principale {
    // ATTRIBUTES
    @Autowired
    private LogRepository logRepository;

    private SmartRest smartRest = new SmartRest();

    // REQUESTS
	@RequestMapping("/")
    public String home(Model model) {
	    model.addAttribute("logs", logRepository.findAll());
        model.addAttribute("log", new LogEntity());
        return "index";
    }

    @GetMapping("/analyze")
    public String analyzeForm(Model model) {
	    model.addAttribute("log", new LogEntity());
	    return "analyze";
    }

    @GetMapping("/train")
    public String train(Model model) {
        smartRest.train();
        return home(model);
    }

    @PostMapping("/analyze")
    public String analyzeSubmitted(@ModelAttribute("log") @Valid LogEntity entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "analyze";
        }
        smartRest.analyze(entity.getContent());
        model.addAttribute("message", "All Logs have been sent to be analyzed. We hope you don't get any alert !");
        return home(model);
    }

    @GetMapping("/provide")
    public String provideForm(Model model) {
        model.addAttribute("log", new LogEntity());
        return "provide";
    }

    @PostMapping("/provide")
    public String provideSubmitted(@ModelAttribute("log") @Valid LogEntity entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "provide";
        }
        smartRest.provide(entity.getContent());
        model.addAttribute("message", "Training Logs have been saved into SmartLogger");
        return home(model);
    }

    @GetMapping("/label")
    public String labelForm(@ModelAttribute("log") @Valid LogEntity entity, Model model) {
	    System.out.println("- HEY -\n" + entity.getId());
        model.addAttribute("log", this.logRepository.findOne(entity.getId()));
        return "label";
    }

    @PostMapping("/label")
    public String labelSubmitted(@ModelAttribute("log") @Valid LogEntity entity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "label";
        }
        System.out.println("-hey-\n" + entity.getId() + "\nhey\n");
        this.logRepository.save(entity);
        model.addAttribute("message", "The value of the label has been changed");
        return home(model);
    }
}
