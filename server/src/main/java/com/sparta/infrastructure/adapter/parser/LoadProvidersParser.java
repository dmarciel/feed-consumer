package com.sparta.infrastructure.adapter.parser;

import com.sparta.domain.model.LoadBatch;
import com.sparta.domain.model.Record;
import com.sparta.domain.model.Sensor;
import com.sparta.domain.model.SensorCollection;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class LoadProvidersParser {

    private BytesReader bytesReader;

    public LoadBatch parseRequestContent(byte[] content) {
        bytesReader = new BytesReader(content);

        LoadBatch loadBatch = extractLoadBatch();
        return loadBatch;
    }

    private LoadBatch extractLoadBatch() {

        long numberOfRecords = bytesReader.readLong();

        List<Record> records = new ArrayList<>();
        for(long recordNumber = 0; recordNumber < numberOfRecords; recordNumber ++) {

            final Record record = extractRecord();
            records.add(record);
        }

        return new LoadBatch(numberOfRecords, records);
    }

    private Record extractRecord() {

        final long recordIndex = bytesReader.readLong(); //en la documentacion viene como int pero es long
        final long timeStamp = bytesReader.readLong();
        final String city = bytesReader.readString();
        final int numberBytesSensorData = bytesReader.readInt();

        final SensorCollection sensorCollection = extractSensorCollection();

        final long crc32SensorsData = bytesReader.readLong();

        return new Record(recordIndex, timeStamp, city, numberBytesSensorData, sensorCollection, crc32SensorsData);
    }

    private SensorCollection extractSensorCollection() {

        int numberOfSensors = bytesReader.readInt();

        List<Sensor> sensors = new ArrayList<>();
        for (int sernsorId = 0; sernsorId < numberOfSensors; sernsorId++) {

            final Sensor sensor = extractSensor();
            sensors.add(sensor);
        }

        return new SensorCollection(numberOfSensors, sensors);
    }

    private Sensor extractSensor() {

        final String id = bytesReader.readString();
        final int measure = bytesReader.readInt();

        return new Sensor(id, measure);
    }

}
