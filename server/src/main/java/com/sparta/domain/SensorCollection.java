package com.sparta.domain;

import java.io.Serializable;
import java.util.HashMap;

public class SensorCollection implements Serializable {

	private int numberOfSensors;
	private HashMap<String, Sensor> sensors;
	
	public int getNumberOfSensors() {
		return numberOfSensors;
	}
	public void setNumberOfSensors(int numberOfSensors) {
		this.numberOfSensors = numberOfSensors;
	}
	public HashMap<String, Sensor> getSensors() {
		return sensors;
	}
	public void setSensors(HashMap<String, Sensor> sensors) {
		this.sensors = sensors;
	}

}
