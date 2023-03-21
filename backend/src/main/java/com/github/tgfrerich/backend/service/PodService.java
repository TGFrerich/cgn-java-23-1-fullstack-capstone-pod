package com.github.tgfrerich.backend.service;
import com.github.tgfrerich.backend.repository.PodRepository;
import org.springframework.stereotype.Service;

@Service
public class PodService {
    private final IdService idService;
    private final PodRepository podRepository;

    public PodService(PodRepository podRepository, IdService idService) {
        this.podRepository = podRepository;
        this.idService = idService;
    }

    public String sendUrl(String url) {
        String inTheFutureUrlWillBeCheckedIfAlreadyInDatabase = url;
        return inTheFutureUrlWillBeCheckedIfAlreadyInDatabase;
    }
}
