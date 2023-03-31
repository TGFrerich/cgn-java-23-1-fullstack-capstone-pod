package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AssemblyAIApiService {

    public static String ASSEMBLY_AI_API_ENDPOINT = "https://api.assemblyai.com/v2";
    public static final String AUTH_TOKEN_ASSEMBLYAI = System.getenv("API_KEY_ASSEMBLYAI");

    private WebClient webClient = WebClient.create(ASSEMBLY_AI_API_ENDPOINT);


    public AssemblyAIApiResponse sendTranscriptionRequestToAssemblyAI(RequestBodyForAssemblyAI audioUrl) {
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



