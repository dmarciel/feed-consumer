package com.sparta.domain;

import java.io.Serializable;
import java.util.HashMap;

public class LoadBatch implements Serializable {
	
	private long numberOfRecords;
    private HashMap<String, Record> records;
    
	public long getNumberOfRecords() {
		return numberOfRecords;
	}
	public void setNumberOfRecords(long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	public HashMap<String, Record> getRecords() {
		return records;
	}
	public void setRecords(HashMap<String, Record> records) {
		this.records = records;
	}

}
