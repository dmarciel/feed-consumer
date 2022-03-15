package com.sparta.infrastructure.repository;

import com.sparta.domain.repository.DataRepository;
import com.sparta.domain.model.LoadBatch;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryDataRepository implements DataRepository<LoadBatch> {

    Map<String, List<LoadBatch>> batchesPerProvider = new HashMap<>();

    public void save(String provider, LoadBatch loadBatchToSave){
        batchesPerProvider.computeIfAbsent(provider, key -> new ArrayList<LoadBatch>());

        batchesPerProvider.get(provider).add(loadBatchToSave);
    }

    @Override
    public long countByProvider(String provider) {

        //this could be precalculated but since I don´t know whether it is going to grow I can´t do precalculations in advance
        return batchesPerProvider.get(provider).stream()
                .flatMap( loadBatch -> loadBatch.getRecords().stream())
                .count();

    }


}
