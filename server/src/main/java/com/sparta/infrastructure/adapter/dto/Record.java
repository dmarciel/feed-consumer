package com.sparta.infrastructure.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record implements Serializable {
	
	private long recordIndex;	// documentation says it is an int but it is actually a long
	private long timestamp;
	private String city;
	private int numberBytesSensorData;  // Number of bytes used in following sensorData section
	private SensorCollection sensorsData;
	private long crc32SensorsData; // crc32 of all bytes present in the sensorData section

}
