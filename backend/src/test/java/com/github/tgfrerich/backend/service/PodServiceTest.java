package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.Podcast;
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
    Podcast podcast1;


    @BeforeEach
    void setUp() {
        podRepository = mock(PodRepository.class);
        idService = mock(IdService.class);
        podService = new PodService();
        when(idService.generateId()).thenReturn("Some Id");
        podcast1 = new Podcast(idService.generateId(), "12", "assemblyai", 345, "url.hendrik", "en_us", 0.97, true, "assembly", true, "completed", "This is the podcast that you are listening to");
    }

    @Test
    void sendUrl_withValidUrl_shouldReturnSameUrl() {
        String url = "test.url";
        String result = podService.sendUrl(url);

        // Assert
        assertEquals(url, result);
    }

    @Test
    void sendUrl_withEmptyUrl_shouldThrowException() {
        String url = "";
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> podService.sendUrl(url));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertNull(exception.getReason());
    }


}