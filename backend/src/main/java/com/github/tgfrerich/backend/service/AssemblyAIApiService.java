package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AssemblyAIApiService {


    private static final String AUTH_TOKEN_ASSEMBLYAI = System.getenv("API_KEY_ASSEMBLYAI");
    private static final String YOUR_BASE_URL = System.getenv("YOUR_BASE_URL");
    public WebClient webClient;

    public AssemblyAIApiService(WebClient webClient) {
        this.webClient = webClient;
    }


    public AssemblyAIApiResponse sendTranscriptionRequestToAssemblyAI(RequestBodyForAssemblyAI audioUrl) {
        String webhookUrl = YOUR_BASE_URL + "/api/webhook";

        RequestBodyForAssemblyAI requestBody = new RequestBodyForAssemblyAI(
                audioUrl.getAudio_url(),
                webhookUrl,
                true,
                true
        );

        return webClient.post()
                .uri("/transcript")
                .header("Authorization", AUTH_TOKEN_ASSEMBLYAI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(AssemblyAIApiResponse.class)
                .block();
    }


    public TranscribedPodcastFromAssemblyAI fetchTranscriptionResult(String id) {
        return webClient.get()
                .uri("/transcript/{id}", id)
                .header("Authorization", AUTH_TOKEN_ASSEMBLYAI)
                .retrieve()
                .bodyToMono(TranscribedPodcastFromAssemblyAI.class)
                .block();
    }
}
