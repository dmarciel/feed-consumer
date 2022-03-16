package com.sparta.domain.repository;

import com.sparta.domain.model.LoadBatch;
import com.sparta.domain.model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryDataRepositoryTest {

    private InMemoryDataRepository inMemoryDataRepository;

    @BeforeEach
    void setup() {
        inMemoryDataRepository = new InMemoryDataRepository();
    }

    @Test
    void checkInMemoryDataRepositoryAddsInformationForAProvider() throws IOException {

        final Record record = new Record();
        final Record record2 = new Record();

        final ArrayList<Record> records = new ArrayList<>();
        records.add(record);

        final ArrayList<Record> records2 = new ArrayList<>();
        records2.add(record2);

        final LoadBatch loadBatchToSave = new LoadBatch();
        loadBatchToSave.setNumberOfRecords(1);
        loadBatchToSave.setRecords(records);

        final LoadBatch loadBatchToSave2 = new LoadBatch();
        loadBatchToSave2.setNumberOfRecords(2);
        loadBatchToSave2.setRecords(records2);


        inMemoryDataRepository.save("One", loadBatchToSave);
        inMemoryDataRepository.save("One", loadBatchToSave2);

        final long totalOne = inMemoryDataRepository.countByProvider("One");
        assertEquals(2, totalOne);

        final long totalTwo = inMemoryDataRepository.countByProvider("Two");
        assertEquals(0, totalTwo);

    }

    @Test
    void checkInMemoryDataRepositoryStoresInformationForMoreThanAProvider1() throws IOException {

        final Record record = new Record();
        final Record record2 = new Record();
        final Record record3 = new Record();

        final ArrayList<Record> records = new ArrayList<>();
        records.add(record);

        final ArrayList<Record> records2 = new ArrayList<>();
        records2.add(record2);
        records2.add(record3);


        final LoadBatch loadBatchToSave = new LoadBatch();
        loadBatchToSave.setNumberOfRecords(1);
        loadBatchToSave.setRecords(records);

        final LoadBatch loadBatchToSave2 = new LoadBatch();
        loadBatchToSave2.setNumberOfRecords(2);
        loadBatchToSave2.setRecords(records2);

        inMemoryDataRepository.save("One", loadBatchToSave);

        inMemoryDataRepository.save("Two", loadBatchToSave2);

        final long totalOne = inMemoryDataRepository.countByProvider("One");
        assertEquals(1, totalOne);

        final long totalTwo = inMemoryDataRepository.countByProvider("Two");
        assertEquals(1, totalTwo);

    }

    @Test
    void checkInMemoryDataRepositoryStoresInformationForMoreThanAProviderInMoreThanOneCall() throws IOException {

        final Record record = new Record();
        final Record record2 = new Record();
        final Record record3 = new Record();
        final Record record4 = new Record();

        final ArrayList<Record> records = new ArrayList<>();
        records.add(record);

        final ArrayList<Record> records2 = new ArrayList<>();
        records2.add(record2);

        final ArrayList<Record> records3 = new ArrayList<>();
        records3.add(record3);
        records3.add(record4);


        final LoadBatch loadBatchToSave = new LoadBatch();
        loadBatchToSave.setNumberOfRecords(1);
        loadBatchToSave.setRecords(records);

        final LoadBatch loadBatchToSave2 = new LoadBatch();
        loadBatchToSave2.setNumberOfRecords(2);
        loadBatchToSave2.setRecords(records2);

        final LoadBatch loadBatchToSave3 = new LoadBatch();
        loadBatchToSave3.setNumberOfRecords(1);
        loadBatchToSave3.setRecords(records3);

        inMemoryDataRepository.save("One", loadBatchToSave);

        inMemoryDataRepository.save("Two", loadBatchToSave2);

        inMemoryDataRepository.save("One", loadBatchToSave3);

        final long totalOne = inMemoryDataRepository.countByProvider("One");
        assertEquals(2, totalOne);

        final long totalTwo = inMemoryDataRepository.countByProvider("Two");
        assertEquals(1, totalTwo);

    }
}
