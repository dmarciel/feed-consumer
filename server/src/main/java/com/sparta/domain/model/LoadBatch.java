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
public class LoadBatch implements Serializable {
	
	private long numberOfRecords;
    private List<Record> records = new ArrayList<>();

}
