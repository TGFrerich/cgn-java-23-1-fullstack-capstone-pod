package com.github.tgfrerich.backend.controller;

import com.github.tgfrerich.backend.service.PodService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PodController {
    private final PodService podService;

    public PodController(PodService podService) {
        this.podService = podService;
    }

    @PostMapping("/podcasts")
    public String sendUrl(@RequestBody(required = false) String url) {
        return podService.sendUrl(url);
    }
}
