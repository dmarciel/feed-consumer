package com.sparta.domain;

import java.io.Serializable;

public class Record implements Serializable {
	
	private int recordIndex;
	private long timestamp;
	private String city;
	private int numberBytesSensorData;  // Number of bytes used in following sensorData section
	private SensorCollection sensorsData;
	private long crc32SensorsData; // crc32 of all bytes present in the sensorData section

	public int getRecordIndex() {
		return recordIndex;
	}
	public void setRecordIndex(int recordIndex) {
		this.recordIndex = recordIndex;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNumberBytesSensorData() {
		return numberBytesSensorData;
	}
	public void setNumberBytesSensorData(int numberBytesSensorData) {
		this.numberBytesSensorData = numberBytesSensorData;
	}
	public SensorCollection getSensorsData() {
		return sensorsData;
	}
	public void setSensorsData(SensorCollection sensorsData) {
		this.sensorsData = sensorsData;
	}
	public long getCrc32SensorsData() {
		return crc32SensorsData;
	}
	public void setCrc32SensorsData(long crc32SensorsData) {
		this.crc32SensorsData = crc32SensorsData;
	}


}
