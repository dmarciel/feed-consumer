package com.sparta.infrastructure.adapter.parser;

import java.nio.ByteBuffer;
import java.util.Date;

public class LoadProvidersParser {

    private int position = 0;
    private byte[] content;

    public int parseRequestContent(byte[] content) {
        position = 0;
        this.content = content;

//        System.out.println();
//        System.out.println();

        long numberOfRecords = readLong();

        System.out.println("numberOfRecords: '" + numberOfRecords + "'");

        for(long recordNumber = 0; recordNumber < numberOfRecords; recordNumber ++) {

            System.out.println();
            final long recordIndex = readLong();
            System.out.print("recordIndex: '" + recordIndex + "'"); //en la documentacion viene como int pero es long

            final long timeStamp = readLong();
            System.out.print(" timeStamp: '" + new Date(timeStamp) + "'");
            final String city = readString();
            System.out.print(" city: '" + city + "'");
            final int numberBytesSensorData = readInt();
            System.out.println(" numberBytesSensorData: '" + numberBytesSensorData + "'");

            int numberOfSensors = readInt();
            System.out.println("numberOfSensors: '" + numberOfSensors + "'");

            for (int sernsorId = 0; sernsorId < numberOfSensors; sernsorId++) {

                final String id = readString();
                final int measure = readInt();

                System.out.println("id: '" + id + "', measure: '" + measure + "'");
            }

            final long crc32SensorsData = readLong();
            System.out.println("crc32SensorsData: '" + crc32SensorsData + "'");


            System.out.println();
        }

        return (int) numberOfRecords; //I can´t change the signature so I have to truncate it
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
