package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
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


    @Test
    void sendTranscriptionRequestToAssemblyAI_validAudioUrl_returnsApiResponse() {
        // Set up a mock response for the AssemblyAI API to send the transcription request
        String audioUrl = "https://test.com/podcast.mp3";
        String webhookUrl = "https://your-base-url/api/webhook";
        String responseBody = "{\"id\":\"mock-id\",\"status\":\"queued\",\"acoustic_model\":\"assemblyai_default\",\"audio_duration\":100,\"audio_url\":\"" + audioUrl + "\",\"format_text\":true,\"language_model\":\"assemblyai_default\",\"punctuate\":true,\"text\":\"mock_text\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(responseBody));

        // Initialize a WebClient with the MockWebServer URL and set it in the AssemblyAIApiService instance
        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();
        assemblyAIApiService.webClient = webClient;

        // Call sendTranscriptionRequestToAssemblyAI with a RequestBodyForAssemblyAI instance containing the audio URL
        RequestBodyForAssemblyAI requestBody = new RequestBodyForAssemblyAI(audioUrl, webhookUrl, true, true);
        AssemblyAIApiResponse result = assemblyAIApiService.sendTranscriptionRequestToAssemblyAI(requestBody);

        // Verify that the result has the correct values
        assertEquals("mock-id", result.getId());
        assertEquals("queued", result.getStatus());
        assertEquals("assemblyai_default", result.getAcoustic_model());
        assertEquals(100, result.getAudio_duration());
        assertEquals(audioUrl, result.getAudio_url());
        assertEquals(true, result.getFormat_text());
        assertEquals("assemblyai_default", result.getLanguage_model());
        assertEquals(true, result.getFormat_text());
        assertEquals("mock_text", result.getText());
    }

}
