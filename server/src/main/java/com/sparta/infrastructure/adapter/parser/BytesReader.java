package com.sparta.infrastructure.adapter.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BytesReader {

    private ByteArrayInputStream bytesReader;
    private ByteToPrinitiveConverter byteToPrinitiveConverter = new ByteToPrinitiveConverter();


    public BytesReader (byte[] content) {
        bytesReader = new ByteArrayInputStream(content);
    }

    public long readLong() {

        byte[] bytes = loadNextBytes(Long.BYTES);

        long long_ = byteToPrinitiveConverter.bytesToLong(bytes);

        return long_;
    }

    public int readInt() {

        byte[] bytes = loadNextBytes(Integer.BYTES);

        int int_ = byteToPrinitiveConverter.bytesToInt(bytes);

        return int_;
    }

    public String readString() {
        int strLenght = readInt();

        byte[] bytes = loadNextBytes(strLenght);

        return new String( bytes );
    }

    private byte[] loadNextBytes(int length) {

        byte[] bytes = new byte[length];

        try {
            bytesReader.read(bytes);

        } catch (IOException exception) {
            // it is not going to happen since the data is already in memory
            exception.printStackTrace();
        }

        return bytes;
    }
}
