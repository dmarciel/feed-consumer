package com.sparta.infrastructure.adapter.parser;

public class BytesReader {

    ByteToPrinitiveConverter byteToPrinitiveConverter = new ByteToPrinitiveConverter();

    private int position = 0;
    private byte[] content;


    public BytesReader (byte[] content) {
        position = 0;
        this.content = content;
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

        for (int i = 0; i < length; i++) {
            bytes[i] = content[position + i];
        }

        position += length;
        return bytes;
    }
}
