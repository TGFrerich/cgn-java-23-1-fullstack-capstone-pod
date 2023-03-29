package com.github.tgfrerich.backend.controller;

import com.github.tgfrerich.backend.model.AssemblyAIApiResponse;
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

    public PodController(PodService podService, AssemblyAIApiService assemblyAIApiService) {
        this.podService = podService;
        this.assemblyAIApiService = assemblyAIApiService;
    }

    @PostMapping("/podcasts")
    public AssemblyAIApiResponse sendUrl(@RequestBody(required = false) String url) {
        var body = podService.sendUrl(url);
        return assemblyAIApiService.transcribeAudio(body);

    }
}
