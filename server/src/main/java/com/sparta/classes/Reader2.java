package com.sparta.classes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Reader2 {

//	private byte[] content = null;
	private ByteArrayInputStream bai = null;
	
	public Reader2(byte[] content) {
//		this.content = content;
		this.bai = new ByteArrayInputStream(content);
	}
	
	
//	int position = 0;	
//	String status;
//	
//	public void status() throws IOException {
//
//		int b = bytesToInt(new byte[] { content[position], content[position+1]});
//		long l = bytesToLong(new byte[] { content[position], content[position+1], content[position+2], content[position+3]});
//		String s = "'" + new String(new byte[] { 
//				content[position], content[position+1], 
//				content[position+2], content[position+3],
//				content[position+4], content[position+5], 
//				content[position+6], content[position+7]
//				}) + "'";
//		
//		status =  " int: " + b + "\n"
//			+ " long: " + l + "\n"
//			+ " string: " + s + "\n";
//	}
	
	
	
	public long nextLong() throws IOException {
		int BYTES = Long.BYTES;
		byte[] bytes = bai.readNBytes(BYTES);
		return bytesToLong(bytes);
	}
	
	public int nextInt() throws IOException {
		int BYTES = Integer.BYTES;
		byte[] bytes = bai.readNBytes(BYTES);
		return bytesToInt(bytes);
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
	
	public byte[] nextBytes(int stringLenght) throws IOException {
		return bai.readNBytes(stringLenght);
	}
	
	public void close() throws IOException {
		bai.close();
	}
//	public byte[] longToBytes(long x) {
//	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
//	    buffer.putLong(x);
//	    return buffer.array();
//	}
}
