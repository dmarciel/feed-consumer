package com.sparta.infrastructure.adapter.parser;

import com.sparta.infrastructure.adapter.dto.LoadBatch;
import com.sparta.infrastructure.adapter.dto.Record;
import com.sparta.infrastructure.adapter.dto.Sensor;
import com.sparta.infrastructure.adapter.dto.SensorCollection;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class LoadProvidersParser {

    private int position = 0;
    private byte[] content;

    public int parseRequestContent(byte[] content) {
        position = 0;
        this.content = content;

        LoadBatch loadBatch = extractLoadBatch();

        return (int) loadBatch.getNumberOfRecords(); //I can´t change the signature so I have to truncate it
    }

    private LoadBatch extractLoadBatch() {

        long numberOfRecords = readLong();
//        System.out.println("numberOfRecords: '" + numberOfRecords + "'");

        List<Record> records = new ArrayList<>();

        for(long recordNumber = 0; recordNumber < numberOfRecords; recordNumber ++) {

            final Record record = extractRecord();

            records.add(record);
        }

        return new LoadBatch(numberOfRecords, records);
    }

    private Record extractRecord() {

        final long recordIndex = readLong(); //en la documentacion viene como int pero es long
        final long timeStamp = readLong();
        final String city = readString();
        final int numberBytesSensorData = readInt();

        final SensorCollection sensorCollection = extractSensorCollection();

        final long crc32SensorsData = readLong();


//            System.out.println();
//            System.out.print("recordIndex: '" + recordIndex + "'");
//            System.out.print(" timeStamp: '" + new Date(timeStamp) + "'");
//            System.out.print(" city: '" + city + "'");
//            System.out.println(" numberBytesSensorData: '" + numberBytesSensorData + "'");
//            System.out.println("numberOfSensors: '" + numberOfSensors + "'");
//            System.out.println("crc32SensorsData: '" + crc32SensorsData + "'");

        return new Record(recordIndex, timeStamp, city, numberBytesSensorData, sensorCollection, crc32SensorsData);
    }

    private SensorCollection extractSensorCollection() {

        int numberOfSensors = readInt();

        List<Sensor> sensors = new ArrayList<>();

        for (int sernsorId = 0; sernsorId < numberOfSensors; sernsorId++) {

            Sensor sensor = extractSensor();
            sensors.add(sensor);
        }

        return new SensorCollection(numberOfSensors, sensors);
    }

    private Sensor extractSensor() {
        final String id = readString();
        final int measure = readInt();

//        System.out.println("id: '" + id + "', measure: '" + measure + "'");
        return new Sensor(id, measure);

    }

    private long readLong() {

        byte[] bytes = loadNextBytes(Long.BYTES);

        long long_ = bytesToLong(bytes);

        return long_;
    }

    private int readInt() {

        byte[] bytes = loadNextBytes(Integer.BYTES);

        int int_ = bytesToInt(bytes);

        return int_;
    }

    private String readString() {
        int strLenght = readInt();

        byte[] bytes = loadNextBytes(strLenght);

        return new String( bytes );
    }

    private byte[] loadNextBytes(int length) {

        byte[] bytes = new byte[length];

        for (int i = 0; i < length; i++) {
            bytes[i] = content[position + i];
        }

        position += length;

        return bytes;
    }

    private long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    private int bytesToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(bytes);
        buffer.flip();//need flip
        return buffer.getInt();
    }

}
