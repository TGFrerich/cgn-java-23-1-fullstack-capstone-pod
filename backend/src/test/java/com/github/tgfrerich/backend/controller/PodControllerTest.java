package com.github.tgfrerich.backend.controller;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import com.github.tgfrerich.backend.repository.AssemblyResponseRepository;
import com.github.tgfrerich.backend.repository.PodRepository;
import com.github.tgfrerich.backend.repository.WebhookRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
    @MockBean
    private AssemblyResponseRepository assemblyResponseRepository;
    @MockBean
    private WebhookRepository webhookRepository;
    @Autowired
    private PodController podController;

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
        AssemblyAIApiResponse assemblyAIApiResponse = new AssemblyAIApiResponse("SomeId", "Queued", "assemblyai_default", 3258, "https://test.com/podcast.mp3", true, "assemblyai_default", true, "Podcast Text", true, true);
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
        when(podService.UrlExistsInDatabase(podRepository, requestBodyForAssemblyAI)).thenReturn(true);
        when(assemblyAIApiService.sendTranscriptionRequestToAssemblyAI(requestBodyForAssemblyAI)).thenReturn(assemblyAIApiResponse);
        when(podRepository.findByAudioUrl("https://test.com/podcast.mp3")).thenReturn(Optional.of(new TranscribedPodcastFromAssemblyAI(
                "12",
                "https://test.com/podcast.mp3",
                "",
                1.12,
                "",
                "",
                "",
                true,
                true,
                List.of(),
                true,
                List.of()
        )));


        WebTestClient.BodyContentSpec response = webTestClient.post()
                .uri("/api/podcasts")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                                                    "audio_url": "https://test.com/podcast.mp3",
                                                    "language_code": ""
                        }
                        """)
                .exchange()
                .expectStatus().isOk()
                .expectBody();


        String responseString = String.valueOf(response.returnResult());
        //System.out.println(responseString);

        assertTrue(responseString.contains(testUrl));


    }
}