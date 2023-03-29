package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AssemblyAIApiService {

    private static final String ASSEMBLY_AI_API_ENDPOINT = "https://api.assemblyai.com/v2";
    private static final String AUTH_TOKEN_ASSEMBLYAI = System.getenv("API_KEY_ASSEMBLYAI");

    private WebClient webClient = WebClient.create(ASSEMBLY_AI_API_ENDPOINT);


    public AssemblyAIApiResponse transcribeAudio(RequestBodyForAssemblyAI audioUrl) {
        return webClient.post()
                .uri("/transcript")
                .header("Authorization", AUTH_TOKEN_ASSEMBLYAI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(audioUrl)
                .retrieve()
                .bodyToMono(AssemblyAIApiResponse.class)
                .block();
    }
}



