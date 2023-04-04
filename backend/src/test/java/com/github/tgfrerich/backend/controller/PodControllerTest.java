package com.github.tgfrerich.backend.controller;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import com.github.tgfrerich.backend.repository.PodRepository;
import com.github.tgfrerich.backend.service.AssemblyAIApiService;
import com.github.tgfrerich.backend.service.PodService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebFluxTest(PodController.class)
@AutoConfigureWebTestClient
public class PodControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PodService podService;

    @MockBean
    private AssemblyAIApiService assemblyAIApiService;
    @MockBean
    private PodRepository podRepository;

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
    public void sendUrl_validUrl_transcribesAudio() {
        String testUrl = "https://test.com/podcast.mp3";

        // Set up a mock response from the AssemblyAI API
        AssemblyAIApiResponse assemblyAIApiResponse = new AssemblyAIApiResponse("test_id", "test_status", "test_acoustic_model", 100, testUrl, true, "test_language_model", "test_punctuate", "test_text");
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader("Content-Type", "application/json")
                        .setBody("{\"id\":\"test_id\",\"status\":\"test_status\",\"acoustic_model\":\"test_acoustic_model\",\"audio_duration\":100,\"audio_url\":\"" + testUrl + "\",\"format_text\":true,\"language_model\":\"test_language_model\",\"punctuate\":\"test_punctuate\",\"text\":\"test_text\"}")
        );

        // Set up behavior for the mock beans
        RequestBodyForAssemblyAI requestBodyForAssemblyAI = new RequestBodyForAssemblyAI();
        requestBodyForAssemblyAI.setAudio_url(testUrl);
        when(podService.verifyUrlAndMakeToRequestBody(testUrl)).thenReturn(requestBodyForAssemblyAI);
        when(podService.UrlExistsInDatabase(podRepository, requestBodyForAssemblyAI)).thenReturn(false);
        when(assemblyAIApiService.sendTranscriptionRequestToAssemblyAI(requestBodyForAssemblyAI)).thenReturn(assemblyAIApiResponse);

        // Call the sendUrl endpoint
        AssemblyAIApiResponse response = webTestClient.post()
                .uri("/api/podcasts")
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue(testUrl)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AssemblyAIApiResponse.class)
                .returnResult()
                .getResponseBody();


        // Assert the response is as expected
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(assemblyAIApiResponse.getId());
        assertThat(response.getStatus()).isEqualTo(assemblyAIApiResponse.getStatus());
        assertThat(response.getAcoustic_model()).isEqualTo(assemblyAIApiResponse.getAcoustic_model());
        assertThat(response.getAudio_duration()).isEqualTo(assemblyAIApiResponse.getAudio_duration());
        assertThat(response.getAudio_url()).isEqualTo(assemblyAIApiResponse.getAudio_url());
        assertThat(response.getFormat_text()).isEqualTo(assemblyAIApiResponse.getFormat_text());
        assertThat(response.getLanguage_model()).isEqualTo(assemblyAIApiResponse.getLanguage_model());
        assertThat(response.getPunctuate()).isEqualTo(assemblyAIApiResponse.getPunctuate());
        assertThat(response.getText()).isEqualTo(assemblyAIApiResponse.getText());

    }
}