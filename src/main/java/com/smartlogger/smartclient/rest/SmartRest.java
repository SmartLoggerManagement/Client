package com.smartlogger.smartclient.rest;

import com.smartlogger.smartclient.entity.LogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SmartRest {
    /**
     * SmartLogger URI.
     */
    private static final String SMARTLOGGER_APP_URI = "http://localhost:8088/smartlogger";

    /**
     * Display event during process.
     */
    private final Logger logger = LoggerFactory.getLogger(SmartRest.class);

    /**
     * RestTemplate object used to interact with SmartLogger.
     */
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Get all logs from the Database.
     *
     * @return
     *  All logs from the Database or an empty list.
     * @since 1.0
     * @version 1.0
     */
    public List<LogEntity> getLogs() {
        this.logger.info("Get all logs");
        ResponseEntity<List> responseEntity = this.restTemplate.getForEntity(SMARTLOGGER_APP_URI + "/logs/", List.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return new ArrayList<>();
    }

    /**
     * Send a request to train SmartLogger's App.
     *
     * @param trainData
     *  Data used to train Machine Learning Model.
     */
    public void provide(String trainData) {
        this.logger.info("Ordering SmartLogger to train with its own training data", trainData);
        this.restTemplate.put(SMARTLOGGER_APP_URI + "/provide", trainData);
    }

    /**
     * Send a order to train SmartLogger's App.
     */
    public void train() {
        this.logger.info("Ordering SmartLogger to train with its own training data");
        this.restTemplate.put(SMARTLOGGER_APP_URI + "/train", "");
    }

    /**
     * Send a request to analyze data passed on parameter.
     *
     * @param analyzeData
     *  A String with all logs at analyze.
     */
    public void analyze(String analyzeData) {
        this.logger.info("Start analyzer for the following data : {}", analyzeData);
        this.restTemplate.put(SMARTLOGGER_APP_URI + "/analyze", analyzeData);
    }

    /**
     * Retrieve properties present on property file specified by his name.
     *
     * @param name
     *  Name of the property at retrieve.
     * @return
     *  An associated map who contains key and value of each property.
     */
    public Map<String, String> getPropertiesByPropertyName(String name) {
        this.logger.info("Get properties from property file : {}", name);
        Map<String, String> properties = (Map<String, String>) this.restTemplate.getForEntity(SMARTLOGGER_APP_URI + "/properties/" + name, Map.class);
        return properties;
    }

    /**
     * Update properties with specific name.
     *
     * @param name
     *  Name of the property file at update.
     * @param properties
     *  New properties for the file.
     */
    public void updateProperties(String name, String properties) {
        logger.info("Send new properties on SmartLogger.");
        this.restTemplate.put(SMARTLOGGER_APP_URI + "/properties/" + name, properties);
    }
}
