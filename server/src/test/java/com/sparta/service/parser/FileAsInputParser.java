package com.sparta.service.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sparta.domain.model.LoadBatch;


public class FileAsInputParser {

    void parseFileTest(String fileName, int recordsExpected) throws IOException {
    	
        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/parser/" + fileName));

        LoadBatch loadBatch = loadProvidersParser.parseRequestContent(content);
        int numberOfRecords = (int) loadBatch.getNumberOfRecords();

        assertEquals(recordsExpected, numberOfRecords);
    }
}
