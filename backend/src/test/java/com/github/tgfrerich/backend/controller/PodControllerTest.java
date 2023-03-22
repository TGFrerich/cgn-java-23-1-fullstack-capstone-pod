package com.github.tgfrerich.backend.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class PodControllerTest {

    @Autowired
    MockMvc mockMvc;
    

    String testUrl = "https://open.spotify.com/episode/34fULWIqFeFmM7yBdo2ltg?si=dbffc903c55f4b61";

    @Test
    @DirtiesContext
    void postUrl_shouldReturnSameUrl() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/podcasts")
                        .contentType("text/plain;charset=UTF-8")
                        .content(testUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String actualResponse = result.getResponse().getContentAsString();

        Assertions.assertEquals(testUrl, actualResponse);
    }
}