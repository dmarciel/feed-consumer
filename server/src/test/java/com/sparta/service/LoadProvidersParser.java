package com.sparta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.sparta.domain.model.LoadBatch;
import com.sparta.service.parser.LoadProvidersParser;

class LoadProvidersParserTest {


    @Test
    public void parseInfoFirstFile() throws IOException {

        parseFileTest("file0.txt", 360);
    }

    @Test
    void parseInfoSecondtFile() throws IOException {

        parseFileTest("file1.txt" , 29);
    }

    @Test
    void parseInfoThirdtFile() throws IOException {

        parseFileTest("file2.txt", 515);
    }

    @Test
    void parseInfoFourthFile() throws IOException {

        parseFileTest("file3.txt" , 491);
    }

    @Test
    void parseInfoFifthFile() throws IOException {

        parseFileTest("file4.txt", 719);
    }


    void parseFileTest(String fileName, int recordsExpected) throws IOException {
        
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/parser/" + fileName));

        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        
        LoadBatch loadBatch = loadProvidersParser.parseRequestContent(content);
        int numberOfRecords = (int) loadBatch.getNumberOfRecords();

        assertEquals(recordsExpected, numberOfRecords);
    }


}
