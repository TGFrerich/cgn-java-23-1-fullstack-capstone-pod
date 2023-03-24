package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service

public class AssemblyAIApiService {

    private static final String ASSEMBLY_AI_API_ENDPOINT = "https://api.assemblyai.com/v2";
    private static final String AUTH_TOKEN_ASSEMBLYAI = System.getenv("API_KEY_ASSEMBLYAI");

    private static WebClient webClient;

    public void AssemblyAIClient(String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(ASSEMBLY_AI_API_ENDPOINT)
                .defaultHeader("Authorization", AUTH_TOKEN_ASSEMBLYAI)
                .build();
    }

    public Mono<AssemblyAIApiResponse> transcribeAudio(String audioUrl) {
        return this.webClient.post()
                .uri("/transcript")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{\"audio_url\": \"" + audioUrl + "\"}"))
                .retrieve()
                .bodyToMono(AssemblyAIApiResponse.class);
    }
}



