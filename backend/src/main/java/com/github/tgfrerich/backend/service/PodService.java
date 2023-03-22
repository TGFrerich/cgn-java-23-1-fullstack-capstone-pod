package com.github.tgfrerich.backend.service;
import com.github.tgfrerich.backend.repository.PodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PodService {
    private final IdService idService;
    private final PodRepository podRepository;

    public PodService(PodRepository podRepository, IdService idService) {
        this.podRepository = podRepository;
        this.idService = idService;
    }

    public String sendUrl(String url) {
        if (url.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "URL Required");
        } else {
            return url;
        }

    }
}
