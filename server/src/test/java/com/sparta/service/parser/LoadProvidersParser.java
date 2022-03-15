package com.sparta.service.parser;


import org.junit.jupiter.api.Test;

import java.io.IOException;

class LoadProvidersParserTest {

    FileAsInputParser fileAsInputParser = new FileAsInputParser();

    @Test
    void parseInfoFirstFile() throws IOException {

        fileAsInputParser.parseFileTest("file0.txt", 360);
    }

    @Test
    void parseInfoSecondtFile() throws IOException {

        fileAsInputParser.parseFileTest("file1.txt" , 29);
    }

    @Test
    void parseInfoThirdtFile() throws IOException {

        fileAsInputParser.parseFileTest("file2.txt", 515);
    }

    @Test
    void parseInfoFourthFile() throws IOException {

        fileAsInputParser.parseFileTest("file3.txt" , 491);
    }

    @Test
    void parseInfoFifthFile() throws IOException {

        fileAsInputParser.parseFileTest("file4.txt", 719);
    }




}
