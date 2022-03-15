package com.sparta.classes;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.nio.ByteBuffer;

public class Reader {

	private Integer position = 0;
	
	private byte[] content = null;
	private ByteArrayInputStream bai = null;
	
	public Reader(byte[] content) {
		this.content = content;
//		this.bai = new ByteArrayInputStream(content);
	}

	public long readLong() {

		int BYTES = 8;
		
		byte[] bytes = new byte[BYTES];
		for (int i = 0; i < BYTES; i++) {
			bytes[i] = content[i + position];
		}
		
		position += BYTES;
		return bytesToLong(bytes);
	}

	public int readInt() {
		
		int BYTES = 4;
		
		byte[] bytes = new byte[BYTES];
		for (int i = 0; i < BYTES; i++) {
			bytes[i] = content[i + position];
		}
		
		position += BYTES;
		return bytesToInt(bytes);
	}
	
	public byte[] longToBytes(long x) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(x);
	    return buffer.array();
	}

	public long bytesToLong(byte[] bytes) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.put(bytes);
	    buffer.flip();//need flip 
	    return buffer.getLong();
	}
	
	public int bytesToInt(byte[] bytes) {
	    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
	    buffer.put(bytes);
	    buffer.flip();//need flip 
	    return buffer.getInt();
	}

	public byte[] readbytes(int stringLenght) {

		byte[] bytes = new byte[stringLenght];


//		for (int  = 0; i < stringLenght; i++) {
//			bytes[i] = content[i + position];
//			position++;
//			System.out.print((char) bytes[i]);
//		}
		
		return bytes;
	}
	
}
