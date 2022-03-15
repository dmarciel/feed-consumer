package com.sparta.infrastructure.adapter.rest;

import com.sparta.infrastructure.adapter.dto.LoadBatch;
import com.sparta.infrastructure.adapter.parser.LoadProvidersParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class MainController {


    @PostMapping("/load/{provider}")
    public int load(@PathVariable("provider") String provider, @RequestBody byte[] content) {

        log.info("a request with a provider {} has been received", provider);

        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        LoadBatch loadBatch = loadProvidersParser.parseRequestContent(content);

        log.info("{} of {} records of the request has been properly parsed", loadBatch.getRecords().size(), loadBatch.getNumberOfRecords());



        log.info("responding the client with {} as the number of records parsed", loadBatch.getNumberOfRecords());

        return (int) loadBatch.getNumberOfRecords(); //I can´t change the signature so I have to truncate it

    }


    @GetMapping("/data/{provider}/total")
    public int total(@PathVariable("provider") String provider) {
        return 100;
    }

}
