package com.sparta.controller;

import com.sparta.service.DataService;
import com.sparta.domain.model.LoadBatch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class MainController {

    private DataService dataService;

    @Autowired
    public MainController(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * Loads the input information into the database
     * @param provider name
     * @param content LoadBatch encoded as byte[]
     * @return the number of records readed
     */
    @PostMapping("/load/{provider}")
    public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) {

        log.info("Load request with a provider {} has been received", provider);

        final LoadBatch loadBatch = dataService.save(provider, content);

        log.info("responding the client with {} of {} records of the request properly parsed", loadBatch.getRecords().size(), loadBatch.getNumberOfRecords());

        return loadBatch.getRecords().size(); //I cant change the signature so I have to truncate it

    }
    
    /**
     * Gets the total number of messages sent by a provider
     * @param provider name
     * @return number of total calls
     */
    @GetMapping("/data/{provider}/total")
    public int total(@PathVariable("provider") String provider) {

        log.info("Total request for the provider {} has been received", provider);

        final int total = dataService.getTotal(provider);

        log.info("responding the client with {} as the total of records parsed for {}", total, provider);

        return total;
    }

}
