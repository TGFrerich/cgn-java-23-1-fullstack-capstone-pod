package com.github.tgfrerich.backend.controller;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.model.AssemblyAIWebhook;
import com.github.tgfrerich.backend.model.RequestFromFrontendDTO;
import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import com.github.tgfrerich.backend.repository.AssemblyResponseRepository;
import com.github.tgfrerich.backend.repository.PodRepository;
import com.github.tgfrerich.backend.repository.WebhookRepository;
import com.github.tgfrerich.backend.service.AssemblyAIApiService;
import com.github.tgfrerich.backend.service.PodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/api")
public class PodController {
    private final PodService podService;
    private final AssemblyAIApiService assemblyAIApiService;
    private final PodRepository podRepository;
    private final AssemblyResponseRepository assemblyResponseRepository;
    private final WebhookRepository webhookRepository;

    private static final Logger logger = LoggerFactory.getLogger(PodController.class);

    final Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    public PodController(PodService podService, AssemblyAIApiService assemblyAIApiService, PodRepository podRepository, AssemblyResponseRepository assemblyResponseRepository, WebhookRepository webhookRepository) {
        this.podService = podService;
        this.assemblyAIApiService = assemblyAIApiService;
        this.podRepository = podRepository;
        this.assemblyResponseRepository = assemblyResponseRepository;
        this.webhookRepository = webhookRepository;
    }

    @PostMapping("/podcasts")
    public SseEmitter sendUrl(@RequestBody RequestFromFrontendDTO requestFromFrontendDTO) {

        String url = requestFromFrontendDTO.getAudio_url();

        var requestBodyForAssemblyAI = podService.verifyUrlAndMakeToRequestBody(url);
        boolean urlAlreadyExistsInDatabase = podService.UrlExistsInDatabase(podRepository, requestBodyForAssemblyAI);

        SseEmitter emitter = new SseEmitter(86400000L);


        if (urlAlreadyExistsInDatabase) {
            Optional<TranscribedPodcastFromAssemblyAI> response = podRepository.findByAudioUrl(requestBodyForAssemblyAI.getAudio_url());
            try {

                emitter.send(response);
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        } else {
            AssemblyAIApiResponse assemblyAIApiResponse = assemblyAIApiService.sendTranscriptionRequestToAssemblyAI(requestBodyForAssemblyAI);
            assemblyResponseRepository.save(assemblyAIApiResponse);

            sseEmitterMap.put(assemblyAIApiResponse.getId(), emitter);
        }

        return emitter;
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody AssemblyAIWebhook webhookData) {
        webhookRepository.save(webhookData);
        if ("completed".equalsIgnoreCase(webhookData.getStatus())) {
            TranscribedPodcastFromAssemblyAI transcribedAudio = assemblyAIApiService.fetchTranscriptionResult(webhookData.getId());
            podRepository.save(transcribedAudio);

            SseEmitter sseEmitter = sseEmitterMap.get(webhookData.getId());
            if (sseEmitter != null) {
                try {
                    sseEmitter.send(transcribedAudio);
                    sseEmitter.complete();
                } catch (IOException e) {
                    logger.error("Error sending SSE data", e);
                }
                sseEmitterMap.remove(webhookData.getId());

            }
            return ResponseEntity.ok("Webhook received and processed");
        } else if ("error".equalsIgnoreCase(webhookData.getStatus())) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in transcription");
        } else {
            return ResponseEntity.ok("Webhook received");
        }
    }

}


