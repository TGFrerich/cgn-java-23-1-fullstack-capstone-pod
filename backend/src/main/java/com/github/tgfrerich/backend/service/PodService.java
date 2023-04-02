package com.github.tgfrerich.backend.service;

import com.github.tgfrerich.backend.model.RequestBodyForAssemblyAI;
import com.github.tgfrerich.backend.repository.PodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URL;

@Service
public class PodService {


    public RequestBodyForAssemblyAI sendUrl(String url) {
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

    public boolean UrlExistsInDatabase(PodRepository podRepository, RequestBodyForAssemblyAI requestBody) {
        if (podRepository.existsByAudio_Url(requestBody.getAudio_url())) {
            return true;
        } else {
            return false;
        }

    }
}
