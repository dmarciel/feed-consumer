package com.sparta.application.service;

import com.sparta.domain.repository.DataRepository;
import com.sparta.domain.model.LoadBatch;
import com.sparta.infrastructure.adapter.parser.LoadProvidersParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private DataRepository dataRepository;
    private LoadProvidersParser loadProvidersParser;

    @Autowired
    public DataService(DataRepository dataRepository, LoadProvidersParser loadProvidersParser) {
        this.dataRepository = dataRepository;
        this.loadProvidersParser = loadProvidersParser;
    }

    public LoadBatch save(String provider, byte[] content) {

        LoadBatch loadBatch = loadProvidersParser.parseRequestContent(content);

        dataRepository.save(provider, loadBatch);

        return loadBatch;
    }

    public long getTotal(String provider) {
        return dataRepository.countByProvider(provider);
    }
}
