package com.sparta.domain.repository;

import com.sparta.domain.model.LoadBatch;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryDataRepository implements DataRepository<LoadBatch> {

    private Map<String, List<LoadBatch>> batchesPerProvider = new HashMap<>();

    /** It stores all the loaded data
     *  It doesn´t make sense to store repeated or unstable data as the number of items in a list plus the list,
     *  but in this case I will store them all just in case it is required later
     * @param provider
     * @param loadBatchToSave
     */
    @Override
    public void save(String provider, LoadBatch loadBatchToSave){
        batchesPerProvider.computeIfAbsent(provider, key -> new ArrayList<LoadBatch>());

        batchesPerProvider.get(provider).add(loadBatchToSave);
    }

    /** Returns the total amount of records uploaded by a provider
     *
     * @param provider
     * @return total amount of records uploaded by a provider
     */
    @Override
    public long countByProvider(String provider) {

        if(batchesPerProvider.containsKey(provider)){

            //this could be precalculated but since I don´t know whether it is going to grow I can´t do precalculations in advance
            return batchesPerProvider.get(provider).size();
        }
        else return 0;
    }


}
