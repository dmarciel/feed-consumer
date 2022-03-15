package adapter.parser;

import com.sparta.infrastructure.adapter.dto.LoadBatch;
import com.sparta.infrastructure.adapter.parser.LoadProvidersParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class FileAsInputParser {

    void parseFileTest(String fileName, int recordsExpected) throws IOException {

        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/parser/" + fileName));

        LoadBatch loadBatch = loadProvidersParser.parseRequestContent(content);
        int numberOfRecords = (int) loadBatch.getNumberOfRecords();

        assertEquals(recordsExpected, numberOfRecords);
    }
}
