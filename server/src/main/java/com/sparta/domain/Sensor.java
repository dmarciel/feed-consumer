package com.sparta.domain;

import java.io.Serializable;

public class Sensor implements Serializable {

	private String id;
	private int measure;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMeasure() {
		return measure;
	}
	public void setMeasure(int measure) {
		this.measure = measure;
	}

	
}
