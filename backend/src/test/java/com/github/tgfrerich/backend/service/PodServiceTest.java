package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import com.github.tgfrerich.backend.repository.PodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PodServiceTest {

    PodRepository podRepository;
    PodService podService;
    IdService idService;
    TranscribedPodcastFromAssemblyAI transcribedPodcastFromAssemblyAI1;


    @BeforeEach
    void setUp() {
        podRepository = mock(PodRepository.class);
        idService = mock(IdService.class);
        podService = new PodService();
        when(idService.generateId()).thenReturn("Some Id");
        transcribedPodcastFromAssemblyAI1 = new TranscribedPodcastFromAssemblyAI(idService.generateId(), "https://someurl.com/podcast.mp3", "completed", 3528, "assemblyai_default", "assemblyai_default", "en_us", true, true, null, true, null);
    }

    @Test
    void sendUrl_withValidUrl_shouldReturn_RequestBodyForAssemblyAI_withValidUrl() {
        String url = "http://test.url";
        RequestBodyForAssemblyAI result = podService.verifyUrlAndMakeToRequestBody(url);

        assertEquals(url, result.getAudio_url());
    }

    @Test
    void sendUrl_withEmptyUrl_shouldThrowException() {
        String url = "";
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> podService.verifyUrlAndMakeToRequestBody(url));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertNull(exception.getReason());
    }


    @Test
    void isValidURL_WithHttpUrl_shouldReturnTrue() {
        String url = "https://test.com";
        assertTrue(podService.isValidURL(url));
    }

    @Test
    void isValidURL_WithoutHttpUrl_shouldReturnFalse() {
        String url = "www.hello.com";
        assertFalse(podService.isValidURL(url));
    }
}