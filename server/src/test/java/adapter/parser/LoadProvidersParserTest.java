package adapter.parser;


import com.sparta.infrastructure.adapter.parser.LoadProvidersParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

class LoadProvidersParserAsClassesTest {

    @Test
    void parseInfoSecondtFileAsClasses() throws IOException {

        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/parser/file1.txt"));

        System.out.println("content = " + new String(content));

        int numberOfRecords = loadProvidersParser.parseRequestContent(content);

        assertEquals(29, numberOfRecords);
    }

}
