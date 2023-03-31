package com.github.tgfrerich.backend.controller;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
import com.github.tgfrerich.backend.repository.PodRepository;
import com.github.tgfrerich.backend.service.AssemblyAIApiService;
import com.github.tgfrerich.backend.service.PodService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PodController {
    private final PodService podService;
    private final AssemblyAIApiService assemblyAIApiService;

    private final PodRepository podRepository;

    public PodController(PodService podService, AssemblyAIApiService assemblyAIApiService, PodRepository podRepository) {
        this.podService = podService;
        this.assemblyAIApiService = assemblyAIApiService;
        this.podRepository = podRepository;
    }

    @PostMapping("/podcasts")
    public AssemblyAIApiResponse sendUrlToBackend_thenCheckIfUrlExists_returnCorrespondingResult(@RequestBody(required = false) String url) {
        var requestBodyForAssemblyAI = podService.sendUrl(url);
        boolean urlAlreadyExistsInDatabase = podService.UrlExistsInDatabase(podRepository, requestBodyForAssemblyAI);
        var response = (urlAlreadyExistsInDatabase == false) ? assemblyAIApiService.sendTranscriptionRequestToAssemblyAI(requestBodyForAssemblyAI) : podRepository.findByAudioUrl(requestBodyForAssemblyAI.getAudio_url());
        return (AssemblyAIApiResponse) response;
    }
}
