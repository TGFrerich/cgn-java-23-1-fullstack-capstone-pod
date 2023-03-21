package com.github.tgfrerich.backend.controller;

import com.github.tgfrerich.backend.repository.PodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PodControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PodRepository podRepository;


    void WhenSuccessfulPostUrlGetUrlBack() throws Exception {


    }
}