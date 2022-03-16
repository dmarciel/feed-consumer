package com.sparta.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.sparta.domain.model.LoadBatch;
import com.sparta.domain.repository.DataRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationMainControllerTest {

    private final String TEST_PROVIDER = "TEST_PROVIDER";

    @Autowired
    private DataRepository<LoadBatch> dataRepository;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        dataRepository.deleteAll();
    }

    @Test
    public void loadData() throws Exception {

        byte[] content = Files.readAllBytes(Path.of("src/test/resources/parser/file1.txt"));

        mvc.perform(post("/load/" + TEST_PROVIDER)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(29));

        final long count = dataRepository.countByProvider(TEST_PROVIDER);

        assertEquals(1, count);
    }

    @Test
    public void getTotal0() throws Exception {

        mvc.perform(get("/data/TEST_PROVIDER/total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    public void getTotal1() throws Exception {

        dataRepository.save(TEST_PROVIDER, new LoadBatch());

        mvc.perform(get("/data/TEST_PROVIDER/total"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }

}
