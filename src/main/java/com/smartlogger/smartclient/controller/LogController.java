package com.smartlogger.smartclient.controller;

import com.smartlogger.smartclient.entity.LogEntity;
import com.smartlogger.smartclient.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 *
 * @author Nicolas GILLE
 * @since SmartClient 1.0
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/smartlogger/logs")
public class LogController {
    // ATTRIBUTES
    /**
     * Repository to interact with Table <code>logs</code>.
     */
    @Autowired
    private LogRepository logRepository;

    /**
     * Display event during process.
     */
    private final Logger logger = LoggerFactory.getLogger(LogController.class);


    // REQUESTS
    /**
     * Return all logs available on table <code>logs</code>.
     *
     * @return
     *  A list of all Logs present on Database, or an error HTTP.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<LogEntity> logs = (List<LogEntity>) logRepository.findAll();
        if (logs.isEmpty()) {
            return new ResponseEntity<Object>(new Exception("Logs is empty"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<LogEntity>>(logs, HttpStatus.OK);
    }

    /**
     * Return a specific log thanks to the id.
     *
     * @param id
     *  Id of the log at retrieve on Database.
     * @return
     *  The log search, or an error HTTP.
     */
    @RequestMapping(value = "/logs/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getLog(@PathVariable(value = "id") String id) {
        LogEntity log = logRepository.findOne(id);
        if (log == null) {
            return new ResponseEntity<Object>(new Exception("Log with id " + id + " not present on Database"), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<LogEntity>(log, HttpStatus.OK);
    }

    /**
     * Insert a Log in Database.
     *
     * @param log
     *  Log at insert on Database.
     * @param uriBuilder
     *  UriComponentsBuilder used to redirect user when the insertion work.
     * @return
     *  The uri to see result of insertion or an error HTTP.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody LogEntity log, UriComponentsBuilder uriBuilder) {
        logger.info("Created log : {}", log);

        // Check if the movie already exist on database.
        LogEntity logExist = logRepository.findByContent(log.getContent());
        if (logExist != null) {
            logger.error("Unable to create. The log {} already exist", logExist.getContent());
            return new ResponseEntity<Object>(new Exception("Unable to create. The log " + log.getContent() + " already exist"), HttpStatus.CONFLICT);
        }
        logRepository.save(log);

        HttpHeaders header = new HttpHeaders();
        header.setLocation(uriBuilder.path("/logs/{id}").buildAndExpand(log.getId()).toUri());
        return new ResponseEntity<String>(header, HttpStatus.CREATED);
    }
}
