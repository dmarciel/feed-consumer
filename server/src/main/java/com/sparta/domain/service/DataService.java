package com.sparta.domain.service;

import com.sparta.domain.repository.DataRepository;
import com.sparta.infrastructure.adapter.dto.LoadBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    DataRepository dataRepository;

    @Autowired
    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void save(String provider, LoadBatch loadBatch) {
        dataRepository.save(provider, loadBatch);
    }

    public long getTotal(String provider) {
        return dataRepository.countByProvider(provider);
    }
}
