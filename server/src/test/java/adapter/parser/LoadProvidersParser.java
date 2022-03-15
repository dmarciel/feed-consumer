package adapter.parser;


import com.sparta.infrastructure.adapter.parser.LoadProvidersParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

class LoadProvidersParserTest {

    @Test
    void parseInfoFirstFile() throws IOException {

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



    private void parseFileTest(String fileName, int recordsExpected) throws IOException {
        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/parser/" + fileName));

        System.out.println("content = " + new String(content));

        int numberOfRecords = loadProvidersParser.parseRequestContent(content);

        assertEquals(recordsExpected, numberOfRecords);
    }


}
