package com.sparta.service.parser;


import com.sparta.domain.model.LoadBatch;
import com.sparta.domain.model.Record;
import com.sparta.domain.model.Sensor;
import com.sparta.domain.model.SensorCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

class LoadProvidersParserAsClassesTest {

    /** The data is too coupled, there is no way to uncouple the reading of the stream since we don´t controll it´s generation
     *  It makes it difficult to extract, and test this part of the code
     *
     * @throws IOException
     */
    @Test
    void parseInfoSecondtFileAsClasses() throws IOException {

        LoadProvidersParser loadProvidersParser = new LoadProvidersParser();
        byte[] content = Files.readAllBytes(Path.of("src/test/resources/parser/file1.txt"));

        LoadBatch loadBatch = loadProvidersParser.parseRequestContent(content);

        final List<Record> recordList = loadBatch.getRecords();
        assertEquals(29, recordList.size());

        final Record record0 = recordList.get(0);
        assertEquals(0, record0.getRecordIndex());
        assertEquals( 1481606985656258L, record0.getTimestamp());
        assertEquals("Braavos", record0.getCity());
        assertEquals(136, record0.getNumberBytesSensorData());
        assertEquals(569649876L, record0.getCrc32SensorsData());


        final SensorCollection sensorCollection0 = record0.getSensorsData();
        assertEquals(3, sensorCollection0.getNumberOfSensors());
        assertEquals(3, sensorCollection0.getSensors().size());

        final Sensor sensor00 = sensorCollection0.getSensors().get(0);
        assertEquals("83f23768-bc61-45e3-a51a-d5a7b0de398f", sensor00.getId());
        assertEquals(2402, sensor00.getMeasure());

        final Sensor sensor01 = sensorCollection0.getSensors().get(1);
        assertEquals("64bf15ab-2bfc-4c50-b764-259ef0c5cf37", sensor01.getId());
        assertEquals(7533, sensor01.getMeasure());


        final Record record1 = recordList.get(1);

        assertEquals(1, record1.getRecordIndex());
        assertEquals( 1481606985656296L, record1.getTimestamp());
        assertEquals("Volantis", record1.getCity());
        assertEquals(92, record1.getNumberBytesSensorData());
        assertEquals(2068419558, record1.getCrc32SensorsData());


        final SensorCollection sensorCollection1 = record1.getSensorsData();
        assertEquals(2, sensorCollection1.getNumberOfSensors());
        assertEquals(2, sensorCollection1.getSensors().size());

        final Sensor sensor10 = sensorCollection1.getSensors().get(0);
        assertEquals("abf5d00c-9618-4c77-a24c-5a86221f3552", sensor10.getId());
        assertEquals(962, sensor10.getMeasure());

        final Sensor sensor11 = sensorCollection1.getSensors().get(1);
        assertEquals("0c9a19d2-7c9c-4dd2-8729-9cc64bb94d6a", sensor11.getId());
        assertEquals(3681, sensor11.getMeasure());
    }

}
