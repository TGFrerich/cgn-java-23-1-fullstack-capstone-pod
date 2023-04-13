package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AssemblyAIApiServiceTest {

    @Autowired
    private AssemblyAIApiService assemblyAIApiService;

    private MockWebServer mockWebServer;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void fetchTranscriptionResult_validId_returnsTranscription() {
        // Set up a mock response for the AssemblyAI API to fetch the transcription result
        String id = "mock-id";
        String responseBody = "{\"id\": \"" + id + "\", \"status\": \"completed\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseBody));

        // Initialize a WebClient with the MockWebServer URL and set it in the AssemblyAIApiService instance
        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();
        assemblyAIApiService.webClient = webClient;

        // Call fetchTranscriptionResult with the mock id
        TranscribedPodcastFromAssemblyAI result = assemblyAIApiService.fetchTranscriptionResult(id);

        // Verify that the result has the correct values
        assertEquals(id, result.getId());
        assertEquals("completed", result.getStatus());
    }
}
