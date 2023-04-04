package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import com.github.tgfrerich.backend.model.TranscribedPodcastFromAssemblyAI;
import com.github.tgfrerich.backend.repository.PodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;
import java.util.Optional;

@Service
public class PodService {


    public RequestBodyForAssemblyAI verifyUrlAndMakeToRequestBody(String url) {
        url = url.strip();
        if (isValidURL(url)) {
            RequestBodyForAssemblyAI requestBodyForAssemblyAI = new RequestBodyForAssemblyAI();
            requestBodyForAssemblyAI.setAudio_url(url);
            return requestBodyForAssemblyAI;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    public boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean UrlExistsInDatabase(PodRepository podRepository, RequestBodyForAssemblyAI requestBodyForAssemblyAI) {
        Optional<TranscribedPodcastFromAssemblyAI> storedTranscription = podRepository.findByAudioUrl(requestBodyForAssemblyAI.getAudio_url());
        return storedTranscription.isPresent();
    }

}
