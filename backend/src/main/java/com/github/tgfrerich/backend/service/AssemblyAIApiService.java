package com.github.tgfrerich.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AssemblyAIApiService {

    private static final String ASSEMBLY_AI_API_ENDPOINT = "https://api.assemblyai.com/v2";
    private static final String AUTH_TOKEN_ASSEMBLYAI = System.getenv("API_KEY_ASSEMBLYAI");

    private WebClient webClient = WebClient.create(ASSEMBLY_AI_API_ENDPOINT);


    public String transcribeAudio(String audioUrl) {
        //"{\"audio_url\": \"" + audioUrl + "\"}"
        var body = "{\n" +
                "\"audio_url\":\n" + audioUrl +
                "}";
        return webClient.post()
                .uri("/transcript")
                .header("Authorization", AUTH_TOKEN_ASSEMBLYAI)
                .bodyValue(audioUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}



