package com.smartlogger.smartclient;

import com.smartlogger.smartclient.entity.LogEntity;
import com.smartlogger.smartclient.repository.LogRepository;
import com.smartlogger.smartclient.rest.SmartRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/smartlogger")
public class Principale {
    // ATTRIBUTES
    @Autowired
    private LogRepository logRepository;

    private SmartRest smartRest = new SmartRest();

    static final Logger logger = LoggerFactory.getLogger(Principale.class);

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

    @GetMapping("/label/{id}")
    public String labelForm(@PathVariable String id, Model model) {
	    model.addAttribute("log", this.logRepository.findOne(id));
        return "label";
    }

    @PutMapping("/label/{id}")
    public String labelSubmitted(@PathVariable String id,
                                 @ModelAttribute("log") @Valid LogEntity entity,
                                 BindingResult result, Model model) {
	    logger.info("Update log : {}", entity);
        if (result.hasErrors()) {
            logger.error("Error occurred during log update : {}", entity);
            return this.labelForm(id, model);
        }
        logger.info("Update log {} ", entity);
        this.logRepository.save(entity);
        model.addAttribute("message", "The value of the label has been changed");
        return this.home(model);
    }
}
