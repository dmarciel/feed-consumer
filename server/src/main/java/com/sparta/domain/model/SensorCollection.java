package com.sparta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorCollection implements Serializable {

	private int numberOfSensors;
	private List<Sensor> sensors = new ArrayList<>();

}
