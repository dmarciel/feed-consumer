package com.sparta.infrastructure.adapter.rest.controller;

import com.sparta.domain.service.DataService;
import com.sparta.domain.model.LoadBatch;
import com.sparta.infrastructure.adapter.parser.LoadProvidersParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class MainController {

    private DataService dataService;
    private LoadProvidersParser loadProvidersParser;

    @Autowired
    public MainController(DataService dataService, LoadProvidersParser loadProvidersParser) {
        this.dataService = dataService;
        this.loadProvidersParser = loadProvidersParser;
    }


    @PostMapping("/load/{provider}")
    public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) {

        log.info("Load request with a provider {} has been received", provider);

        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        LoadBatch loadBatch = loadProvidersParser.parseRequestContent(content);

        log.info("{} of {} records of the request has been properly parsed", loadBatch.getRecords().size(), loadBatch.getNumberOfRecords());

        dataService.save(provider, loadBatch);

        log.info("responding the client with {} as the number of records parsed", loadBatch.getNumberOfRecords());

        return loadBatch.getRecords().size(); //I can´t change the signature so I have to truncate it

    }


    @GetMapping("/data/{provider}/total")
    public int total(@PathVariable("provider") String provider) {

        log.info("Total request for the provider {} has been received", provider);

        final long total = dataService.getTotal(provider);

        log.info("responding the client with {} as the total of records parsed for {}", total, provider);

        return (int) total;
    }

}
